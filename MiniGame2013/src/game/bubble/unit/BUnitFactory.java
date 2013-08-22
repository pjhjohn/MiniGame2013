package game.bubble.unit;

import java.util.Random;

import game.main.R;

import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.manager.AppManager;

public class BUnitFactory{
	public static enum UnitType { BPLAYER, RBALL, GBALL, BBALL, RAND }
	private static BUnitFactory singleton = new BUnitFactory();
	public BUnitFactory() {}
	public static BUnitFactory getInstance(){ return singleton; }
	private Random rand = new Random();
	public AUnit create(UnitType type) {
		AUnit rtn_unit = null;
			
		switch(type){
		case BPLAYER : 
			rtn_unit = BUnitPlayer.getInstance();
			if(rtn_unit.getBitmap()==null) rtn_unit.setBitmap(AppManager.getBitmap(R.drawable.cannon));//그림 수정하기
			break;
		case RAND : 
			switch(rand.nextInt(2)){
			case 0 :
				type = UnitType.RBALL;
				break;
			case 1 :
				type = UnitType.GBALL;
				break;
			case 2 :
				type = UnitType.BBALL;
				break;
			}
		case RBALL : 
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(AppManager.getBitmap(R.drawable.redball));
			break;
		case GBALL :
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(AppManager.getBitmap(R.drawable.greenball));
			break;
		case BBALL : 
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(AppManager.getBitmap(R.drawable.blueball));
		}
		return rtn_unit;
	}
}
