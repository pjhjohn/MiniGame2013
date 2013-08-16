package game.dodge.controller;

import org.pjhjohn.framework.controller.AController;
import org.pjhjohn.framework.controller.IController;

import android.view.MotionEvent;
import android.view.View;
import app.main.AppOption;

public class CControllerTouch extends AController {
	private static IController singleton = new CControllerTouch();
	private CControllerTouch() {
		super();
		super.sensitivity = (AppOption.Dodge.Sensitivity.TOUCH_MIN + AppOption.Dodge.Sensitivity.TOUCH_MAX)/2;
	}
	public static IController getInstance(){
		return singleton;
	}
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			player.setSpeedX(super.sensitivity * (event.getX() - player.getX()));
			player.setSpeedY(super.sensitivity * (event.getY() - player.getY()));
			player.setAccX(-super.sensitivity*super.sensitivity*(event.getX() - player.getX())/2);
			player.setAccY(-super.sensitivity*super.sensitivity*(event.getY() - player.getY())/2);
			break;
		case MotionEvent.ACTION_MOVE:
			player.setSpeedX(super.sensitivity * (event.getX() - player.getX()));
			player.setSpeedY(super.sensitivity * (event.getY() - player.getY()));
			player.setAccX(0);
			player.setAccY(0);
			break;
		case MotionEvent.ACTION_UP:
			player.setAccX(-player.getSpeedX()/10);
			player.setAccY(-player.getSpeedY()/10);
			break;
		} return true;
	}
	@Override
	public void setSensitivity(int _current, int _max) {
		super.sensitivity = AppOption.Dodge.Sensitivity.TOUCH_MIN + (AppOption.Dodge.Sensitivity.TOUCH_MAX - AppOption.Dodge.Sensitivity.TOUCH_MIN) * (float)_current / (float)_max;
	}
	@Override
	public float getProgressRatio() {
		return (super.sensitivity - AppOption.Dodge.Sensitivity.TOUCH_MIN) / (AppOption.Dodge.Sensitivity.TOUCH_MAX - AppOption.Dodge.Sensitivity.TOUCH_MIN);
	}
	@Override
	public float getSensitivity() {
		return super.sensitivity;
	}
	@Override
	public void setDefaultSensitivity() {
		super.sensitivity = (AppOption.Dodge.Sensitivity.TOUCH_MIN + AppOption.Dodge.Sensitivity.TOUCH_MAX)/2;
	}
}
