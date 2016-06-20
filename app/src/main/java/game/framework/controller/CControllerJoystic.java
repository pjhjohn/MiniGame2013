package game.framework.controller;

import game.framework.ApplicationManager;
import game.framework.ApplicationOption;
import game.framework.ImageObject;
import game.dodge.main.R;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class CControllerJoystic extends AController {
	private static IController singleton = new CControllerJoystic();
	public static IController getInstance(){ return singleton; }
	
	private ImageObject controller_background = new ImageObject(); 
	private ImageObject controller_handle = new ImageObject();
	private ImageObject event_dummy = new ImageObject();
	
	private final float controller_x;
	private final float controller_y;
	private final float controller_background_radius;
	private final float controller_handle_radius;
	private boolean iscontrolActive;
	
	private CControllerJoystic() {
		super();
		super.sensitivity = ApplicationOption.SENSITIVITY_JOYSTIC_DEFAULT;
		controller_background.setBitmap(ApplicationManager.getBitmap(R.drawable.joystic_background));
		controller_handle.setBitmap(ApplicationManager.getBitmap(R.drawable.joystic_handle));
		controller_background_radius = controller_background.getBitmap().getWidth()/2;
		controller_handle_radius = controller_handle.getBitmap().getWidth()/2;
		controller_x = ApplicationOption.getDeviceWidth() - ApplicationOption.PADDING_JOYSTIC - controller_background_radius;
		controller_y = ApplicationOption.getDeviceHeight() - ApplicationOption.PADDING_JOYSTIC - controller_background_radius;
		
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
		controller_background.draw(canvas);
		controller_handle.draw(canvas);
	}
	public void init(){
		controller_handle.setPosition(controller_x, controller_y);
	}
	@Override public void setSensitivity(int _current, int _max) {
		super.sensitivity = ApplicationOption.SENSITIVITY_JOYSTIC_MIN + (ApplicationOption.SENSITIVITY_JOYSTIC_MAX - ApplicationOption.SENSITIVITY_JOYSTIC_MIN) * (float)_current / (float)_max;
	}
	@Override public float getProgressRatio() {
		return (super.sensitivity - ApplicationOption.SENSITIVITY_JOYSTIC_MIN) / (ApplicationOption.SENSITIVITY_JOYSTIC_MAX - ApplicationOption.SENSITIVITY_JOYSTIC_MIN);
	}
	@Override public float getSensitivity() {
		return super.sensitivity;
	}
	@Override public void setDefaultSensitivity() {
		super.sensitivity = ApplicationOption.SENSITIVITY_JOYSTIC_DEFAULT;
	}
}
