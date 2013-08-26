package game.dodge.state;

import org.pjhjohn.framework.manager.AState;
import org.pjhjohn.framework.manager.AppManager;
import org.pjhjohn.framework.manager.IState;

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
	public void update(){
		this.gameManager.update();
		this.gameManager.updateBackground();
		if(this.gameManager.isGameOver()) AppManager.setState(CStateGameover.getInstance());
	}
}
