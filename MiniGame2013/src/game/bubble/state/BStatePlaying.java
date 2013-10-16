package game.bubble.state;

import org.pjhjohn.framework.main.AState;
import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.main.IState;

public class BStatePlaying extends AState {
	private static IState singleton = new BStatePlaying();
	public BStatePlaying() { super(); }
	public static IState getInstance() { return singleton; }
	
	@Override
	public void init() {
		this.gameManager.onGameStart();
		AppManager.getController().init();
	}
	public void update(){
		this.gameManager.update();
		if(this.gameManager.isGameOver()) AppManager.setState(BStateGameover.getInstance());
	}
}
