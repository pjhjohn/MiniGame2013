package org.pjhjohn.framework.controller;


public interface ISubject {
	public void registerObserver(IObserver observer);
	public void unregisterObserver(IObserver observer);
	public void unregisterAll();
	public void notifyObservers();
}
