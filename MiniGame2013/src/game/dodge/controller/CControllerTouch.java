package game.dodge.controller;

import game.dodge.unit.CUnitFactory;
import game.dodge.unit.CUnitTypePlayer;

import org.pjhjohn.framework.sub.AUnitController;
import org.pjhjohn.framework.sub.IController;

import android.view.MotionEvent;
import app.main.AppOption;

public class CControllerTouch extends AUnitController {
	private static IController singleton = new CControllerTouch();
	private CControllerTouch() {
		super(CUnitFactory.getInstance().create(CUnitTypePlayer.getInstance()));
		super.sensitivity = (AppOption.Dodge.Sensitivity.TOUCH_MIN + AppOption.Dodge.Sensitivity.TOUCH_MAX)/2;
	}
	public static IController getInstance(){
		return singleton;
	}
	@Override
	public boolean update(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			controllee.setSpeed(super.sensitivity * (event.getX() - controllee.getX()), super.sensitivity * (event.getY() - controllee.getY()));
			controllee.setAcc(super.sensitivity*super.sensitivity*(controllee.getX()-event.getX())/2, super.sensitivity*super.sensitivity*(controllee.getY()-event.getY())/2);
			break;
		case MotionEvent.ACTION_MOVE:
			controllee.setSpeed(super.sensitivity * (event.getX() - controllee.getX()), super.sensitivity * (event.getY() - controllee.getY()));
			controllee.setAcc(0, 0);
			break;
		case MotionEvent.ACTION_UP:
			controllee.setAcc(-controllee.getSpeedX()/10, -controllee.getSpeedY()/10);
			break;
		} return true;
	}
	@Override
	public void setSensitivity(int current, int max) {
		super.sensitivity = AppOption.Dodge.Sensitivity.TOUCH_MIN + (AppOption.Dodge.Sensitivity.TOUCH_MAX - AppOption.Dodge.Sensitivity.TOUCH_MIN) * (float)current / (float)max;
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
