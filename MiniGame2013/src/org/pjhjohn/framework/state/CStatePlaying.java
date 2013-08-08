package org.pjhjohn.framework.state;

import org.pjhjohn.framework.ApplicationManager;

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
		this.gameManager.onStart();
		ApplicationManager.getController().init();
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		return ApplicationManager.getController().onTouch(view,event);
	}
	@Override
	public void update(){
		this.gameManager.update();
		this.gameManager.updateBackground();
		if(this.gameManager.isGameOver()) this.gameManager.setCurrentState(CStateGameover.getInstance());
	}
}
