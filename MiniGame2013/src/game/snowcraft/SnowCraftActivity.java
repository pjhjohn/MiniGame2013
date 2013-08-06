package game.snowcraft;

import org.pjhjohn.framework.ApplicationManager;
import org.pjhjohn.framework.controller.CControllerJoystic;
import org.pjhjohn.framework.controller.CControllerTilt;
import org.pjhjohn.framework.rank.RankingActivity;

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
public class SnowCraftActivity extends Activity {
	SnowCraftView mainView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		// ApplicationManager Properties
		ApplicationManager.setContext(this);

		mainView = new SnowCraftView(this);
		setContentView(mainView);
		// Set View & Click-event Listeners to Buttons
		((Button)mainView.findViewById(SnowCraftView.ID_BTN1)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(SnowCraftView.ID_BTN2)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(SnowCraftView.ID_BTN3)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(SnowCraftView.ID_BTN4)).setOnClickListener(buttonClickListerForIntent);
		((Button)mainView.findViewById(SnowCraftView.ID_BTN1)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
		((Button)mainView.findViewById(SnowCraftView.ID_BTN2)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
		((Button)mainView.findViewById(SnowCraftView.ID_BTN3)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
		((Button)mainView.findViewById(SnowCraftView.ID_BTN4)).setOnTouchListener(buttonTouchListenerForChangeTextColor);
	}
		
	private Button.OnClickListener buttonClickListerForIntent = new View.OnClickListener() {
		public void onClick(View v){
			switch(v.getId()){
				case SnowCraftView.ID_BTN1 : Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_SHORT).show();
				case SnowCraftView.ID_BTN2 : ApplicationManager.setController(CControllerJoystic.getInstance());	break;
				case SnowCraftView.ID_BTN3 : ApplicationManager.setController(CControllerTilt.getInstance());		break;
				case SnowCraftView.ID_BTN4 : startActivity(new Intent(SnowCraftActivity.this, RankingActivity.class));
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