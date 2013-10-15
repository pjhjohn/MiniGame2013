package game.bubble;

import game.bubble.line.BUnitLineManager;
import game.bubble.state.BStatePregame;
import game.bubble.unit.BUnitBall;
import game.bubble.unit.BUnitFactory;
import game.bubble.unit.BUnitTypePlayer;
import game.bubble.unit.BUnitTypeRandBall;
import game.main.R;

import org.pjhjohn.framework.main.AGameView;
import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.resource.AUnit;
import org.pjhjohn.framework.resource.Drawable;
import org.pjhjohn.framework.resource.IFactory;
import org.pjhjohn.framework.sub.CountDownTimerPausable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;

public class BubbleView extends AGameView{
	private IFactory unitFactory;
	private AUnit player;
	private BUnitBall movingBall;
	private BUnitLineManager lineManager;

	private Paint textPaint;
	Bitmap _bg;
	private boolean Gameover;
	private boolean tick;
	private boolean timer;
	
	public BubbleView(Context context) {
		super(context, BStatePregame.getInstance());
	}
	@Override
	public void onCreate(){
		super.onCreate();
		this._bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		this.unitFactory = BUnitFactory.getInstance();
		this.textPaint = new Paint();
		this.textPaint.setColor(Color.WHITE);
		this.textPaint.setAntiAlias(true);
		this.textPaint.setTextSize(AppManager.getDeviceHeight()/16);
		this.player = unitFactory.create(BUnitTypePlayer.getInstance());
//		this.lineManager = BUnitLineManager.getInstance();
		this.Gameover=false;
	}
	
	@Override
	public void onGameReady() {
		super.onGameReady();
//		this.lineManager.init();
		this.lineManager = new BUnitLineManager();
		this.tick = false;
		this.timer = false;
		this.Gameover = this.lineManager.pushDown();
		this.player.setPosition(AppManager.getDeviceWidth()/2, (AppManager.getDeviceHeight()*11)/12);
		this.player.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.cannon), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));
		this.movingBall = (BUnitBall)unitFactory.create(BUnitTypeRandBall.getInstance());
		this.movingBall.setPosition(AppManager.getDeviceWidth()/4, (AppManager.getDeviceHeight()*11)/12);
		this.gameTimer.unregisterAll();
		this.gameTimer.registerCountDownTimer(new CountDownTimerPausable(1000000, 1000){
			@Override
			public void onTick(long millisUntilFinished){
				tick = true;
				Log.v("tick", "hi, tick is " + tick + "\n"+millisUntilFinished);
			}
			@Override
			public void onFinish(){
				Log.v("tick","finished");
				timer = true;
			}
		});
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(_bg, 0, 0, null);
		movingBall.draw(canvas, Drawable.Align.CENTER);
		player.draw(canvas, Drawable.Align.CENTER);
		lineManager.drawBall(canvas);
		this.textPaint.setTextAlign(Align.RIGHT);
//		canvas.drawText("시간 : "+score2string(this.score), AppManager.getDeviceWidth()*(3/4), this.textPaint.getTextSize(), this.textPaint);
//		AppManager.getController().draw(canvas);
	}
	@Override 
	public void updateBackground() {}
	@Override 
	public void update() {
		if(tick) Log.i("tick", "tick!!");
		if(this.tick && !movingBall.getMoving()){
			this.Gameover = lineManager.pushDown();
			this.tick=false;
		}//line내리기
		if(this.timer){
			this.timer = false;
//			this.gameTimer.unregisterAll();
//			this.gameTimer.registerCountDownTimer(new CountDownTimerPausable(1000000, 200){
//				public void onTick(long millisUntilFinished){ 
//					tick = true; 
//					Log.v("tick", "tick is " + tick + ", ms remained : "+millisUntilFinished);
//				}
//				public void onFinish(){
//					timer = true;
//					Log.v("tick", "Tick onFisish");
//				}
//			});
		}
		player.update();
		movingBall.update();
		if (movingBall.getX()<=0 || movingBall.getX()>=AppManager.getDeviceWidth())
			movingBall.setSpeedX(-movingBall.getSpeedX());
		if(movingBall.getY()<=0){//공이 위쪽 벽에 닿으면
			movingBall.setSpeedY(0);
			movingBall.setMoving(false);
			//멈춘 볼을 라인에 넣어야함
			this.movingBall = (BUnitBall)unitFactory.create(BUnitTypeRandBall.getInstance());
			this.movingBall.setPosition(AppManager.getDeviceWidth()/4, (AppManager.getDeviceHeight()*11)/12);
			movingBall.setMoving(false);
		}
			
	}

	@Override
	public boolean isGameOver() {
		if(Gameover) this.player.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ship_destroyed_mid));
		return Gameover;
	}

	public BUnitBall getMovingBall(){ return movingBall; }
}