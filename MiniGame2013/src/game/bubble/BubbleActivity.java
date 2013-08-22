package game.bubble;

import game.snowcraft.SnowCraftView;

import org.pjhjohn.manager.AppManager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/* 1. MainActivity initializes static properties that will be used in further Activities.
 * setDeviceSize
 * ApplicationManager.setResources|setSensorManager|setContext
 * 2. MainActivity Also determines controlling mode for DodgeGame.
 */
public class BubbleActivity extends Activity {
	private BubbleView bubbleView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		// ApplicationManager Properties
		AppManager.setContext(this);

		bubbleView = new BubbleView(this);
		setContentView(bubbleView);
	}
	protected void onDestroy(){
		super.onDestroy();
		bubbleView.onDestroy();		// unregister sensors
	}	
	
}