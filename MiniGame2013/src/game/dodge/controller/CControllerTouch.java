package game.dodge.controller;

import game.dodge.unit.CUnitFactory;
import game.dodge.unit.CUnitTypePlayer;

import org.pjhjohn.framework.controller.AUnitController;
import org.pjhjohn.framework.controller.IController;

import android.view.MotionEvent;
import app.main.AppOption;

public class CControllerTouch extends AUnitController {
	private static IController singleton = new CControllerTouch();
	private CControllerTouch() {
		super();
		super.controllee = CUnitFactory.getInstance().create(CUnitTypePlayer.getInstance());
		super.sensitivity = (AppOption.Dodge.Sensitivity.TOUCH_MIN + AppOption.Dodge.Sensitivity.TOUCH_MAX)/2;
	}
	public static IController getInstance(){
		return singleton;
	}
	@Override
	public boolean update(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			controllee.setSpeedX(super.sensitivity * (event.getX() - controllee.getX()));
			controllee.setSpeedY(super.sensitivity * (event.getY() - controllee.getY()));
			controllee.setAccX(-super.sensitivity*super.sensitivity*(event.getX() - controllee.getX())/2);
			controllee.setAccY(-super.sensitivity*super.sensitivity*(event.getY() - controllee.getY())/2);
			break;
		case MotionEvent.ACTION_MOVE:
			controllee.setSpeedX(super.sensitivity * (event.getX() - controllee.getX()));
			controllee.setSpeedY(super.sensitivity * (event.getY() - controllee.getY()));
			controllee.setAccX(0);
			controllee.setAccY(0);
			break;
		case MotionEvent.ACTION_UP:
			controllee.setAccX(-controllee.getSpeedX()/10);
			controllee.setAccY(-controllee.getSpeedY()/10);
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
