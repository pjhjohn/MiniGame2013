package app.main;

import game.bubble.BubbleActivity;
import game.dodge.DodgeActivity;
import game.snowcraft.SnowCraftActivity;

import org.pjhjohn.framework.main.AppManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/* 1. MainActivity initializes static properties that will be used in further Activities.
 * setDeviceSize
 * ApplicationManager.setResources|setSensorManager|setContext
 * 2. MainActivity Also determines controlling mode for DodgeGame.
 */
public class AppMainActivity extends Activity {
	AppMainView mainView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		// ApplicationManager Properties
		AppManager.setResources(getResources());
		AppManager.setContext(this);
		AppManager.setSensorManager((SensorManager)this.getSystemService(SENSOR_SERVICE));

		mainView = new AppMainView(this);
		setContentView(mainView);
		// Register View & Click-event Listeners to Buttons
		((Button)mainView.findViewById(AppMainView.ID_BTN_MISSLEDODGE)).setOnClickListener(btnOnClickListener);
		((Button)mainView.findViewById(AppMainView.ID_BTN_PUZZLEBUBBLE)).setOnClickListener(btnOnClickListener);
		((Button)mainView.findViewById(AppMainView.ID_BTN_SNOWCRAFT)).setOnClickListener(btnOnClickListener);
		((Button)mainView.findViewById(AppMainView.ID_BTN_SETTING)).setOnClickListener(btnOnClickListener);
		((Button)mainView.findViewById(AppMainView.ID_BTN_MISSLEDODGE)).setOnTouchListener(btnOnTouchListener);
		((Button)mainView.findViewById(AppMainView.ID_BTN_PUZZLEBUBBLE)).setOnTouchListener(btnOnTouchListener);
		((Button)mainView.findViewById(AppMainView.ID_BTN_SNOWCRAFT)).setOnTouchListener(btnOnTouchListener);
		((Button)mainView.findViewById(AppMainView.ID_BTN_SETTING)).setOnTouchListener(btnOnTouchListener);
	}
	
	private Button.OnClickListener btnOnClickListener = new View.OnClickListener() {
		public void onClick(View view){
			switch(view.getId()){
				case AppMainView.ID_BTN_MISSLEDODGE  : startActivity(new Intent(AppMainActivity.this, DodgeActivity.class));	break;
				case AppMainView.ID_BTN_PUZZLEBUBBLE : startActivity(new Intent(AppMainActivity.this, BubbleActivity.class));	break;
				case AppMainView.ID_BTN_SNOWCRAFT    : startActivity(new Intent(AppMainActivity.this, SnowCraftActivity.class));break;
				case AppMainView.ID_BTN_SETTING : Toast.makeText(getApplicationContext(), "세팅 미구현", Toast.LENGTH_SHORT).show();
				default : return;
			}
		}
	};
	private Button.OnTouchListener btnOnTouchListener = new View.OnTouchListener() {
		public boolean onTouch(View view, MotionEvent event) {
			switch(event.getAction()){
				case MotionEvent.ACTION_DOWN : ((Button)view).setTextColor(Color.rgb(0xff, 0xad, 0x33));	break;
				case MotionEvent.ACTION_UP   : ((Button)view).setTextColor(Color.WHITE);					break;
			}	return false;
		}
	};
}