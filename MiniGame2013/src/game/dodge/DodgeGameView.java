package game.dodge;

import game.dodge.unit.CUnitFactory;
import game.main.R;

import java.util.ArrayList;
import java.util.Random;

import org.pjhjohn.framework.ApplicationManager;
import org.pjhjohn.framework.Option;
import org.pjhjohn.framework.ImageObj;
import org.pjhjohn.framework.state.CStatePregame;
import org.pjhjohn.framework.state.IState;
import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IFactory;
import org.pjhjohn.framework.widget.SettingButton;
import org.pjhjohn.framework.widget.Star;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *|
|* [TODO 1] Use System Clock to Check Score									   *|
|* CStatePlaying.getInstance().onInit() : Save Initial time					   *|
|* CurrentScore = time - initialTime										   *|
|* When HighScore > CurrentScore, represent currentScore -> HighScore realtime *|
|* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*
 * [TODO 2] Make Abstract Class for this "GameView" Things.
 * These things should contain main game sequences such as onCreate / onPause / onResume / onDestroy
 * Automatically register / unregister corresponding resources / threads.
 */
public class DodgeGameView extends SurfaceView implements OnTouchListener, IGameManager, SurfaceHolder.Callback {
	private IFactory 	unitFactory = (IFactory) CUnitFactory.getInstance();
	private IState 		state;
	private AUnit 		player;
	private Star[]		stars;
	private ImageObj 	settingBtn = SettingButton.getInstance();
	private ArrayList<AUnit> asteroids;
	private DodgeGameViewThread mGameThread;
	
	private Random 	random 	  = new Random();
	private Paint 	textPaint = new Paint();
	private float 	score;
	private float	highScore;
	private long	startSystemTime;
	private int		threadCount;
	
	public DodgeGameView(Context context){
		super(context);
		this.textPaint.setColor(Color.WHITE);
		this.textPaint.setAntiAlias(true);
		this.textPaint.setTextSize(Option.getDeviceHeight()/16);
		this.asteroids = new ArrayList<AUnit>();
		this.stars = new Star[Option.Dodge.NUMBER_OF_STAR];
		this.highScore= 0;
		
		this.state = CStatePregame.getInstance();
		this.state.setGameManager(this);				// Set GameManager -> State
		this.state.init();								// Start State
		
		this.mGameThread = new DodgeGameViewThread(this.getHolder());
		ApplicationManager.setState(this.state);		// Set State -> Thread
		this.mGameThread.start();
		
		this.setOnTouchListener(this);
	}
	@Override
	public void onDraw(Canvas canvas) {
		// Layer 0 : BLACK BACKGROUND
		canvas.drawColor(Color.BLACK);
		// Layer 1 : Background-floating Stars
		for(int i = 0; i < Option.Dodge.NUMBER_OF_STAR; i++) this.stars[i].draw(canvas, ImageObj.Align.CENTER);
		// Layer 2 : Asteroids
		for(AUnit asteroid:asteroids) asteroid.draw(canvas, ImageObj.Align.CENTER);
		// Layer 3 : Score & Time 
		this.textPaint.setTextAlign(Align.LEFT);
		canvas.drawText("최고득점: " + String.valueOf((int)highScore), Option.Dodge.PADDING_SCORETEXT , this.textPaint.getTextSize() + Option.Dodge.PADDING_SCORETEXT, this.textPaint);
		this.textPaint.setTextAlign(Align.RIGHT);
		canvas.drawText("시간 : "+score2string(this.score), Option.getDeviceWidth() - Option.Dodge.PADDING_SCORETEXT, this.textPaint.getTextSize() + Option.Dodge.PADDING_SCORETEXT, this.textPaint);		
		// Layer 4 : Setting Button & Player & Controller(Optional)
		this.settingBtn.draw(canvas, ImageObj.Align.CENTER);
		this.player.draw(canvas, ImageObj.Align.CENTER);
		ApplicationManager.getController().draw(canvas);
	}

//	Implement OnTouchListener
	@Override 
	public boolean onTouch(View view, MotionEvent event) {
		return this.state.onTouch(view, event);
	}
	
//	Implement IGameManager
	public void update() {
		threadCount++;
		score = (System.currentTimeMillis() - this.startSystemTime) / 100;
		if(threadCount%200==0){
			AUnit newUnit = unitFactory.create((threadCount%600==0) ? CUnitFactory.UnitType.GUIDED_ASTEROID : CUnitFactory.UnitType.ASTEROID);
			int rand = random.nextInt(4);
			switch(rand){
				case 0: newUnit.setPosition(0, random.nextFloat() * Option.getDeviceHeight());									break;
				case 1: newUnit.setPosition(Option.getDeviceWidth(), random.nextFloat() * Option.getDeviceHeight());	break;
				case 2: newUnit.setPosition(random.nextFloat() * Option.getDeviceWidth(), 0);									break;
				case 3: newUnit.setPosition(random.nextFloat() * Option.getDeviceWidth(), Option.getDeviceHeight());	break;
			} this.asteroids.add(AsteroidWithInitialSpeed(newUnit));
		} player.update();
		for(AUnit asteroid:asteroids) asteroid.update();
		this.highScore = (highScore < score)? score : highScore;
	}
	public void updateBackground(){
		for (int i = 0; i < stars.length; i++) stars[i].move();
	}	
	@Override
	public void setCurrentState(IState currentState) {
		this.state.dismiss();							// Exit State
		this.state = currentState;						// Change to New State
		this.state.setGameManager(this);				// Set GameManager -> State
		ApplicationManager.setState(this.state);		// Set State -> Thread
		this.state.init();								// Start State
	}
	@Override
	public boolean isGameOver() {
		boolean isCrashed = false;
		for(AUnit asteroid:asteroids) if(player.isCrashed(asteroid)) isCrashed = true;
		if(isCrashed) this.player.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ship_destroyed_mid));
		return isCrashed;
	}
	@Override
	public void onInit() {
		ApplicationManager.setThreadFlag(true);
		this.threadCount = 0;
		this.player = unitFactory.create(CUnitFactory.UnitType.PLAYER);
		this.player.setPosition(Option.getDeviceWidth() / 2, Option.getDeviceHeight() / 2);
		// Initialize Asteroids
		for (int i = 0; i < Option.Dodge.NUMBER_OF_STAR; i++){
			stars[i] = new Star();
			stars[i].setRandomColor();
			stars[i].setPosition(Option.getDeviceWidth()*random.nextFloat(), Option.getDeviceHeight()*random.nextFloat());
			stars[i].setSpeed(0,Option.Dodge.STAR_SPEED_MIN + Option.Dodge.STAR_SPEED_RANGE * random.nextFloat());
		}
		this.asteroids.clear();
		for (int i = 0; i < Option.Dodge.NUMBER_OF_ASTEROID; i++) {
			AUnit newUnit = unitFactory.create(CUnitFactory.UnitType.ASTEROID);
			while (true) {
				newUnit.setPosition(random.nextFloat() * Option.getDeviceWidth(), random.nextFloat() * Option.getDeviceHeight());
				if(player.getDistance(newUnit) > Option.Dodge.ASTEROID_SAFETY_RANGE) break;
			} this.asteroids.add(AsteroidWithInitialSpeed(newUnit));
		}
	}
	@Override
	public void onStart() {
		this.startSystemTime = System.currentTimeMillis();
	}
	@Override
	public void onDestroy() {
//		ApplicationManager.setThreadFlag(false);
		ApplicationManager.getController().destroy();
		ApplicationManager.setState(null);
	}
	@Override 
	public void drawSurface(Canvas canvas) {
		this.onDraw(canvas);
	}
	
