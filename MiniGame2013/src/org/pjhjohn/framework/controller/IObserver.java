package org.pjhjohn.framework.controller;

import android.hardware.SensorEvent;
import android.view.MotionEvent;

/* Observer will be registered to Subjects(subClass of ISubject)
 * I need to implement ones(inputs) to many(D:each game's controller) correspondance.
 * This figure will be shown by combination of MEDIATOR and OBSERVER pattern.
 * ONES : TOUCH/TILE/... EVENTS
 * MANY : Each Game's Controller (Current Game Registered at ApplicationManager will select Class)
 */

public interface IObserver {
	public void update(SensorEvent event);
	public boolean update(MotionEvent event);
}
