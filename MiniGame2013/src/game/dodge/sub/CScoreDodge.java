package game.dodge.sub;

import org.pjhjohn.framework.sub.AScore;
import org.pjhjohn.framework.sub.IScore;

public class CScoreDodge extends AScore {
	private static IScore singleton = new CScoreDodge();
	private CScoreDodge() {
		super();
	}
	public static IScore getInstance(){
		return singleton;
	}

	@Override
	public long timeScore(long time) {
		return time / 100;
	}
	
	@Override
	public void increase(long scoreEarned) {
	
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.get());
	}
}
