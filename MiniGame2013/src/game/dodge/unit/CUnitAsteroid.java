package game.dodge.unit;

import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IUnit;
import org.pjhjohn.manager.AppManager;

public class CUnitAsteroid extends AUnit {
	@Override 
	public void setPosition(float x, float y){
		if(x < 0) this.posX = x + AppManager.getDeviceWidth();
		else if(x <= AppManager.getDeviceWidth()) this.posX = x;
		else this.posX = x - AppManager.getDeviceWidth();

		if(y < 0) this.posY = y + AppManager.getDeviceHeight();
		else if(y <= AppManager.getDeviceHeight()) this.posY = y;
		else this.posY = y - AppManager.getDeviceHeight();
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.getDistance((AUnit)_target) < ((this.bitmap.getWidth() + ((AUnit)_target).getBitmap().getWidth())/2) - 2 ;
	}
}
