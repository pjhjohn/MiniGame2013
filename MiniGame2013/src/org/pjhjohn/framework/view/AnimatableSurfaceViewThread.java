package org.pjhjohn.framework.view;

import org.pjhjohn.framework.manager.AppManager;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class AnimatableSurfaceViewThread extends Thread {
	private AnimatableSurfaceView view;
	private SurfaceHolder holder;
	private boolean threadEnable;
	private final static int FRAME_PERIOD = 1000 / AppManager.MAX_FPS;
	public AnimatableSurfaceViewThread(SurfaceHolder holder, AnimatableSurfaceView surfaceContainer){
		this.holder = holder;
		this.view = surfaceContainer;
		this.threadEnable = false;
	}
	public void setThreadEnable(boolean enable){
		this.threadEnable = enable;
	}
	@Override
	public void run() {
		Canvas canvas = null;
		long tBegin, tDiff;
		int tSleep = 0, numOfSkippedFrame;
		while (this.threadEnable) {
			Log.v("AnimatableSurfaceViewThread", "Before Update");
			view.update();
			Log.v("AnimatableSurfaceViewThread", "After Update");
			try {
				canvas = holder.lockCanvas(null);
				synchronized (holder) {
					tBegin = System.currentTimeMillis();
					numOfSkippedFrame = 0;
					Log.v("AnimatableSurfaceViewThread", "Before Draw");
					view.draw(canvas);
					Log.v("AnimatableSurfaceViewThread", "After Draw");
					tDiff = System.currentTimeMillis() - tBegin;
					tSleep = FRAME_PERIOD - (int)tDiff;
					if (tSleep > 0) {
						try {
							Thread.sleep(tSleep);	
						} catch (InterruptedException e) {
							Log.e("AnimatableSurfaceViewThread", e.getMessage());
						}
					}					
					while (tSleep < 0 && numOfSkippedFrame < AppManager.MAX_FRAME_SKIPS) {
						Log.v("AnimatableSurfaceViewThread", "Before Update2");
						view.update();
						Log.v("AnimatableSurfaceViewThread", "After Update2");
						tSleep += FRAME_PERIOD;	
						numOfSkippedFrame++;
					}
				}
			} catch (Exception e){
				Log.e("AnimatableSurfaceViewThread", "Cause : " + e.getCause() + "\nMessage : " + e.getMessage());
			} finally {
				if(canvas!=null) holder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
