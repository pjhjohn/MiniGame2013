package game.framework.controller;

import android.graphics.Canvas;
import android.view.View.OnTouchListener;

// Controller Interface
public interface IController extends OnTouchListener {
	void draw(Canvas canvas);
	void init();
	void destroy();
	void setSensitivity(int _current, int _max);
	void setDefaultSensitivity();
	float getProgressRatio();
	float getSensitivity();
}
