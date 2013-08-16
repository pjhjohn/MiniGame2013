package org.pjhjohn.framework.sensorlistener;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class CListenerTouch extends AListener implements OnTouchListener{
	private static IListener singleton = new CListenerTouch();
	private CListenerTouch() {
		super();
	}
	public static IListener getInstance(){
		return singleton;
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		super.motion = event;
		super.notifyObservers();
		return true;
	}
}
