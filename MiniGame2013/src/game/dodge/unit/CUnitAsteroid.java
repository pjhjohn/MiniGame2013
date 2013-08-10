package game.dodge.unit;

import org.pjhjohn.framework.Option;
import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IUnit;

public class CUnitAsteroid extends AUnit {
	@Override 
	public void setPosition(float x, float y){
		if(x < 0) this.posX = x + Option.getDeviceWidth();
		else if(x <= Option.getDeviceWidth()) this.posX = x;
		else this.posX = x - Option.getDeviceWidth();

		if(y < 0) this.posY = y + Option.getDeviceHeight();
		else if(y <= Option.getDeviceHeight()) this.posY = y;
		else this.posY = y - Option.getDeviceHeight();
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.getDistance((AUnit)_target) < ((this.bitmap.getWidth() + ((AUnit)_target).getBitmap().getWidth())/2) - 2 ;
	}
}
