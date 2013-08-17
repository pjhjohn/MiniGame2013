package game.dodge.unit;

import org.pjhjohn.framework.unit.IUnitType;

public class CUnitTypeAsteroid implements IUnitType{
	private static IUnitType singleton;
	private CUnitTypeAsteroid(){
	}
	public static IUnitType getInstance(){
		if(singleton == null) singleton = new CUnitTypeAsteroid();
		return (IUnitType)CUnitTypeAsteroid.singleton; 
	}
}