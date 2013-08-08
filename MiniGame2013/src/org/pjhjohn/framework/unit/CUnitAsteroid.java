package org.pjhjohn.framework.unit;

import org.pjhjohn.framework.ApplicationOption;

public class CUnitAsteroid extends AUnit {
	@Override 
	public void setPosition(float x, float y){
		if(x < 0) this.x = x + ApplicationOption.getDeviceWidth();
		else if(x <= ApplicationOption.getDeviceWidth()) this.x = x;
		else this.x = x - ApplicationOption.getDeviceWidth();

		if(y < 0) this.y = y + ApplicationOption.getDeviceHeight();
		else if(y <= ApplicationOption.getDeviceHeight()) this.y = y;
		else this.y = y - ApplicationOption.getDeviceHeight();
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.getDistance((AUnit)_target) < ((this.bitmap.getWidth() + ((AUnit)_target).getBitmap().getWidth())/2) - 2 ;
	}
}
