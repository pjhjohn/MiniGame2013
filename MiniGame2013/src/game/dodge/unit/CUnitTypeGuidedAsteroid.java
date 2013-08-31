package game.dodge.unit;

import org.pjhjohn.framework.resource.IUnitType;

public class CUnitTypeGuidedAsteroid implements IUnitType {
	private static IUnitType singleton;
	private CUnitTypeGuidedAsteroid(){
	}
	public static IUnitType getInstance(){
		if(singleton == null) singleton = new CUnitTypeGuidedAsteroid();
		return (IUnitType)CUnitTypeGuidedAsteroid.singleton; 
	}
}