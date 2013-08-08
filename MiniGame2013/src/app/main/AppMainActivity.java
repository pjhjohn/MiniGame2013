package app.main;

import org.pjhjohn.framework.ApplicationManager;
import org.pjhjohn.framework.ApplicationOption;

import game.bubble.BubbleActivity;
import game.dodge.DodgeActivity;
import game.snowcraft.SnowCraftActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

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
		setDeviceSize();
		ApplicationManager.setResources(getResources());
		ApplicationManager.setSensorManager((SensorManager)this.getSystemService(SENSOR_SERVICE));
		ApplicationManager.setContext(this);

		mainView = new AppMainView(this);
		setContentView(mainView);
		// Set View & Click-event Listeners to Buttons
		((Button)mainView.findViewById(AppMainView.ID_BTN_MISSLEDODGE)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(AppMainView.ID_BTN_PUZZLEBUBBLE)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(AppMainView.ID_BTN_SNOWCRAFT)).setOnClickListener(buttonClickListerForIntent);
//		((Button)mainView.findViewById(AppMainView.ID_BTN4)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(AppMainView.ID_BTN_MISSLEDODGE)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
		((Button)mainView.findViewById(AppMainView.ID_BTN_PUZZLEBUBBLE)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
		((Button)mainView.findViewById(AppMainView.ID_BTN_SNOWCRAFT)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
//		((Button)mainView.findViewById(AppMainView.ID_BTN4)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
	}
	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void setDeviceSize(){
		Point size = new Point();
		WindowManager windowManager = getWindowManager();
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			windowManager.getDefaultDisplay().getSize(size);
			ApplicationOption.setDeviceSize(size.x, size.y);
		} else {
			Display display = windowManager.getDefaultDisplay();
			ApplicationOption.setDeviceSize(display.getWidth(), display.getHeight());
		}
	}
	
	private Button.OnClickListener buttonClickListerForIntent = new View.OnClickListener() {
		public void onClick(View v){
			switch(v.getId()){
				case AppMainView.ID_BTN_MISSLEDODGE : startActivity(new Intent(AppMainActivity.this, DodgeActivity.class));		break;
				case AppMainView.ID_BTN_PUZZLEBUBBLE : startActivity(new Intent(AppMainActivity.this, BubbleActivity.class));	break;
				case AppMainView.ID_BTN_SNOWCRAFT : startActivity(new Intent(AppMainActivity.this, SnowCraftActivity.class));	break;
				//case AppMainView.ID_BTN4 : startActivity(new Intent(AppMainActivity.this, RankingActivity.class));
				default : return;
			}
		}
	};
	private Button.OnTouchListener buttonTouchListenerForChangeTextColor = new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent e) {
			switch(e.getAction()){
				case MotionEvent.ACTION_DOWN : ((Button)v).setTextColor(Color.rgb(0xff, 0xad, 0x33));	break;
				case MotionEvent.ACTION_UP   : ((Button)v).setTextColor(Color.WHITE);					break;
			}	return false;
		}
	};
}