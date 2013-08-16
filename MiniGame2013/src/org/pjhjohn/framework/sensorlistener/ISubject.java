package org.pjhjohn.framework.sensorlistener;

import org.pjhjohn.framework.controller.IObserver;

public interface ISubject {
	public void registerObserver(IObserver observer);
	public void unregisterObserver(IObserver observer);
	public void notifyObservers();
}
