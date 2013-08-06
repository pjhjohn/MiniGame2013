package org.pjhjohn.framework.unit;


import org.pjhjohn.framework.ApplicationManager;

import game.main.R;


public class CUnitFactory implements IFactory {
	public static enum UnitType {
		PLAYER, ASTEROID, GUIDED_ASTEROID
	}
	private static CUnitFactory singleton = new CUnitFactory();
	private CUnitFactory(){}
	public static CUnitFactory getInstance(){ return singleton; }
	
	@Override
	public AUnit create(UnitType type) {
		AUnit rtn_unit = null;
		switch(type){
		case PLAYER : 
			rtn_unit = CUnitPlayer.getInstance();
			if(rtn_unit.getBitmap()==null) rtn_unit.setBitmap(ApplicationManager.getBitmap(R.drawable.ship));
			break;
		case ASTEROID : 
			rtn_unit = new CUnitAsteroid();
			rtn_unit.setBitmap(ApplicationManager.getBitmap(R.drawable.asteroid_small));
			break;
		case GUIDED_ASTEROID :
			rtn_unit = new CUnitGuidedAsteroid();
			rtn_unit.setBitmap(ApplicationManager.getBitmap(R.drawable.guide_asteroid_small));
			break;
		}
		return rtn_unit;
	}
}
