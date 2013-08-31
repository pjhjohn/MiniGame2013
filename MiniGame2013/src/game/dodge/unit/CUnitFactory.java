package game.dodge.unit;


import game.main.R;

import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.resource.AUnit;
import org.pjhjohn.framework.resource.IFactory;
import org.pjhjohn.framework.resource.IUnitType;


public class CUnitFactory implements IFactory {
	private static IFactory singleton = new CUnitFactory();
	private CUnitFactory(){}
	public static IFactory getInstance(){ return singleton; }
	
	@Override
	public AUnit create(IUnitType type) {
		AUnit rtn_unit = null;
		if(type instanceof CUnitTypePlayer){
			rtn_unit = CUnitPlayer.getInstance();
			if(rtn_unit.getBitmap()==null) rtn_unit.setBitmap(AppManager.getBitmap(R.drawable.ship));
		} else if(type instanceof CUnitTypeAsteroid){
			rtn_unit = new CUnitAsteroid();
			rtn_unit.setBitmap(AppManager.getBitmap(R.drawable.asteroid_small));
		} else if(type instanceof CUnitTypeGuidedAsteroid){
			rtn_unit = new CUnitGuidedAsteroid();
			rtn_unit.setBitmap(AppManager.getBitmap(R.drawable.guide_asteroid_small));
		} return rtn_unit;
	}
}
