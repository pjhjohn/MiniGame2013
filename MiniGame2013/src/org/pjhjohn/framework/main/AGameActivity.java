package org.pjhjohn.framework.main;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public abstract class AGameActivity extends Activity {
	protected AGameView gameView;
	private AGameView sensor_attached_view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// FullScreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		AppManager.setContext(this);
	}
	@Override
	public void setContentView(View view){
		if(view instanceof AGameView){
			AppManager.setGameView((AGameView)view);
			this.gameView = (AGameView) view;
		} super.setContentView(view);
	}
	@Override
	protected void onResume(){	// Register Sensor
		super.onResume();
		this.sensor_attached_view = AppManager.getGameView();
		AppManager.getSensorManager().registerListener(sensor_attached_view,AppManager.getSensorManager().getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
		AppManager.getSensorManager().registerListener(sensor_attached_view,AppManager.getSensorManager().getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI);
	}
	@Override
	protected void onPause(){	// Unregister Sensor : What Happens when view changes in single activity???
		super.onPause();
		if(sensor_attached_view.equals(AppManager.getGameView())==false) Toast.makeText(AppManager.getContext(), "View has changed inside single Activity.", Toast.LENGTH_SHORT).show();
		AppManager.getSensorManager().unregisterListener(sensor_attached_view);
	}
	@Override
	protected void onStop(){
		super.onStop();
		this.gameView.onDestroy();
	}
}