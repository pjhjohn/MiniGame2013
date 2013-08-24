package game.dodge.state;

import org.pjhjohn.framework.manager.AState;
import org.pjhjohn.framework.manager.AppManager;
import org.pjhjohn.framework.manager.IState;
import org.pjhjohn.framework.sensorlistener.CListenerTouch;

import android.view.MotionEvent;
import android.view.View;

// Start Re-Assigned Thread
public class CStatePlaying extends AState {
	private static IState singleton = new CStatePlaying();
	public  static IState getInstance(){ return singleton; }
	private CStatePlaying(){ 
		super(); 
	}	
	@Override
	public void init() {
		// Initialize Controller ( Especially Gravity Controller )
		this.gameManager.onGameStart();
		AppManager.getController().init();
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		return ((CListenerTouch)CListenerTouch.getInstance()).onTouch(view, event);
//		return AppManager.getController().onTouch(view,event);
	}
	@Override
	public void update(){
		this.gameManager.update();
		this.gameManager.updateBackground();
		if(this.gameManager.isGameOver()) this.gameManager.setState(CStateGameover.getInstance());
	}
}
