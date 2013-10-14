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
	private static Matrix m = new Matrix();
	private static PointF cent = new PointF();
	private static Bitmap b;
	@Override
	public boolean isCrashed(IUnit _target) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void rotate(float dx, float dy){
		if(b==null)
			b= Bitmap.createScaledBitmap(AppManager.getBitmap(R.drawable.cannon), (int)BUnitBall.getRadius()*2, (int)BUnitBall.getRadius()*2, true);
		float angle = (float) Math.atan2((double)(dy - cent.y),(double)(dx - cent.x));
		angle=(float) (angle/Math.PI*180);
		Log.v("BUnitPlayer", "angle"+angle);
		m.setRotate(angle, (float)b.getWidth()/2, (float)b.getHeight()/2);
		this.setBitmap(Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m ,true));
	}
	public void setCent(float x, float y){ cent.set(x, y); }
}
