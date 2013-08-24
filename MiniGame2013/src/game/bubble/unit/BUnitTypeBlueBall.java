package game.bubble.unit;

import org.pjhjohn.framework.unit.IUnitType;

public class BUnitTypeBlueBall implements IUnitType{
	private static IUnitType singleton = new BUnitTypeBlueBall();
	private BUnitTypeBlueBall(){	}
	public static IUnitType getInstance(){	return singleton;	}
}
