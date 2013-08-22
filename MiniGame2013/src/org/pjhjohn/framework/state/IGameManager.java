package org.pjhjohn.framework.state;

import android.graphics.Canvas;

public interface IGameManager {
	void onCreate();	// ONLY ONCE for the first time.
	void onGameReady();		// Game Ready : Load resources
	void onGameStart();		// Game Start
	void onGamePause();		// Game Pause : Release Thread
	void onGameResume();	// Game Resume: Reattach Thread
	void onGameOver();		// Game Over  : Stop Game Progress
	void onDestroy();		// Stop Game Permanantly
	
	void update();			// update units per thread-loop
	void updateBackground();// update background per thread-loop
	void setState(IState nextState);
	void drawSurface(Canvas canvas);
	boolean isGameOver();
}
