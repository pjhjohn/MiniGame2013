package game.framework.controller;

import game.framework.unit.AUnit;
import game.framework.unit.CUnitFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public abstract class AController implements IController {
	protected AUnit player;
	protected float sensitivity;
	public AController(){
		player = CUnitFactory.getInstance().create(CUnitFactory.UnitType.PLAYER);
	}
	public void init(){
		// TODO : OVERRIDE if necessary
	}
	public void destroy(){
		// TODO : OVERRIDE if necessary
	}
	public void draw(Canvas canvas){
		// TODO : OVERRIDE if necessary
	}	
	public abstract boolean onTouch(View v, MotionEvent event);
	public abstract void setSensitivity(int _current, int _max);
	public abstract void setDefaultSensitivity();
	public abstract float getProgressRatio();
	public abstract float getSensitivity();
}
