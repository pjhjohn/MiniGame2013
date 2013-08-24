package game.dodge.unit;

import org.pjhjohn.framework.manager.AppManager;
import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IUnit;

public class CUnitPlayer extends AUnit{
	private static AUnit singleton = new CUnitPlayer();
	private CUnitPlayer() {}
	public static AUnit getInstance(){ return singleton; }
	@Override 
	public void setPosition(float x, float y){
		if(x < 0) this.x = 0;
		else if(x > AppManager.getDeviceWidth()) this.x = AppManager.getDeviceWidth();
		else this.x = x;
		
		if(y < 0) this.y = 0;
		else if(y > AppManager.getDeviceHeight()) this.y = AppManager.getDeviceHeight();
		else this.y = y;
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.distanceTo((AUnit)_target) < ((this.bmp.getWidth() + ((AUnit) _target).getBitmap().getWidth())/2) - 10 ;
	}
}
