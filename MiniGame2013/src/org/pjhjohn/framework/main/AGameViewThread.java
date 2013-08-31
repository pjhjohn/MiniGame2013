package org.pjhjohn.framework.main;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class AGameViewThread extends Thread{ 
	private SurfaceHolder holder;
	private final static int FRAME_PERIOD = 1000 / AppManager.MAX_FPS;		
	
	public AGameViewThread(SurfaceHolder holder){
		this.holder = holder;
	}
	
	public void run() {
		Canvas canvas = null;
		long tBegin, tDiff;
		int tSleep = 0, numOfSkippedFrame; 							// ms to sleep (<0 if we're behind)
		while (AppManager.isGameThreadActive()) {
			if(AppManager.getState()==null) AppManager.setThreadFlag(false);
			else AppManager.getState().update(); 
			try {													// Try locking the canvas for exclusive pixel editing in the surface
				canvas = holder.lockCanvas(null);
				synchronized (holder) {
					tBegin = System.currentTimeMillis();
					numOfSkippedFrame = 0;							// Reset frames skipped
					AppManager.getState().drawSurface(canvas);		// Render : Draws the canvas on the panel		
					tDiff = System.currentTimeMillis() - tBegin;	// Calculate Cycle Length
					tSleep = FRAME_PERIOD - (int)tDiff;				// calculate sleep time
					if (tSleep > 0) {								// if sleepTime > 0 we're OK
						try {
							Thread.sleep(tSleep);					// Sleep Thread for a short period very useful for battery saving	
						} catch (InterruptedException e) {
							//Log.e("AGameViewThread", e.getStackTrace().toString() + "\n" + e.getMessage());
						}
					}					
					while (tSleep < 0 && numOfSkippedFrame < AppManager.MAX_FRAME_SKIPS) { // Need to catch up update without rendering
						AppManager.getState().update();
						tSleep += FRAME_PERIOD; 					// add frame period to check if in next frame	
						numOfSkippedFrame++;
					}
				}
			} catch (Exception e){
				//Log.e("AGameViewThread", e.getStackTrace().toString() + "\n" + e.getMessage());
			} finally {
				if(canvas!=null) holder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
