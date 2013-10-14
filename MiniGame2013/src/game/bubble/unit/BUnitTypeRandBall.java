package game.bubble.unit;

import org.pjhjohn.framework.resource.IUnitType;

public class BUnitTypeRandBall implements IUnitType{
	private static IUnitType singleton = new BUnitTypeRandBall();
	private BUnitTypeRandBall(){	}
	public static IUnitType getInstance(){	return singleton;	}
}
