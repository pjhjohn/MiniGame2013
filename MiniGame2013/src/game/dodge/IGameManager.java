package game.dodge;

import org.pjhjohn.framework.state.IState;

import android.graphics.Canvas;

public interface IGameManager {
	void onInit();		// Called only once. For the first time
	void onStart();		// Game Start
	void onResume();	// Resume from PAUSE
	void onPause();		// Temporarily pause game
	void onStop();		// Stop Game Progress			
	void onDestroy();	// Stop Game Permanantly
	
	void update();		// update units per thread-loop
	void updateBackground();// update background per thread-loop
	void setCurrentState(IState _currentState);
	void drawSurface(Canvas canvas);
	boolean isGameOver();
}
