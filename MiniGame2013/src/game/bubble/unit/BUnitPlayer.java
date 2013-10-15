package game.bubble.unit;

import game.main.R;

import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.resource.AUnit;
import org.pjhjohn.framework.resource.IUnit;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Log;

public class BUnitPlayer extends AUnit implements IUnitPlayer{
	private static AUnit singleton = new BUnitPlayer();
	private BUnitPlayer() {}
	public static AUnit getInstance(){	return singleton; }
	private static Matrix matrix = new Matrix();
	private static PointF center = new PointF();
	private static Bitmap bmp;
	@Override
	public boolean isCrashed(IUnit _target) {
		return false;
	}
	
	public void rotate(float dx, float dy){
		if(bmp==null) bmp= Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.cannon), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true);
		float angle = (float) Math.atan2((double)(dy - center.y),(double)(dx - center.x));
		angle=(float) (angle/Math.PI*180);
		Log.v("BUnitPlayer", "angle"+angle);
		matrix.setRotate(angle, (float)bmp.getWidth()/2, (float)bmp.getHeight()/2);
		this.setBitmap(Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix ,true));
	}
	public void setCent(float x, float y){
		center.set(x, y);
	}
}
