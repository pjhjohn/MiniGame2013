package game.common;

import org.pjhjohn.framework.controller.IObserver;
import org.pjhjohn.manager.AppManager;

import android.hardware.SensorEvent;
import android.view.MotionEvent;

/*
 * ControllerBridge is Observer of AListener (CListenerTouch, CListenerSensor).
 * ControllerBridge have current active VIEW so that this class can choose Controller to send received event.
 * 
 * When MotionEvent is triggered on AGameView, @Override-onTouch method in AGameView directly calls state.onTouch method.
 * On First Layer - state - Current State Determines whether send current event trigger to Controller or not.
 * This is necessary to active/de-active sensor event trigger.
 * So, after all, ControllerBridge only deals with those triggered-event that state allows to do so.
 */
public class ControllerBridge implements IObserver{

	@Override
	public void update(MotionEvent event) {
		AppManager.getController().onTouch(AppManager.getGameView(), event);
		// WTF AM I DOING??? TODO TODO WHAT TODO
	}

	@Override
	public void update(SensorEvent event) {
		// TODO Auto-generated method stub
	}
}
