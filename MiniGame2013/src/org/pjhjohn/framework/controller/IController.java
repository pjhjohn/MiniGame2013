package org.pjhjohn.framework.controller;

import org.pjhjohn.framework.unit.AUnit;

import android.graphics.Canvas;

// Controller Interface
public interface IController extends IObserver{
	void draw(Canvas canvas);	// Draw on Canvas if Necessary
	void init();				// initialize when controller has been registered
	void dismiss();				// destroy when controller has been removed
	void setControlUnit(AUnit unitToControl);
	
	// Sensitivity Controller
	void setSensitivity(int _current, int _max);
	void setDefaultSensitivity();
	float getProgressRatio();
	float getSensitivity();
}
