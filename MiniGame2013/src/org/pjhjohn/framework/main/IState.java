package org.pjhjohn.framework.main;

import org.pjhjohn.framework.sub.IObserver;

import android.graphics.Canvas;

public interface IState extends IObserver {
	void init();								// State Initialization
	void update();								// Thread-Update
	void dismiss();								// State Destruction
	void setGameManager(IGameManager _manager);	// Set IGameManager that controls view
	void drawSurface(Canvas canvas);			// Called by Thread holding SurfaceHolder
}