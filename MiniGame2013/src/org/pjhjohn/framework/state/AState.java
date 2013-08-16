package org.pjhjohn.framework.state;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public abstract class AState implements IState {
	protected IGameManager gameManager;
	public abstract boolean onTouch(View v, MotionEvent event);
	public abstract void init();
	@Override
	public void setGameManager(IGameManager _manager) {
		this.gameManager = _manager;
	}
	@Override
	public void dismiss(){
		// TODO : Override if needed
	}
	@Override
	public void update(){
		// TODO : Override if needed
	}
	@Override
	public void drawSurface(Canvas canvas){
		gameManager.drawSurface(canvas);
	}
}
