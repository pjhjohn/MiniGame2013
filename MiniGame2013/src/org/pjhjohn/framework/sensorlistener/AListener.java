package org.pjhjohn.framework.sensorlistener;

import java.util.ArrayList;

import org.pjhjohn.framework.controller.IObserver;

import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.View;

public abstract class AListener implements ISubject, IListener{
	private ArrayList<IObserver> observers;
	protected MotionEvent motion;
	protected SensorEvent sensor;
	protected View view;
	
	@Override
	public void registerObserver(IObserver observer) {
		observers.add(observer);
	}

	@Override
	public void unregisterObserver(IObserver observer) {
		int i = observers.indexOf(observer);
		if(i>=0) observers.remove(i);
	}

	@Override
	public void notifyObservers() {
		for(int i = 0; i < observers.size(); i ++) {
			IObserver observer = (IObserver)observers.get(i);
			observer.update(motion);
			observer.update(sensor);
		}
	}

	public void init(){
		// TODO : OVERRIDE if necessary
	}
	public void destroy(){
		// TODO : OVERRIDE if necessary
	}
}
