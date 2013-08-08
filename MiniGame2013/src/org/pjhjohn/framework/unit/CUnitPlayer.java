package org.pjhjohn.framework.unit;

import org.pjhjohn.framework.ApplicationOption;

public class CUnitPlayer extends AUnit{
	private static AUnit singleton = new CUnitPlayer();
	private CUnitPlayer() {}
	public static AUnit getInstance(){ return singleton; }
	@Override 
	public void setPosition(float x, float y){
		if(x < 0) this.x = 0;
		else if(x > ApplicationOption.getDeviceWidth()) this.x = ApplicationOption.getDeviceWidth();
		else this.x = x;
		
		if(y < 0) this.y = 0;
		else if(y > ApplicationOption.getDeviceHeight()) this.y = ApplicationOption.getDeviceHeight();
		else this.y = y;
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.getDistance((AUnit)_target) < ((this.bitmap.getWidth() + ((AUnit) _target).getBitmap().getWidth())/2) - 10 ;
	}
}
