package game.bubble;


import game.bubble.controller.BControllerTouch;

import org.pjhjohn.framework.main.AGameActivity;
import org.pjhjohn.framework.main.AppManager;

import android.os.Bundle;
public class BubbleActivity extends AGameActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ApplicationManager Properties
		setContentView(new BubbleView(this));
		AppManager.setController(BControllerTouch.getInstance());
	}	
}