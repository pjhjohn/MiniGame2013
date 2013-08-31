package org.pjhjohn.framework.resource;


/*
 * Just add update & draw method : 
 * so that SurfaceContainer has sub-classes of THIS. 
 * 						&
 * SurfaceThread controls these Classes through SurfaceContainer.
 */
public abstract class AnimatableObj extends DrawableObj implements Animatable {
	protected boolean isRunning;
	public AnimatableObj(){
		super();
		this.isRunning = false;
	}
	@Override
	public void start() {
		this.isRunning = true;
	}
	@Override
	public void stop() {
		this.isRunning = false;
	}
	@Override
	public boolean isRunning() {
		return this.isRunning;
	}
}
