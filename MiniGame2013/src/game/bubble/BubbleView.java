package game.bubble;

import game.bubble.line.BUnitLineManager;
import game.bubble.state.BStatePregame;
import game.bubble.unit.BUnitBall;
import game.bubble.unit.BUnitFactory;
import game.bubble.unit.BUnitTypePlayer;
import game.bubble.unit.BUnitTypeRandBall;
import game.main.R;


import org.pjhjohn.framework.drawable.IDrawable;
import org.pjhjohn.framework.manager.AppManager;
import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IFactory;
import org.pjhjohn.framework.view.AGameView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class BubbleView extends AGameView{
	private IFactory unitFactory;
	private AUnit player;
	private AUnit movingBall;
	private BUnitLineManager lineManager;

	private Paint textPaint;
	private float score;
	private long startSystemTime;
	private int threadCount;
	Bitmap _bg;
	private boolean Gameover;
	
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

		this.score = 0;
		this.Gameover=false;
	}
	
	@Override
	public void onGameReady() {
		super.onGameReady();
//		this.lineManager.init();
		this.lineManager = new BUnitLineManager();
		this.Gameover = this.lineManager.pushDown();
		this.player.setPosition(AppManager.getDeviceWidth()/2, (AppManager.getDeviceHeight()*11)/12);
		this.player.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.cannon), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));
		this.movingBall = unitFactory.create(BUnitTypeRandBall.getInstance());
		this.movingBall.setPosition(AppManager.getDeviceWidth()/4, (AppManager.getDeviceHeight()*11)/12);
	}
	@Override
	public void onGameStart() {
		super.onGameStart();
		this.startSystemTime = System.currentTimeMillis();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(_bg, 0, 0, null);
		movingBall.draw(canvas, IDrawable.Align.CENTER);
		player.draw(canvas, IDrawable.Align.CENTER);
		lineManager.drawBall(canvas);
		this.textPaint.setTextAlign(Align.RIGHT);
		canvas.drawText("½Ã°£ : "+score2string(this.score), AppManager.getDeviceWidth()*(3/4), this.textPaint.getTextSize(), this.textPaint);
//		AppManager.getController().draw(canvas);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		threadCount++;
		score = (System.currentTimeMillis() - this.startSystemTime) / 100;
		if(threadCount%80==0)
			this.Gameover = lineManager.pushDown();
		player.update();
		
	}
	@Override
	public void updateBackground() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		if(Gameover)
			this.player.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ship_destroyed_mid));
		return Gameover;
	}
	private String score2string(float score){
		int min, sec, ms = (int)score;
		min = ms/600;		ms = ms - min * 600;
		sec = ms/10;		ms = ms - sec * 10;
		return ((min<10)?("0"+min):min)+":"+((sec<10)?("0"+sec):sec)+":"+ms;
	}
}