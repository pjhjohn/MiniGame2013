package game.bubble.unit;

import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.resource.AUnit;
import org.pjhjohn.framework.resource.IUnit;
import org.pjhjohn.framework.resource.IUnitType;

import android.util.Log;

public class BUnitBall extends AUnit implements IUnitBall{
	private IUnitType btype;
	private boolean moving=false;//Ã³À½¿¡ ½ò¶§ true
	private boolean RU=false, LU=false, RR=false, LL=false, RD=false, LD=false;
	private static float radius = AppManager.getDeviceWidth()/16;
	public BUnitBall(IUnitType _balltype) {
		// TODO Auto-generated constructor stub
		btype = _balltype; 
	}
	public IUnitType getBallType() { return btype; }
	@Override
	public boolean isCrashed(IUnit _target) {
		// TODO Auto-generated method stub
		return false;
	}
	public static float getRadius() { return radius; }
	public void setRU(boolean _ru){ RU=_ru; }
	public void setLU(boolean _lu){ LU=_lu; }
	public void setRR(boolean _rr){ RR=_rr; }
	public void setLL(boolean _ll){ LL=_ll; }
	public void setRD(boolean _rd){ RD=_rd; }
	public void setLD(boolean _ld){ LD=_ld; }
	public boolean getRU(){ return RU; }
	public boolean getLU(){ return LU; }
	public boolean getRR(){ return RR; }
	public boolean getLL(){ return LL; }
	public boolean getRD(){ return RD; }
	public boolean getLD(){ return LD; }
	public void setMoving(boolean _mv){ moving = _mv; }
	public boolean getMoving() { return moving; }
	public void shooting(float x, float y){
		float centx = AppManager.getDeviceWidth()/2;
		float centy = (AppManager.getDeviceHeight()*11)/12;
		this.setPosition(centx, centy);
		float proportion = (float)(50/Math.sqrt(Math.pow(x-centx,2) + Math.pow(y-centy,2)));
		this.setSpeed((x-centx)*proportion, (y-centy)*proportion);
	}
}
