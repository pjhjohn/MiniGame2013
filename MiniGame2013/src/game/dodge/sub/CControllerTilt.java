package game.dodge.sub;

import game.dodge.unit.CUnitFactory;
import game.dodge.unit.CUnitTypePlayer;

import org.pjhjohn.framework.sub.AUnitController;
import org.pjhjohn.framework.sub.IController;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import app.main.AppOption;

public class CControllerTilt extends AUnitController {
	private float[] gravity = null;
	private float[] magnetic = null;
	private float initial_roll;
	private float current_roll;
	private static IController singleton = new CControllerTilt();
	private CControllerTilt() {
		super(CUnitFactory.getInstance().create(CUnitTypePlayer.getInstance()));
		super.sensitivity = (AppOption.Dodge.Sensitivity.TILT_MIN + AppOption.Dodge.Sensitivity.TILT_MAX)/2;
	}
	public static IController getInstance(){
		return singleton;
	}
	
	@Override
	public void update(SensorEvent event){
		switch(event.sensor.getType()){
		case Sensor.TYPE_ACCELEROMETER : 
			this.gravity = event.values.clone();
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			this.magnetic = event.values.clone();
			break;
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
		controllee.setSpeed(0, 0);
		this.initial_roll = current_roll;
	}
	@Override
	public void dismiss(){
		
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