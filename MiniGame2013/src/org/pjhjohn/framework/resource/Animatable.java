package org.pjhjohn.framework.resource;

/* [pjhjohn]
 * Enables Object to Animate.
 * update() : update current data per frame.
 * start()  : start animation
 * stop()   : stop animation
 * isRunning() : returns whether current animation is running.
 */
public interface Animatable {
	public void update();
	public void start();
	public void stop();
	public boolean isRunning();
}
