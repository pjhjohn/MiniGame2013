package game.bubble.unit;

import java.util.Random;

import game.main.R;

import org.pjhjohn.framework.ApplicationManager;
import org.pjhjohn.framework.unit.AUnit;

public class BUnitFactory {
	public static enum UnitType { BPLAYER, RBALL, GBALL, BBALL, RAND }
	private static BUnitFactory singleton = new BUnitFactory();
	public BUnitFactory() {}
	public static BUnitFactory getInstance(){ return singleton; }
	private Random rand = new Random();
	private int temp;
	public AUnit create(UnitType type) {
		AUnit rtn_unit = null;
			
		switch(type){
		case BPLAYER : 
			rtn_unit = BUnitPlayer.getInstance();
			if(rtn_unit.getBitmap()==null) rtn_unit.setBitmap(ApplicationManager.getBitmap(R.drawable.ship));//그림 수정하기
			break;
		case RAND : 
			temp = rand.nextInt(2);
			if(temp == 0)
				type = UnitType.RBALL;
			else if(temp == 1)
				type = UnitType.GBALL;
			else
				type = UnitType.BBALL;
		case RBALL : 
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(ApplicationManager.getBitmap(R.drawable.redball));
			break;
		case GBALL :
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(ApplicationManager.getBitmap(R.drawable.greenball));
			break;
		case BBALL : 
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(ApplicationManager.getBitmap(R.drawable.blueball));
		}
		return rtn_unit;
	}
	
}
