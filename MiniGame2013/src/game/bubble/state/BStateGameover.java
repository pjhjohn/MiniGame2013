package game.bubble.state;



import org.pjhjohn.framework.main.AState;
import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.main.IState;

import android.view.MotionEvent;

public class BStateGameover extends AState {

	private static IState singleton = new BStateGameover();
	private boolean isActionDown;
	public static IState getInstance(){ return singleton; }
	public BStateGameover() {
		super();
		isActionDown = false;
	}

	@Override
	public boolean update(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			isActionDown=true;
			break;
		case MotionEvent.ACTION_UP :
			if(isActionDown)
				AppManager.setState(BStatePregame.getInstance());
			isActionDown=false;
		}
		return isActionDown;
	}

	@Override
	public void init() {	
		
	}
	public void update(){
		this.gameManager.updateBackground();
	}
}
