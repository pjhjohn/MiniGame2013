package game.bubble.unit;

import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IUnit;

import android.util.Log;

public class BUnitPlayer extends AUnit {
	private static AUnit singleton = new BUnitPlayer();
	private BUnitPlayer() {}
	public static AUnit getInstance(){
		Log.i("hi", "hihiihiihhihi");
		return singleton; }

	@Override
	public boolean isCrashed(IUnit _target) {
		// TODO Auto-generated method stub
		return false;
	}

}
