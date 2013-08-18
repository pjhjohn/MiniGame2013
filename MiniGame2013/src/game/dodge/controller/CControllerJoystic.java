package game.dodge.controller;

import game.main.R;

import org.pjhjohn.framework.controller.AController;
import org.pjhjohn.framework.controller.IController;
import org.pjhjohn.framework.obj2d.ImageObj;
import org.pjhjohn.manager.AppManager;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import app.main.AppOption;

public class CControllerJoystic extends AController {
	private static IController singleton = new CControllerJoystic();
	public static IController getInstance(){ return singleton; }
	
	private ImageObj controller_background = new ImageObj(); 
	private ImageObj controller_handle = new ImageObj();
	private ImageObj event_dummy = new ImageObj();
	
	private final float controller_x;
	private final float controller_y;
	private final float controller_background_radius;
	private final float controller_handle_radius;
	private boolean iscontrolActive;
	
	private CControllerJoystic() {
		super();
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
	public boolean onTouch(View v, MotionEvent event) {
		this.event_dummy.setPosition(event.getX(), event.getY());
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(controller_background.getDistance(event_dummy) < controller_background_radius) iscontrolActive = true;
			break;
		case MotionEvent.ACTION_MOVE:
			if(iscontrolActive){
				if(controller_background.getDistance(event_dummy) < controller_handle_radius)	
					controller_handle.setPosition(event.getX(), event.getY());
				else controller_handle.setPosition(
						controller_background.getX() + controller_background_radius * (event.getX() - controller_background.getX())/controller_background.getDistance(event_dummy),
						controller_background.getY() + controller_background_radius * (event.getY() - controller_background.getY())/controller_background.getDistance(event_dummy)
						);				
				player.setSpeedX(super.sensitivity*(controller_handle.getX() - controller_background.getX()));
				player.setSpeedY(super.sensitivity*(controller_handle.getY() - controller_background.getY()));
			}
			break;
		case MotionEvent.ACTION_UP:
			iscontrolActive = false;
			controller_handle.setPosition(controller_x, controller_y);
			player.setAccX(-player.getSpeedX()/10);
			player.setAccY(-player.getSpeedY()/10);
		}
		return true;
	}
	@Override
	public void draw(Canvas canvas){
		controller_background.draw(canvas, ImageObj.Align.CENTER);
		controller_handle.draw(canvas, ImageObj.Align.CENTER);
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
