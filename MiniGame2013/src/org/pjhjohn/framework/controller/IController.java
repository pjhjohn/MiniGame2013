package org.pjhjohn.framework.controller;

import android.graphics.Canvas;
import android.view.View.OnTouchListener;

// Controller Interface
public interface IController extends OnTouchListener {
	void draw(Canvas canvas);						// Draw on Canvas
	void init();									// initialize when controller has been registered
	void destroy();									// destroy when controller has been removed
	void setSensitivity(int _current, int _max);	//
	void setDefaultSensitivity();
	float getProgressRatio();
	float getSensitivity();
}