//	Implement SurfaceHolder.Callback
	@Override public void surfaceCreated(SurfaceHolder holder) {
		ApplicationManager.setThreadFlag(true);
		this.mGameThread = new DodgeGameViewThread(this.getHolder());
		this.mGameThread.start();
	}
	@Override 
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {}
	@Override 
	public void surfaceDestroyed(SurfaceHolder holder) {
		ApplicationManager.setThreadFlag(false);
	}

//	Private Class & Methods
	private String score2string(float score){
		int min, sec, ms = (int)score;
		min = ms/600;		ms = ms - min * 600;
		sec = ms/10;		ms = ms - sec * 10;
		return ((min<10)?("0"+min):min)+":"+((sec<10)?("0"+sec):sec)+":"+ms;
	}
	private AUnit AsteroidWithInitialSpeed(AUnit asteroid){
		float diff_x = asteroid.getX() - player.getX();
		float diff_y = asteroid.getY() - player.getY();
		double degree = Math.toDegrees(Math.atan(diff_y / diff_x)) + (random.nextFloat() * 2 * Option.Dodge.ASTEROID_ANGLE_RANGE - Option.Dodge.ASTEROID_ANGLE_RANGE);
		asteroid.setSpeedX((float) (Option.Dodge.ASTEROID_SPEED * Math.cos(Math.toRadians(degree))) * (-diff_x)/Math.abs(diff_x));
		asteroid.setSpeedY((float) (Option.Dodge.ASTEROID_SPEED * Math.sin(Math.toRadians(degree))) * (-diff_x)/Math.abs(diff_x));
		return asteroid;
	}
	private class DodgeGameViewThread extends Thread{ 
		private SurfaceHolder holder;
		public DodgeGameViewThread(SurfaceHolder holder){
			this.holder = holder;
		}
		public void run(){
			Log.i(this.toString(),"ThreadStart");
			Canvas canvas = null;
			while(ApplicationManager.isThreadActive()){
				if(ApplicationManager.getState()==null) ApplicationManager.setThreadFlag(false);
				else ApplicationManager.getState().update();
				try {
					canvas = holder.lockCanvas(null);
					synchronized (holder) {
						ApplicationManager.getState().drawSurface(canvas);
					}
				} catch (Exception e){
				} finally {
					if(canvas!=null) holder.unlockCanvasAndPost(canvas);
				}
			} Log.i(this.toString(),"ThreadStop");
		}
	}
	@Override
	public void onResume() {
		Toast.makeText(this.getContext(), "((IGameManager)DodgeGameView).onResume() Triggered", Toast.LENGTH_SHORT).show();		
	}
	@Override
	public void onPause() {
		Toast.makeText(this.getContext(), "((IGameManager)DodgeGameView).onPause() Triggered", Toast.LENGTH_SHORT).show();		
	}
	@Override
	public void onStop() {
		Toast.makeText(this.getContext(), "((IGameManager)DodgeGameView).onStop() Triggered", Toast.LENGTH_SHORT).show();		
	}
}
