package org.pjhjohn.framework.main;

import android.graphics.Canvas;
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
			view.update();
			try {
				canvas = holder.lockCanvas(null);
				synchronized (holder) {
					tBegin = System.currentTimeMillis();
					numOfSkippedFrame = 0;
					view.draw(canvas);
					tDiff = System.currentTimeMillis() - tBegin;
					tSleep = FRAME_PERIOD - (int)tDiff;
					if (tSleep > 0) {
						try {
							Thread.sleep(tSleep);	
						} catch (InterruptedException e) {
							//Log.e("AnimatableSurfaceViewThread", e.getMessage());
						}
					}					
					while (tSleep < 0 && numOfSkippedFrame < AppManager.MAX_FRAME_SKIPS) {
						view.update();
						tSleep += FRAME_PERIOD;	
						numOfSkippedFrame++;
					}
				}
			} catch (Exception e){
				//Log.e("AnimatableSurfaceViewThread", "Cause : " + e.getCause() + "\nMessage : " + e.getMessage());
			} finally {
				if(canvas!=null) holder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
