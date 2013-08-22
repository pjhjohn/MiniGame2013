package game.bubble.state;

import org.pjhjohn.framework.state.AState;
import org.pjhjohn.framework.state.IState;

import org.pjhjohn.manager.AppManager;

import android.view.MotionEvent;
import android.view.View;

public class BStatePregame extends AState{

	public BStatePregame() { super(); }
	private static IState singleton = new BStatePregame();
	public static IState getInstance(){ return singleton; }
	private boolean isActionDown = false;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			isActionDown=true;
			break;
		case MotionEvent.ACTION_UP :
			if(isActionDown)
				gameManager.setState(BStatePlaying.getInstance());
			isActionDown=false;
		}
		return isActionDown;
	}

	@Override
	public void init() {
//		AppManager.getController().init();
		this.gameManager.onGameReady();
	}
	

}
