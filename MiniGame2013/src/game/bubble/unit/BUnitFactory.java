package game.bubble.unit;

import java.util.Random;

import game.main.R;

import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.manager.AppManager;

import android.graphics.Bitmap;


public class BUnitFactory{
	public static enum UnitType { BPLAYER, RBALL, GBALL, BBALL, RAND }
	private static BUnitFactory singleton = new BUnitFactory();
	public BUnitFactory() {}
	public static BUnitFactory getInstance(){ return singleton; }
	private Random rand = new Random();
	private int k;
	public AUnit create(UnitType type) {
		AUnit rtn_unit = null;
			
		switch(type){
		case BPLAYER : 
			rtn_unit = BUnitPlayer.getInstance();
			if(rtn_unit.getBitmap()==null) rtn_unit.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.cannon), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));//그림 수정하기
			break;
		case RAND : 
			k = rand.nextInt(3);
			if(k==0) return this.create(UnitType.RBALL);
			else if (k==1) return this.create(UnitType.GBALL);
			else return this.create(UnitType.BBALL);
		case RBALL : 
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.redball), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));
			break;
		case GBALL :;
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.greenball), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));
			break;
		case BBALL : 
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.blueball), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));
		}
		return rtn_unit;
	}
}
