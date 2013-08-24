package game.bubble.unit;

import org.pjhjohn.framework.unit.IUnitType;

public class BUnitTypePlayer implements IUnitType{
	private static IUnitType singleton = new BUnitTypePlayer();
	private BUnitTypePlayer(){	}
	public static IUnitType getInstance(){	return singleton;	}
}
