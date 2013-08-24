package game.bubble.unit;

import org.pjhjohn.framework.unit.IUnitType;

public class BUnitTypeRedBall implements IUnitType{
	private static IUnitType singleton = new BUnitTypeRedBall();
	private BUnitTypeRedBall(){	}
	public static IUnitType getInstance(){	return singleton;	}
}
