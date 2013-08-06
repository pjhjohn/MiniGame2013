package org.pjhjohn.framework.unit;

import org.pjhjohn.framework.ApplicationOption;

public class CUnitGuidedAsteroid extends AUnit {
	private AUnit player;
	public CUnitGuidedAsteroid() {
		this.player = CUnitFactory.getInstance().create(CUnitFactory.UnitType.PLAYER);
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.getDistance((AUnit)_target) < ((this.bitmap.getWidth() + ((AUnit)_target).getBitmap().getWidth())/2) - 2 ;
	}
	@Override
	public void move() {
		super.setPosition(this.x+this.sx, this.y+this.sy);
		// Apply Move and find next speed
		float dist_x = player.getX() - this.x;
		float dist_y = player.getY() - this.y;
		float dist = this.getDistance(player);
		super.setSpeedX(ApplicationOption.GUIDED_ASTEROID_SPEED * dist_x / dist);
		super.setSpeedY(ApplicationOption.GUIDED_ASTEROID_SPEED * dist_y / dist);
	}
	// Disable Methods those must not triggered
	@Override public void setPosition(float x, float y){}
	@Override public void setSpeedX(float _dx){}
	@Override public void setSpeedY(float _dy){}
}
