package game.bubble.state;



import org.pjhjohn.framework.state.AState;
import org.pjhjohn.framework.state.IState;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BStateGameover extends AState {

	private static IState singleton = new BStateGameover();
	private boolean isActionDown;
	public static IState getInstance(){ return singleton; }
	public BStateGameover() {
		super();
		isActionDown = false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			isActionDown=true;
			break;
		case MotionEvent.ACTION_UP :
			if(isActionDown)
				gameManager.setState(BStatePregame.getInstance());
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
