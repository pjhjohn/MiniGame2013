package org.pjhjohn.framework.sub; 

public class GameTimer implements ITimer{
	private static ITimer singleton = new GameTimer();
	private GameTimer(){}
	public static ITimer getInstance(){ return GameTimer.singleton; }
	
	private boolean isRunning;
	private long startTime;
	private long elapsedTime;
	@Override
	public void reset() {
		this.elapsedTime = 0;
		this.isRunning = false;
	}

	@Override
	public void start() {
		this.startTime = System.currentTimeMillis();
		this.isRunning = true;
	}

	@Override
	public long pause() {
		this.elapsedTime += System.currentTimeMillis() - startTime;
		this.isRunning = false;
		return elapsedTime;
	}
	
	@Override
	public long stop() {
		long time = pause();
		this.isRunning = false;
		return time;
	}
	
	@Override
	public long get() {
		if(isRunning) return elapsedTime + System.currentTimeMillis() - startTime;
		else return elapsedTime;
	}
	
	@Override
	public String toString() {
		long score = this.get() / 100;
		int min, sec, ms = (int)score;
		min = ms/600;		ms = ms - min * 600;
		sec = ms/10;		ms = ms - sec * 10;
		return ((min<10)?("0"+min):min)+":"+((sec<10)?("0"+sec):sec)+":"+ms;
	}
}
