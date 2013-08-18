package org.pjhjohn.framework.sensorlistener;

import java.util.ArrayList;

import org.pjhjohn.framework.controller.IObserver;

import android.hardware.SensorEvent;
import android.view.MotionEvent;

public abstract class AListener implements ISubject, IListener{
	private ArrayList<IObserver> observers;
	protected MotionEvent motion;
	protected SensorEvent sensor;
	protected AListener(){
		this.observers = new ArrayList<IObserver>();
	}
	
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
	
	@Override
	public void unregisterAll(){
		for(int i = 0; i < observers.size(); i ++) {
			observers.remove(0);
		}
	}

	public void init(){
		// TODO : OVERRIDE if necessary
	}
	public void destroy(){
		// TODO : OVERRIDE if necessary
	}
}
