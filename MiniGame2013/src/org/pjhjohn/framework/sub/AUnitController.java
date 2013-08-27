package org.pjhjohn.framework.sub;

import org.pjhjohn.framework.resource.AUnit;

import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

public abstract class AUnitController implements IObserver, IController {
	protected AUnit controllee;
	protected float sensitivity;
	public AUnitController(AUnit controllee){
		this.controllee = controllee; 
	}
	public AUnitController(){
		this.controllee = null;
	}
	@Override
	public void setControlUnit(AUnit unitToControl){
		this.controllee = unitToControl;
	}
	@Override
	public void init(){
	}
	@Override
	public void dismiss(){
	}
	@Override
	public void draw(Canvas canvas){
	}	
	@Override
	public void update(SensorEvent event){
	}
	@Override
	public boolean update(MotionEvent event){
		return false;		
	}
	
	@Override public abstract void setSensitivity(int _current, int _max);
	@Override public abstract void setDefaultSensitivity();
	@Override public abstract float getProgressRatio();
	@Override public abstract float getSensitivity();
}
