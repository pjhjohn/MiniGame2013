package game.bubble.unit;

import org.pjhjohn.framework.unit.IUnitType;

public class BUnitTypeGreenBall implements IUnitType{
	private static IUnitType singleton = new BUnitTypeGreenBall();
	private BUnitTypeGreenBall(){	}
	public static IUnitType getInstance(){	return singleton;	}
}
