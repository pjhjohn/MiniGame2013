package game.dodge;

import game.dodge.resource.AnimatableObjBackground;
import game.dodge.state.CStatePregame;
import game.dodge.unit.CUnitFactory;
import game.dodge.unit.CUnitTypeAsteroid;
import game.dodge.unit.CUnitTypeGuidedAsteroid;
import game.dodge.unit.CUnitTypePlayer;
import game.main.R;

import java.util.ArrayList;
import java.util.Random;

import org.pjhjohn.framework.animatable.AnimatableObj;
import org.pjhjohn.framework.drawable.DrawableObj;
import org.pjhjohn.framework.drawable.IDrawable;
import org.pjhjohn.framework.manager.AppManager;
import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IFactory;
import org.pjhjohn.framework.view.AGameView;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.os.CountDownTimer;
import android.widget.Toast;
import app.main.AppOption;

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
public class DodgeGameView extends AGameView {
	private IFactory 		 unitFactory;
	private AUnit 			 player;
	private DrawableObj 	 settingBtn;
	private ArrayList<AUnit> asteroids;
	private AnimatableObj 	 background;
	private CountDownTimer   timer;
	
	private Random 	random;
	private Paint 	textPaint;
	private float 	score;
	private float	highScore;
	private long	startSystemTime;
	
	public DodgeGameView(Context context){
		super(context, CStatePregame.getInstance());
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.unitFactory = (IFactory) CUnitFactory.getInstance();
		this.settingBtn = GameSpeedButton.getInstance();
		this.background = new AnimatableObjBackground();
		this.asteroids = new ArrayList<AUnit>();
		this.random = new Random();
		this.textPaint = new Paint();
		this.textPaint.setColor(Color.WHITE);
		this.textPaint.setAntiAlias(true);
		this.textPaint.setTextSize(AppManager.getDeviceHeight()/16);
		this.highScore= 0;
		this.player = unitFactory.create(CUnitTypePlayer.getInstance());
		this.timer = new CountDownTimer(10000000, 2000) {
			@Override
			public void onTick(long millisUntilFinished) {
				AUnit newUnit = unitFactory.create((millisUntilFinished%10000)==0 ? CUnitTypeGuidedAsteroid.getInstance() : CUnitTypeAsteroid.getInstance());
				int rand = random.nextInt(4);
				switch(rand){
					case 0: newUnit.setPosition(0, random.nextFloat() * AppManager.getDeviceHeight());									break;
					case 1: newUnit.setPosition(AppManager.getDeviceWidth(), random.nextFloat() * AppManager.getDeviceHeight());	break;
					case 2: newUnit.setPosition(random.nextFloat() * AppManager.getDeviceWidth(), 0);									break;
					case 3: newUnit.setPosition(random.nextFloat() * AppManager.getDeviceWidth(), AppManager.getDeviceHeight());	break;
				} asteroids.add(AsteroidWithInitialSpeed(newUnit));
			}
			
			@Override
			public void onFinish() {
				Toast.makeText(AppManager.getContext(), "CountDownTimer Done", Toast.LENGTH_SHORT).show();
			}
		};
	}
	@Override
	public void onGameReady() {
		this.player.setPosition(AppManager.getDeviceWidth() / 2, AppManager.getDeviceHeight() / 2);
		// Initialize Asteroids
		this.asteroids.clear();
		for (int i = 0; i < AppOption.Dodge.NUMBER_OF_ASTEROID; i++) {
			AUnit newUnit = unitFactory.create(CUnitTypeAsteroid.getInstance());
			while (true) {
				newUnit.setPosition(random.nextFloat() * AppManager.getDeviceWidth(), random.nextFloat() * AppManager.getDeviceHeight());
				if(player.distanceTo(newUnit) > AppOption.Dodge.ASTEROID_SAFETY_RANGE) break;
			} this.asteroids.add(AsteroidWithInitialSpeed(newUnit));
		}
		this.timer.cancel();
	}
	@Override
	public void onGameStart() {
		this.startSystemTime = System.currentTimeMillis();
		this.timer.start();
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);												 	// Layer 0 : BLACK BACKGROUND
		this.background.draw(canvas);						 							// Layer 1 : Background-floating Stars
		for(AUnit asteroid : asteroids) asteroid.draw(canvas, IDrawable.Align.CENTER);	// Layer 2 : Asteroids
		// Layer 3 : Score & Time 
		this.textPaint.setTextAlign(Align.LEFT);
		canvas.drawText("최고득점: " + highScore, AppOption.Dodge.PADDING_SCORETEXT , this.textPaint.getTextSize() + AppOption.Dodge.PADDING_SCORETEXT, this.textPaint);
		this.textPaint.setTextAlign(Align.RIGHT);
		canvas.drawText("시간 : "+score2string(this.score), AppManager.getDeviceWidth() - AppOption.Dodge.PADDING_SCORETEXT, this.textPaint.getTextSize() + AppOption.Dodge.PADDING_SCORETEXT, this.textPaint);		
		// Layer 4 : Setting Button & Player & Controller(Optional)
		this.settingBtn.draw(canvas, IDrawable.Align.CENTER);
		this.player.draw(canvas, IDrawable.Align.CENTER);
		AppManager.getController().draw(canvas);
	}
	
//	Implement IGameManager
	@Override
	public void update() {
		score = (System.currentTimeMillis() - this.startSystemTime) / 100;
		player.update();
		for(AUnit asteroid:asteroids) asteroid.update();
		this.highScore = (highScore < score)? score : highScore;
	}
	@Override
	public void updateBackground(){
		this.background.update();
	}
	
	@Override
	public boolean isGameOver() {
		boolean isCrashed = false;
		for(AUnit asteroid:asteroids) if(player.isCrashed(asteroid)) {
			isCrashed = true;
			break;
		}
		if(isCrashed) this.player.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ship_destroyed_mid));
		return isCrashed;
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
		double degree = Math.toDegrees(Math.atan(diff_y / diff_x)) + (random.nextFloat() * 2 * AppOption.Dodge.ASTEROID_ANGLE_RANGE - AppOption.Dodge.ASTEROID_ANGLE_RANGE);
		asteroid.setSpeedX((float) (AppOption.Dodge.ASTEROID_SPEED * Math.cos(Math.toRadians(degree))) * (-diff_x)/Math.abs(diff_x));
		asteroid.setSpeedY((float) (AppOption.Dodge.ASTEROID_SPEED * Math.sin(Math.toRadians(degree))) * (-diff_x)/Math.abs(diff_x));
		return asteroid;
	}
}
