package game.dodge.unit;

import org.pjhjohn.framework.resource.AUnit;
import org.pjhjohn.framework.resource.IUnit;

import app.main.AppOption;

public class CUnitGuidedAsteroid extends AUnit {
	private AUnit player;
	public CUnitGuidedAsteroid() {
		this.player = CUnitFactory.getInstance().create(CUnitTypePlayer.getInstance());
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.distanceTo((AUnit)_target) < ((this.bmp.getWidth() + ((AUnit)_target).getBitmap().getWidth())/2) - 2 ;
	}
	@Override
	public void update() {
		super.setPosition(this.x+this.speedX, this.y+this.speedY);
		// Apply Move and find next speed
		float dist_x = player.getX() - this.x;
		float dist_y = player.getY() - this.y;
		float dist = this.distanceTo(player);
		super.setSpeedX(AppOption.Dodge.GUIDED_ASTEROID_SPEED * dist_x / dist);
		super.setSpeedY(AppOption.Dodge.GUIDED_ASTEROID_SPEED * dist_y / dist);
	}
	// Disable Methods those must not triggered
	@Override public void setPosition(float x, float y){}
	@Override public void setSpeedX(float _dx){}
	@Override public void setSpeedY(float _dy){}
}
