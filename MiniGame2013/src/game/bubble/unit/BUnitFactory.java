package game.bubble.unit;

import game.main.R;

import java.util.Random;

import org.pjhjohn.framework.manager.AppManager;
import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IFactory;
import org.pjhjohn.framework.unit.IUnitType;

import android.graphics.Bitmap;


public class BUnitFactory implements IFactory{
	private static BUnitFactory singleton = new BUnitFactory();
	private BUnitFactory() {}
	public static BUnitFactory getInstance(){ return singleton; }
	private Random rand = new Random();
	private int k;
	public AUnit create(IUnitType type) {
		AUnit rtn_unit = null;
			
		if(type instanceof BUnitTypePlayer){
			rtn_unit = BUnitPlayer.getInstance();
			if(rtn_unit.getBitmap()==null) rtn_unit.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.cannon), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));//그림 수정하기
		
		}else if(type instanceof BUnitTypeRandBall){
			k = rand.nextInt(3);
			if(k==0) return this.create(BUnitTypeRedBall.getInstance());
			else if (k==1) return this.create(BUnitTypeGreenBall.getInstance());
			else return this.create(BUnitTypeBlueBall.getInstance());
		
		}else if(type instanceof BUnitTypeRedBall){
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.redball), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));
		
		}else if(type instanceof BUnitTypeGreenBall){
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.greenball), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));
		
		}else if(type instanceof BUnitTypeBlueBall){
			rtn_unit = new BUnitBall(type);
			rtn_unit.setBitmap(Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.blueball), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true));
		}
		return rtn_unit;
	}
}
