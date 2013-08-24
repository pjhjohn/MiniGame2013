package org.pjhjohn.framework.animatable;

import org.pjhjohn.framework.drawable.DrawableObj;

/*
 * Just add update & draw method : 
 * so that SurfaceContainer has sub-classes of THIS. 
 * 						&
 * SurfaceThread controls these Classes through SurfaceContainer.
 */
public abstract class AnimatableObj extends DrawableObj implements IAnimatable {
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
