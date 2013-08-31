package org.pjhjohn.framework.sub;


public interface ISubject {
	public void registerObserver(IObserver observer);
	public void unregisterObserver(IObserver observer);
	public void unregisterAll();
	public void notifyObservers();
}
