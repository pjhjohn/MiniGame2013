package game.bubble;

import game.bubble.line.BUnitLineManager;
import game.bubble.state.BStatePregame;
import game.bubble.unit.BUnitFactory;
import game.main.R;

import org.pjhjohn.framework.obj2d.ImageObj;
import org.pjhjohn.framework.state.IState;
import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.view.AGameView;
import org.pjhjohn.framework.view.CustomView;
import org.pjhjohn.framework.view.CustomViewBackground;
import org.pjhjohn.framework.view.CustomViewStarText;
import org.pjhjohn.framework.view.CustomViewSurfaceContainer;
import org.pjhjohn.manager.AppManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.RelativeLayout;
import app.main.AppOption;

public class BubbleView extends AGameView{
	private BUnitFactory unitFactory;
	private AUnit player;
	private AUnit movingBall;
	private BUnitLineManager lineManager;
	
	private Paint textPaint;
	private float score;
	private long startSystemTime;
	private int threadCount;
	Bitmap _bg;
	private boolean isGameover;
	
	public BubbleView(Context context) {
		super(context, BStatePregame.getInstance());
	}
	@Override
	public void onGameCreate(){
		super.onGameCreate();
		this._bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		this.unitFactory = BUnitFactory.getInstance();
		this.textPaint = new Paint();
		this.textPaint.setColor(Color.WHITE);
		this.textPaint.setAntiAlias(true);
		this.textPaint.setTextSize(AppOption.getDeviceHeight()/16);
		this.player = unitFactory.create(BUnitFactory.UnitType.BPLAYER);
		this.lineManager = BUnitLineManager.getInstance();
		this.score = 0;
		this.isGameover=false;
	}
	
	@Override
	public void onGameReady() {
		super.onGameReady();
		this.lineManager.init();
		this.isGameover = this.lineManager.pushDown();
		this.player.setPosition(AppOption.getDeviceWidth()/2, (AppOption.getDeviceHeight()*11)/12);
		this.movingBall = unitFactory.create(BUnitFactory.UnitType.RAND);
		this.movingBall.setPosition(AppOption.getDeviceWidth()/4, (AppOption.getDeviceHeight()*11)/12);
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
		movingBall.draw(canvas, ImageObj.Align.CENTER);
		player.draw(canvas, ImageObj.Align.CENTER);
		lineManager.drawBall(canvas);
		this.textPaint.setTextAlign(Align.RIGHT);
		canvas.drawText("½Ã°£ : "+score2string(this.score), AppOption.getDeviceWidth()*(3/4), this.textPaint.getTextSize(), this.textPaint);
//		AppManager.getController().draw(canvas);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		threadCount++;
		score = (System.currentTimeMillis() - this.startSystemTime) / 100;
		if(threadCount%200==0)
			this.isGameover = lineManager.pushDown();
		player.update();
		
	}
	@Override
	public void updateBackground() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void setState(IState currentState) {
		// TODO Auto-generated method stub
		this.state.dismiss();							// Exit State
		this.state = currentState;						// Change to New State
		this.state.setGameManager(this);				// Set GameManager -> State
		AppManager.setState(this.state);				// Set State -> Thread
		this.state.init();
	}
	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		if(isGameover)
			this.player.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ship_destroyed_mid));
		return isGameover;
	}
	private String score2string(float score){
		int min, sec, ms = (int)score;
		min = ms/600;		ms = ms - min * 600;
		sec = ms/10;		ms = ms - sec * 10;
		return ((min<10)?("0"+min):min)+":"+((sec<10)?("0"+sec):sec)+":"+ms;
	}
}