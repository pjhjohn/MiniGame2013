package game.dodge.controller;

import game.dodge.unit.CUnitFactory;
import game.dodge.unit.CUnitTypePlayer;
import game.main.R;

import org.pjhjohn.framework.controller.AUnitController;
import org.pjhjohn.framework.controller.IController;
import org.pjhjohn.framework.drawable.DrawableObj;
import org.pjhjohn.framework.drawable.IDrawable;
import org.pjhjohn.framework.manager.AppManager;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import app.main.AppOption;

public class CControllerJoystic extends AUnitController {
	private static IController singleton = new CControllerJoystic();
	public static IController getInstance(){ return singleton; }
	
	private DrawableObj controller_background = new DrawableObj(); 
	private DrawableObj controller_handle = new DrawableObj();
	private DrawableObj event_dummy = new DrawableObj();
	
	private final float controller_x;
	private final float controller_y;
	private final float controller_background_radius;
	private final float controller_handle_radius;
	private boolean iscontrolActive;
	
	private CControllerJoystic() {
		super();
		super.controllee = CUnitFactory.getInstance().create(CUnitTypePlayer.getInstance());
		super.sensitivity = (AppOption.Dodge.Sensitivity.JOYSTIC_MIN + AppOption.Dodge.Sensitivity.JOYSTIC_MAX)/2;
		controller_background.setBitmap(AppManager.getBitmap(R.drawable.joystic_background));
		controller_handle.setBitmap(AppManager.getBitmap(R.drawable.joystic_handle));
		controller_background_radius = controller_background.getBitmap().getWidth()/2;
		controller_handle_radius = controller_handle.getBitmap().getWidth()/2;
		controller_x = AppManager.getDeviceWidth() - AppOption.Dodge.PADDING_JOYSTIC - controller_background_radius;
		controller_y = AppManager.getDeviceHeight() - AppOption.Dodge.PADDING_JOYSTIC - controller_background_radius;
		
		controller_background.setPosition(controller_x, controller_y);
		controller_handle.setPosition(controller_x, controller_y);
	}

	@Override
	public boolean update(MotionEvent event) {
		this.event_dummy.setPosition(event.getX(), event.getY());
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(controller_background.distanceTo(event_dummy) < controller_background_radius) iscontrolActive = true;
			break;
		case MotionEvent.ACTION_MOVE:
			if(iscontrolActive){
				if(controller_background.distanceTo(event_dummy) < controller_handle_radius)	
					controller_handle.setPosition(event.getX(), event.getY());
				else controller_handle.setPosition(
						controller_background.getX() + controller_background_radius * (event.getX() - controller_background.getX())/controller_background.distanceTo(event_dummy),
						controller_background.getY() + controller_background_radius * (event.getY() - controller_background.getY())/controller_background.distanceTo(event_dummy)
						);				
				controllee.setSpeedX(super.sensitivity*(controller_handle.getX() - controller_background.getX()));
				controllee.setSpeedY(super.sensitivity*(controller_handle.getY() - controller_background.getY()));
			}
			break;
		case MotionEvent.ACTION_UP:
			iscontrolActive = false;
			controller_handle.setPosition(controller_x, controller_y);
			controllee.setAccX(-controllee.getSpeedX()/10);
			controllee.setAccY(-controllee.getSpeedY()/10);
		}
		return true;
	}
	@Override
	public void draw(Canvas canvas){
		controller_background.draw(canvas, IDrawable.Align.CENTER);
		controller_handle.draw(canvas, IDrawable.Align.CENTER);
	}
	public void init(){
		controller_handle.setPosition(controller_x, controller_y);
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
