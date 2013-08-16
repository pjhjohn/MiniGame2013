package org.pjhjohn.framework.view;

import org.pjhjohn.manager.AppManager;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class AGameViewThread extends Thread{ 
	private SurfaceHolder holder;
	private final static int MAX_FPS = 50;	
	private final static int MAX_FRAME_SKIPS = 5;	
	private final static int FRAME_PERIOD = 1000 / MAX_FPS;		
	
	public AGameViewThread(SurfaceHolder holder){
		this.holder = holder;
	}
	
	public void run() {
		Canvas canvas = null;
		long beginTime, timeDiff;		// the time when the cycle begun
		int sleepTime = 0, framesSkipped;		// ms to sleep (<0 if we're behind)
		
		while (AppManager.isGameThreadActive()) {
			if(AppManager.getState()==null) AppManager.setThreadFlag(false);
			else AppManager.getState().update(); 					// update game state 
			// Try locking the canvas for exclusive pixel editing in the surface
			try {
				canvas = holder.lockCanvas(null);
				synchronized (holder) {
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;	// Reset frames skipped
					AppManager.getState().drawSurface(canvas);			// render state to the screen : draws the canvas on the panel		
					timeDiff = System.currentTimeMillis() - beginTime;	// calculate how long did the cycle take
					sleepTime = (int)(FRAME_PERIOD - timeDiff);			// calculate sleep time
					if (sleepTime > 0) {								// if sleepTime > 0 we're OK
						try {
							// send the thread to sleep for a short period very useful for battery saving
							Thread.sleep(sleepTime);	
						} catch (InterruptedException e) {}
					}					
					while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) { // Need to catch up update without rendering
						AppManager.getState().update();
						sleepTime += FRAME_PERIOD; 			// add frame period to check if in next frame	
						framesSkipped++;
					}
				}
			} catch (Exception e){
				Log.e("AGameViewThreadError", e.getStackTrace().toString() + "\n" + e.getMessage());
			} finally {
				if(canvas!=null) holder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
