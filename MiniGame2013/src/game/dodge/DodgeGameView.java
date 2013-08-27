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

import org.pjhjohn.framework.main.AGameView;
import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.resource.AUnit;
import org.pjhjohn.framework.resource.AnimatableObj;
import org.pjhjohn.framework.resource.DrawableObj;
import org.pjhjohn.framework.resource.Drawable;
import org.pjhjohn.framework.resource.IFactory;

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
	
	private Random random;
	private Paint  textPaint;
	
	public DodgeGameView(Context context){
		super(context, CStatePregame.getInstance());
		this.gameScore = CScoreDodge.getInstance();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.unitFactory = CUnitFactory.getInstance();
		this.settingBtn = GameSpeedButton.getInstance();
		this.background = new AnimatableObjBackground();
		this.asteroids = new ArrayList<AUnit>();
		this.random = new Random();
		this.textPaint = new Paint();
		this.textPaint.setColor(Color.WHITE);
		this.textPaint.setAntiAlias(true);
		this.textPaint.setTextSize(AppManager.getDeviceHeight()/16);
		this.player = unitFactory.create(CUnitTypePlayer.getInstance());
		this.timer = new CountDownTimer(10000000, 2000) {
			@Override
			public void onTick(long millisUntilFinished) {
				AUnit newUnit = unitFactory.create((millisUntilFinished%10000)==0 ? CUnitTypeGuidedAsteroid.getInstance() : CUnitTypeAsteroid.getInstance());
				int rand = random.nextInt(4);
				switch(rand){
					case 0: newUnit.setPosition(0, random.nextFloat() * AppManager.getDeviceHeight());								break;
					case 1: newUnit.setPosition(AppManager.getDeviceWidth(), random.nextFloat() * AppManager.getDeviceHeight());	break;
					case 2: newUnit.setPosition(random.nextFloat() * AppManager.getDeviceWidth(), 0);								break;
					case 3: newUnit.setPosition(random.nextFloat() * AppManager.getDeviceWidth(), AppManager.getDeviceHeight());	break;
				} asteroids.add(getInitialSpeedAsteroid(newUnit));
			}
			
			@Override
			public void onFinish() {
				Toast.makeText(AppManager.getContext(), "CountDownTimer Done", Toast.LENGTH_SHORT).show();
			}
		};
	}
	@Override
	public void onGameReady() {
		super.onGameReady();
		this.player.setPosition(AppManager.getDeviceWidth() / 2, AppManager.getDeviceHeight() / 2);
		// Initialize Asteroids
		this.asteroids.clear();
		for (int i = 0; i < AppOption.Dodge.NUMBER_OF_ASTEROID; i++) {
			AUnit newUnit = unitFactory.create(CUnitTypeAsteroid.getInstance());
			while (true) {
				newUnit.setPosition(random.nextFloat() * AppManager.getDeviceWidth(), random.nextFloat() * AppManager.getDeviceHeight());
				if(player.distanceTo(newUnit) > AppOption.Dodge.ASTEROID_SAFETY_RANGE) break;
			} this.asteroids.add(getInitialSpeedAsteroid(newUnit));
		}
		this.timer.cancel();
	}
	@Override
	public void onGameStart() {
		super.onGameStart();
		this.timer.start();
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);												 	// Layer 0 : BLACK BACKGROUND
		this.background.draw(canvas);						 							// Layer 1 : Background-floating Stars
		for(AUnit asteroid : asteroids) asteroid.draw(canvas, Drawable.Align.CENTER);	// Layer 2 : Asteroids
		// Layer 3 : Score & Time 
		this.textPaint.setTextAlign(Align.LEFT);
		canvas.drawText("Hi-Score : " + this.gameScore.hiScore(), AppOption.Dodge.PADDING_SCORETEXT , this.textPaint.getTextSize() + AppOption.Dodge.PADDING_SCORETEXT, this.textPaint);
		this.textPaint.setTextAlign(Align.RIGHT);
		canvas.drawText("½Ã°£ : " + this.gameTimer, AppManager.getDeviceWidth() - AppOption.Dodge.PADDING_SCORETEXT, this.textPaint.getTextSize() + AppOption.Dodge.PADDING_SCORETEXT, this.textPaint);		
		// Layer 4 : Setting Button & Player & Controller(Optional)
		this.settingBtn.draw(canvas, Drawable.Align.CENTER);
		this.player.draw(canvas, Drawable.Align.CENTER);
		AppManager.getController().draw(canvas);
	}
	
//	Implement IGameManager
	@Override
	public void update() {
		player.update();
		for(AUnit asteroid:asteroids) asteroid.update();
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
	private AUnit getInitialSpeedAsteroid(AUnit asteroid){
		float diff_x = asteroid.getX() - player.getX();
		float diff_y = asteroid.getY() - player.getY();
		double degree = Math.toDegrees(Math.atan(diff_y / diff_x)) + (random.nextFloat() * 2 * AppOption.Dodge.ASTEROID_ANGLE_RANGE - AppOption.Dodge.ASTEROID_ANGLE_RANGE);
		asteroid.setSpeedX((float) (AppOption.Dodge.ASTEROID_SPEED * Math.cos(Math.toRadians(degree))) * (-diff_x)/Math.abs(diff_x));
		asteroid.setSpeedY((float) (AppOption.Dodge.ASTEROID_SPEED * Math.sin(Math.toRadians(degree))) * (-diff_x)/Math.abs(diff_x));
		return asteroid;
	}
}
