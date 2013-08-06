package org.pjhjohn.framework.state;

import game.dodge.IGameManager;
import android.graphics.Canvas;
import android.view.View.OnTouchListener;

public interface IState extends OnTouchListener{
	void init();								// State Initialization
	void update();								// Thread-Update
	void dismiss();								// State Destruction
	void setGameManager(IGameManager _manager);	// Set IGameManager that controls view
	void drawSurface(Canvas canvas);			// Called by Thread holding SurfaceHolder
}
 