package game.dodge;

import game.dodge.controller.CControllerJoystic;
import game.dodge.controller.CControllerTilt;
import game.dodge.controller.CControllerTouch;

import org.pjhjohn.framework.main.AppManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
public class DodgeActivity extends Activity {
	DodgeView mainView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		// ApplicationManager Properties
		AppManager.setContext(this);

		mainView = new DodgeView(this);
		setContentView(mainView);
		// Set View & Click-event Listeners to Buttons
		((Button)mainView.findViewById(DodgeView.ID_TOUCH)).setOnClickListener(btnOnClickListener);
		((Button)mainView.findViewById(DodgeView.ID_JOYSTIC)).setOnClickListener(btnOnClickListener);
		((Button)mainView.findViewById(DodgeView.ID_TILT)).setOnClickListener(btnOnClickListener);
		((Button)mainView.findViewById(DodgeView.ID_TOUCH)).setOnTouchListener(btnOnTouchListener);
		((Button)mainView.findViewById(DodgeView.ID_JOYSTIC)).setOnTouchListener(btnOnTouchListener);
		((Button)mainView.findViewById(DodgeView.ID_TILT)).setOnTouchListener(btnOnTouchListener);
	}
		
	private Button.OnClickListener btnOnClickListener = new View.OnClickListener() {
		public void onClick(View view){
			switch(view.getId()){
				case DodgeView.ID_TOUCH : 
					AppManager.setController(CControllerTouch.getInstance());
					break;
				case DodgeView.ID_JOYSTIC : 
					AppManager.setController(CControllerJoystic.getInstance());
					break;
				case DodgeView.ID_TILT : 
					AppManager.setController(CControllerTilt.getInstance());
					break;
				default : return;
			}	startActivity(new Intent(DodgeActivity.this, DodgeGameActivity.class));
		}
	};
	private Button.OnTouchListener btnOnTouchListener = new View.OnTouchListener() {
		public boolean onTouch(View view, MotionEvent event) {
			switch(event.getAction()){
				case MotionEvent.ACTION_DOWN : ((Button)view).setTextColor(Color.rgb(0xff, 0xad, 0x33));	break;
				case MotionEvent.ACTION_UP : ((Button)view).setTextColor(Color.WHITE);						break;
			}	return false;
		}
	};
}