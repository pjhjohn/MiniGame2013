package org.pjhjohn.framework.view;

import org.pjhjohn.framework.manager.AppManager;
import org.pjhjohn.framework.manager.IGameManager;
import org.pjhjohn.framework.manager.IState;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

/*
 * [TODO 2] Make Abstract Class for this "GameView" Things.
 * These things should contain main game sequences such as onCreate / onPause / onResume / onDestroy
 * Automatically register / unregister corresponding resources / threads.
 */
public abstract class AGameView extends SurfaceView implements OnTouchListener, SensorEventListener, IGameManager, SurfaceHolder.Callback {
	protected AGameViewThread gameThread;
	
//	Constructor
	private AGameView(Context context){
		super(context);	// But Nobody can call this outside.
	}
	
	
	public AGameView(Context context, IState initialState){
		super(context);
		this.onCreate();
		AppManager.setGameView(this);
		AppManager.setState(initialState);
		this.gameThread = new AGameViewThread(this.getHolder());
		this.gameThread.start();
		this.setOnTouchListener(this);
	}
	
//	Implement OnTouchListener
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		if(!view.equals(AppManager.getGameView())) Log.w("AGameView", "TouchTriggered View is not registered view.");
		if(AppManager.getState()!=null) return AppManager.getState().update(event);
		else return false;
	}
	
//	Implement SensorEventListener
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(AppManager.getState()!=null) AppManager.getState().update(event);
	}
	
//	Implement IGameManager	
	@Override public void drawSurface(Canvas canvas) {
		this.onDraw(canvas);
	}
	@Override public void onCreate() {	
		Log.i("AGameView", "onGameCreate");	
		AppManager.setThreadFlag(true);
	}
	@Override public void onDestroy() {
		Log.i("AGameView", "onGameDestroy");	
		if(AppManager.getController()!=null) AppManager.getController().dismiss();
		AppManager.setState(null);
	}	
	@Override public void onGameReady() {	Log.i("AGameView", "onGameReady" );	}
	@Override public void onGameStart() {	Log.i("AGameView", "onGameStart" );	}
	@Override public void onGameResume(){ Log.i("AGameView", "onGameResume");	}
	@Override public void onGamePause() {	Log.i("AGameView", "onGamePause" );	}
	@Override public void onGameOver()  {	Log.i("AGameView", "onGameOver"  );	}

	
//	Implement SurfaceHolder.Callback
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		AppManager.setThreadFlag(true);
		this.gameThread = new AGameViewThread(this.getHolder());
		this.gameThread.start();
	}
	@Override 
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}
	@Override 
	public void surfaceDestroyed(SurfaceHolder holder) {
		AppManager.setThreadFlag(false);
		boolean retry = true;
		while (retry) {
			try {
				this.gameThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		} Log.d("AGameView", "Thread has been shut down.");
	}
}
