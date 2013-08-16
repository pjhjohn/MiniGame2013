package game.dodge.unit;

import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IUnit;

import app.main.AppOption;

public class CUnitAsteroid extends AUnit {
	@Override 
	public void setPosition(float x, float y){
		if(x < 0) this.posX = x + AppOption.getDeviceWidth();
		else if(x <= AppOption.getDeviceWidth()) this.posX = x;
		else this.posX = x - AppOption.getDeviceWidth();

		if(y < 0) this.posY = y + AppOption.getDeviceHeight();
		else if(y <= AppOption.getDeviceHeight()) this.posY = y;
		else this.posY = y - AppOption.getDeviceHeight();
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.getDistance((AUnit)_target) < ((this.bitmap.getWidth() + ((AUnit)_target).getBitmap().getWidth())/2) - 2 ;
	}
}
