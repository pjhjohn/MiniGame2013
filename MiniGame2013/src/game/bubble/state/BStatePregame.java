package game.bubble.state;

import org.pjhjohn.framework.main.AState;
import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.main.IState;

import android.view.MotionEvent;

public class BStatePregame extends AState{

	public BStatePregame() { super(); }
	private static IState singleton = new BStatePregame();
	public static IState getInstance(){ return singleton; }
	private boolean isActionDown = false;
	@Override
	public boolean update(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			isActionDown=true;
			break;
		case MotionEvent.ACTION_UP :
			if(isActionDown)
				AppManager.setState(BStatePlaying.getInstance());
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
