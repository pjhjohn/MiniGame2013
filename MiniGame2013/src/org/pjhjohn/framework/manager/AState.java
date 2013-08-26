package org.pjhjohn.framework.manager;

import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

public abstract class AState implements IState {
	protected IGameManager gameManager;
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
	
	// IObserver Part
	@Override
	public void update(SensorEvent event){
		if(AppManager.getController()!=null) AppManager.getController().update(event);
	}
	@Override
	public boolean update(MotionEvent event){
		if(AppManager.getController()!=null) return AppManager.getController().update(event);
		else return false;
	}
}
