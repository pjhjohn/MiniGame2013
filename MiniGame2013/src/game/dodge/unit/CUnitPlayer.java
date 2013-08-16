package game.dodge.unit;

import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IUnit;

import app.main.AppOption;

public class CUnitPlayer extends AUnit{
	private static AUnit singleton = new CUnitPlayer();
	private CUnitPlayer() {}
	public static AUnit getInstance(){ return singleton; }
	@Override 
	public void setPosition(float x, float y){
		if(x < 0) this.posX = 0;
		else if(x > AppOption.getDeviceWidth()) this.posX = AppOption.getDeviceWidth();
		else this.posX = x;
		
		if(y < 0) this.posY = 0;
		else if(y > AppOption.getDeviceHeight()) this.posY = AppOption.getDeviceHeight();
		else this.posY = y;
	}
	@Override
	public boolean isCrashed(IUnit _target) {
		return this.getDistance((AUnit)_target) < ((this.bitmap.getWidth() + ((AUnit) _target).getBitmap().getWidth())/2) - 10 ;
	}
}
