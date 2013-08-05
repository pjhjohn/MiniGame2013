package game.snow;

import game.dodge.activity.DodgeGameActivity;
import game.dodge.rank.RankingActivity;
import game.framework.ApplicationManager;
import game.framework.controller.CControllerJoystic;
import game.framework.controller.CControllerTilt;
import game.framework.controller.CControllerTouch;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
public class SnowActivity extends Activity {
	SnowView mainView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		// ApplicationManager Properties
		ApplicationManager.setContext(this);

		mainView = new SnowView(this);
		setContentView(mainView);
		// Set View & Click-event Listeners to Buttons
		((Button)mainView.findViewById(SnowView.ID_BTN1)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(SnowView.ID_BTN2)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(SnowView.ID_BTN3)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(SnowView.ID_BTN4)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(SnowView.ID_BTN1)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
		((Button)mainView.findViewById(SnowView.ID_BTN2)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
		((Button)mainView.findViewById(SnowView.ID_BTN3)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
		((Button)mainView.findViewById(SnowView.ID_BTN4)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
	}
		
	private Button.OnClickListener buttonClickListerForIntent = new View.OnClickListener() {
		public void onClick(View v){
			switch(v.getId()){
				case SnowView.ID_BTN1 : Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_SHORT).show();
				case SnowView.ID_BTN2 : ApplicationManager.setController(CControllerJoystic.getInstance());	break;
				case SnowView.ID_BTN3 : ApplicationManager.setController(CControllerTilt.getInstance());		break;
				case SnowView.ID_BTN4 : startActivity(new Intent(SnowActivity.this, RankingActivity.class));
				default : return;
			}//	startActivity(new Intent(SnowActivity.this, DodgeGameActivity.class));
		}
	};
	private Button.OnTouchListener buttonTouchListenerForChangeTextColor = new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent e) {
			switch(e.getAction()){
				case MotionEvent.ACTION_DOWN : ((Button)v).setTextColor(Color.rgb(0xff, 0xad, 0x33));	break;
				case MotionEvent.ACTION_UP : ((Button)v).setTextColor(Color.WHITE);						break;
			}	return false;
		}
	};
}