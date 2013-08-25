package org.pjhjohn.framework.sensorlistener;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class CListenerTouch extends AListener implements OnTouchListener{
	private boolean enable = true;
	private static AListener singleton = new CListenerTouch();
	private CListenerTouch() {
		super();
	}
	public static AListener getInstance(){
		return singleton;
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		super.motion = event;
		super.notifyObservers();
		return true;
	}
	@Override
	public void enable() {
		enable = true;
	}
	@Override
	public void disable() {
		enable = false;
	}
}
