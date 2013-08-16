package org.pjhjohn.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CustomViewSurfaceContainer extends SurfaceView implements Callback {
	private CustomViewSurfaceThread thread;
	private CustomView[] views;

	public CustomViewSurfaceContainer(Context context, CustomView[] customviews) {
		super(context);
		this.views = customviews;
		this.getHolder().addCallback(this);
	}
	
	public void update(){
		for(int i=0; i<views.length; i++) views[i].update();
	}
	@Override
	public void onDraw(Canvas canvas){
		for(int i=0; i<views.length; i++) views[i].draw(canvas);
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread = new CustomViewSurfaceThread(getHolder(), this);
		this.thread.setThreadEnable(true);
		this.thread.start();	
	}
	@Override 
	public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2, int arg3) {}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		this.thread.setThreadEnable(false);
		while(retry){
			try{
				this.thread.join();
				retry = false;
			} catch(InterruptedException e){
				
			}
		}
	}
	
	// Inner Class : FULLY DEPENDENT TO SurfaceContainer. (No other class can access)
	private class CustomViewSurfaceThread extends Thread {
		private CustomViewSurfaceContainer view;
		private SurfaceHolder surfaceHolder;
		private boolean threadEnable;
		public CustomViewSurfaceThread(SurfaceHolder _surfaceHolder, CustomViewSurfaceContainer surfaceContainer){
			this.surfaceHolder = _surfaceHolder;
			this.view = surfaceContainer;
			Log.i("pjhjohnThread","SurfaceThreadCreated");
		}
		public void setThreadEnable(boolean enable){
			this.threadEnable = enable;
		}
		@Override
		public void run(){
			Log.i("pjhjohnThread","SurfaceThreadStart");
			Canvas canvas = null;
			while(this.threadEnable){
				view.update();
				try {
					canvas = surfaceHolder.lockCanvas(null);
					synchronized (surfaceHolder) {
						view.onDraw(canvas);
					}
				} catch (Exception e){
				} finally {
					if(canvas!=null) surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			Log.i("pjhjohnThread","SurfaceThreadStop");
		}
	}
}