package game.dodge.sub;

import game.dodge.unit.CUnitFactory;
import game.dodge.unit.CUnitTypePlayer;
import game.main.R;

import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.resource.DrawableObj;
import org.pjhjohn.framework.resource.Drawable;
import org.pjhjohn.framework.sub.AUnitController;
import org.pjhjohn.framework.sub.IController;

import android.graphics.Canvas;
import android.view.MotionEvent;
import app.main.AppOption;

public class CControllerJoystic extends AUnitController {
	private static IController singleton = new CControllerJoystic();
	public static IController getInstance(){ return singleton; }
	
	private DrawableObj controlBackground = new DrawableObj(); 
	private DrawableObj controlHandle = new DrawableObj();
	private DrawableObj eventDummy = new DrawableObj();
	
	private final float controlX;
	private final float controlY;
	private final float controllerRadius;
	private boolean isControllerActive;
	
	private CControllerJoystic() {
		super(CUnitFactory.getInstance().create(CUnitTypePlayer.getInstance()));
		super.sensitivity = (AppOption.Dodge.Sensitivity.JOYSTIC_MIN + AppOption.Dodge.Sensitivity.JOYSTIC_MAX)/2;
		controlBackground.setBitmap(AppManager.getBitmap(R.drawable.joystic_background));
		controlHandle.setBitmap(AppManager.getBitmap(R.drawable.joystic_handle));
		controllerRadius = controlBackground.getBitmap().getWidth()/2;
		controlX = AppManager.getDeviceWidth()  - AppOption.Dodge.PADDING_JOYSTIC - controllerRadius;
		controlY = AppManager.getDeviceHeight() - AppOption.Dodge.PADDING_JOYSTIC - controllerRadius;
		controlBackground.setPosition(controlX, controlY);
		controlHandle.setPosition(controlX, controlY);
	}

	@Override
	public boolean update(MotionEvent event) {
		this.eventDummy.setPosition(event.getX(), event.getY());
		float distance = controlBackground.distanceTo(eventDummy);
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(distance < controllerRadius) isControllerActive = true;
			break;
		case MotionEvent.ACTION_MOVE:
			if(!isControllerActive) break;
			controlHandle.setX(distance<controllerRadius? event.getX() : controlX + controllerRadius * (event.getX() - controlX)/controlBackground.distanceTo(eventDummy));
			controlHandle.setY(distance<controllerRadius? event.getY() : controlY + controllerRadius * (event.getY() - controlY)/controlBackground.distanceTo(eventDummy));
			controllee.setSpeed(super.sensitivity*(controlHandle.getX() - controlX), super.sensitivity*(controlHandle.getY() - controlY));
			break;
		case MotionEvent.ACTION_UP:
			isControllerActive = false;
			controlHandle.setPosition(controlX, controlY);
			controllee.setAcc(-controllee.getSpeedX()/10, -controllee.getSpeedY()/10);
		} return true;
	}
	@Override
	public void draw(Canvas canvas){
		controlBackground.draw(canvas, Drawable.Align.CENTER);
		controlHandle.draw(canvas, Drawable.Align.CENTER);
	}
	public void init(){
		controlHandle.setPosition(controlX, controlY);
	}
	@Override public void setSensitivity(int _current, int _max) {
		super.sensitivity = AppOption.Dodge.Sensitivity.JOYSTIC_MIN + (AppOption.Dodge.Sensitivity.JOYSTIC_MAX - AppOption.Dodge.Sensitivity.JOYSTIC_MIN) * (float)_current / (float)_max;
	}
	@Override public float getProgressRatio() {
		return (super.sensitivity - AppOption.Dodge.Sensitivity.JOYSTIC_MIN) / (AppOption.Dodge.Sensitivity.JOYSTIC_MAX - AppOption.Dodge.Sensitivity.JOYSTIC_MIN);
	}
	@Override public float getSensitivity() {
		return super.sensitivity;
	}
	@Override public void setDefaultSensitivity() {
		super.sensitivity = (AppOption.Dodge.Sensitivity.JOYSTIC_MIN + AppOption.Dodge.Sensitivity.JOYSTIC_MAX)/2;
	}
}
