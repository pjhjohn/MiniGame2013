package game.dodge.unit;

import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.resource.AUnit;
import org.pjhjohn.framework.resource.IUnit;

public class CUnitAsteroid extends AUnit {
	@Override 
	public void setPosition(float x, float y){
		if(x < 0) this.x = x + AppManager.getDeviceWidth();
		else if(x <= AppManager.getDeviceWidth()) this.x = x;
		else this.x = x - AppManager.getDeviceWidth();

		if(y < 0) this.y = y + AppManager.getDeviceHeight();
		else if(y <= AppManager.getDeviceHeight()) this.y = y;
		else this.y = y - AppManager.getDeviceHeight();
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.distanceTo((AUnit)_target) < ((this.bmp.getWidth() + ((AUnit)_target).getBitmap().getWidth())/2) - 2 ;
	}
}
