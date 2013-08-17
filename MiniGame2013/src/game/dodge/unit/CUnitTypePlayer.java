package game.dodge.unit;

import org.pjhjohn.framework.unit.IUnitType;

public class CUnitTypePlayer implements IUnitType {
	private static IUnitType singleton;
	private CUnitTypePlayer(){
	}
	public static IUnitType getInstance(){
		if(singleton == null) singleton = new CUnitTypePlayer();
		return (IUnitType)CUnitTypePlayer.singleton; 
	}
}