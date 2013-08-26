package game.dodge.controller;

import game.dodge.unit.CUnitFactory;
import game.dodge.unit.CUnitTypePlayer;

import org.pjhjohn.framework.controller.AUnitController;
import org.pjhjohn.framework.controller.IController;
import org.pjhjohn.framework.manager.AppManager;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import app.main.AppOption;

public class CControllerTilt extends AUnitController implements SensorEventListener{
	private SensorManager sensorManager;
	private float[] gravity = null;
	private float[] magnetic = null;
	private float initial_roll;
	private float current_roll;
	private static IController singleton = new CControllerTilt();
	private CControllerTilt() {
		super();
		super.controllee = CUnitFactory.getInstance().create(CUnitTypePlayer.getInstance());
		super.sensitivity = (AppOption.Dodge.Sensitivity.TILT_MIN + AppOption.Dodge.Sensitivity.TILT_MAX)/2;
		sensorManager = AppManager.getSensorManager();
		Log.i("sensor","sensormanager received");
	}
	public static IController getInstance(){
		return singleton;
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		Log.i("sensor","changed");
		switch(event.sensor.getType()){
		case Sensor.TYPE_ACCELEROMETER:
			this.gravity = event.values.clone();
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			this.magnetic = event.values.clone();
		}
		if(gravity!=null && magnetic!=null){
			float[] R = new float[16];
			SensorManager.getRotationMatrix(R, null, gravity, magnetic);
			float[] values = new float[3];
			SensorManager.getOrientation(R, values);
			current_roll = -Radian2Degree(values[2]);
			
			float coefficient = (float)(1/(Math.abs(Math.cos(initial_roll)) + 0.1));				// Correcting coefficient of Roll-initiated pitch error
			controllee.setSpeedX(super.sensitivity * -Radian2Degree(values[1]) * coefficient);			// pitch
			controllee.setSpeedY(super.sensitivity * (current_roll - initial_roll));			
		}
	}
	@Override
	public void init(){
		controllee.setSpeedX(0);
		controllee.setSpeedY(0);
		sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
		sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI);
		Log.i("sensor","registered");
		this.initial_roll = current_roll;
	}
	@Override
	public void dismiss(){
		sensorManager.unregisterListener(this);
		Log.i("sensor","unregistered");
	}
	private float Radian2Degree(float radian) {
		return radian * 180 / (float)Math.PI;
	}
	@Override
	public void setSensitivity(int _current, int _max) {
		super.sensitivity = AppOption.Dodge.Sensitivity.TILT_MIN + (AppOption.Dodge.Sensitivity.TILT_MAX - AppOption.Dodge.Sensitivity.TILT_MIN) * (float)_current / (float)_max;
	}
	@Override
	public float getProgressRatio() {
		return (super.sensitivity - AppOption.Dodge.Sensitivity.TILT_MIN) / (AppOption.Dodge.Sensitivity.TILT_MAX - AppOption.Dodge.Sensitivity.TILT_MIN);
	}
	@Override
	public float getSensitivity() {
		return super.sensitivity;
	}
	@Override
	public void setDefaultSensitivity() {
		super.sensitivity = (AppOption.Dodge.Sensitivity.TILT_MIN + AppOption.Dodge.Sensitivity.TILT_MAX)/2;
	}
}