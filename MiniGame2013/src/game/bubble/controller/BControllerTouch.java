package game.bubble.controller;

import game.bubble.BubbleView;
import game.bubble.unit.BUnitBall;
import game.bubble.unit.BUnitFactory;
import game.bubble.unit.BUnitPlayer;
import game.bubble.unit.BUnitTypePlayer;

import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.sub.AUnitController;
import org.pjhjohn.framework.sub.IController;

import android.view.MotionEvent;

public class BControllerTouch extends AUnitController {
	private static IController singleton = new BControllerTouch();
	public BControllerTouch() { 
		super();
		super.controllee = BUnitFactory.getInstance().create(BUnitTypePlayer.getInstance());
	}
	public static IController getInstance() { return singleton; }
	private BUnitBall mvBall;
	@Override
	public boolean update (MotionEvent event) {
		// ToDo : down과 move상태에서 player의 각도를 바꾸는것, up일때 공을 쏘아보내는것
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			((BUnitPlayer)controllee).rotate(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_MOVE:
			((BUnitPlayer)controllee).rotate(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_UP:
			mvBall=((BubbleView)AppManager.getGameView()).getMovingBall();
			mvBall.shooting(event.getX(), event.getY());
			mvBall.setMoving(true);
			break;
		} return true;
	}

	@Override
	public void setSensitivity(int _current, int _max) {
	}

	@Override
	public void setDefaultSensitivity() {
	}

	@Override
	public float getProgressRatio() {
		return 0;
	}

	@Override
	public float getSensitivity() {
		return 0;
	}
}
