package game.dodge.unit;

import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IUnit;

import app.main.AppOption;

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
	public void update() {
		super.setPosition(this.posX+this.speedX, this.posY+this.speedY);
		// Apply Move and find next speed
		float dist_x = player.getX() - this.posX;
		float dist_y = player.getY() - this.posY;
		float dist = this.getDistance(player);
		super.setSpeedX(AppOption.Dodge.GUIDED_ASTEROID_SPEED * dist_x / dist);
		super.setSpeedY(AppOption.Dodge.GUIDED_ASTEROID_SPEED * dist_y / dist);
	}
	// Disable Methods those must not triggered
	@Override public void setPosition(float x, float y){}
	@Override public void setSpeedX(float _dx){}
	@Override public void setSpeedY(float _dy){}
}
