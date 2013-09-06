package org.pjhjohn.framework.sub; 

import java.util.ArrayList;

import android.util.Log;

public class GameTimer {
	private static GameTimer singleton = new GameTimer();
	private GameTimer(){
		timerContainer = new ArrayList<CountDownTimerPausable>();
	}
	public static GameTimer getInstance(){ return GameTimer.singleton; }
	private ArrayList<CountDownTimerPausable> timerContainer;
	
	private boolean isRunning;
	private long startTime;
	private long elapsedTime;
	// Implement ITimer
	public void reset() {
		Log.i("GameTimer", "reset");
		this.isRunning = false;
		this.elapsedTime = 0;
	}

	public void start() {
		Log.i("GameTimer", "start " + timerContainer.size() + " timers.");
		for(CountDownTimerPausable timer : timerContainer) timer = timer.start();
		this.startTime = System.currentTimeMillis();
		this.isRunning = true;
	}

	public long pause() {
		Log.i("GameTimer", "pause");
		for(CountDownTimerPausable timer : timerContainer) timer.pause();
		this.elapsedTime += System.currentTimeMillis() - startTime;
		this.isRunning = false;
		return elapsedTime;
	}
	
	public long stop() {
		Log.i("GameTimer", "stop");
		for(CountDownTimerPausable timer : timerContainer) timer.cancel();
		this.elapsedTime += System.currentTimeMillis() - startTime;
		this.isRunning = false;
		return elapsedTime;
	}
	
	public long get() {
		if(isRunning) return elapsedTime + System.currentTimeMillis() - startTime;
		else return elapsedTime;
	}
	// Local Method
	public void registerCountDownTimer(CountDownTimerPausable timer){
		this.timerContainer.add(timer);
	}
	
	public void unregisterCountDownTimer(CountDownTimerPausable timer){
		this.timerContainer.remove(timer);
	}

	public void unregisterCountDownTimer(int index) {
		this.timerContainer.remove(index);
	}

	public void unregisterAll(){
		this.timerContainer.clear();
	}
	
	// Override Object
	@Override
	public String toString() {
		long score = this.get() / 100;
		int min, sec, ms = (int)score;
		min = ms/600;		ms = ms - min * 600;
		sec = ms/10;		ms = ms - sec * 10;
		return ((min<10)?("0"+min):min)+":"+((sec<10)?("0"+sec):sec)+":"+ms;
	}
}
