package game.dodge.state;

import org.pjhjohn.framework.main.AState;
import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.main.IState;

// Start Re-Assigned Thread
public class CStatePlaying extends AState {
	private static IState singleton = new CStatePlaying();
	public  static IState getInstance(){ return singleton; }
	private CStatePlaying(){ 
		super(); 
	}	
	@Override
	public void init() {
		this.gameManager.onGameStart();
		AppManager.getController().init();
	}

	@Override
	public void update(){
		this.gameManager.update();
		this.gameManager.updateBackground();
		if(this.gameManager.isGameOver()) AppManager.setState(CStateGameover.getInstance());
	}
}
