package game.bubble.unit;

import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IUnit;

public class BUnitPlayer extends AUnit {
	private static AUnit singleton = new BUnitPlayer();
	private BUnitPlayer() {}
	public static AUnit getInstance(){ return singleton; }

	@Override
	public boolean isCrashed(IUnit _target) {
		// TODO Auto-generated method stub
		return false;
	}

}
