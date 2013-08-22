package game.bubble.state;

import org.pjhjohn.framework.state.AState;
import org.pjhjohn.framework.state.IState;
import org.pjhjohn.manager.AppManager;

import android.view.MotionEvent;
import android.view.View;

public class BStatePlaying extends AState {
	private static IState singleton = new BStatePlaying();
	public BStatePlaying() {	super(); }
	public static IState getInstance() { return singleton; }
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
//		return AppManager.getController().onTouch(v, event);
	}

	@Override
	public void init() {
		this.gameManager.onGameStart();
//		AppManager.getController().init();
	}
	public void update(){
		this.gameManager.update();
		this.gameManager.updateBackground();
		if(this.gameManager.isGameOver()) this.gameManager.setState(BStateGameover.getInstance());
	}
	

}
