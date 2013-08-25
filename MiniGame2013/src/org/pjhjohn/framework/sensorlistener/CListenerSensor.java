package org.pjhjohn.framework.sensorlistener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class CListenerSensor extends AListener implements SensorEventListener{
	private static AListener singleton = new CListenerSensor();
	private CListenerSensor() {
		super();
	}
	public static AListener getInstance(){
		return singleton;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		super.sensor = event;
		super.motion = null;	
	}
	@Override
	public void init(){
		
	}
	@Override
	public void destroy(){
		
	}
	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}
}