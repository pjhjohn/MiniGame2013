package org.pjhjohn.framework.timer; 

public class Timer implements ITimer{
	public long startTime;
	public long elapsedTime;
	@Override
	public void reset() {
		elapsedTime = 0;
	}

	@Override
	public void start() {
		this.startTime = System.currentTimeMillis();
	}

	@Override
	public long pause() {
		elapsedTime += System.currentTimeMillis() - startTime;
		return elapsedTime;
	}
	
	@Override
	public long stop() {
		long time = pause();
		reset();
		return time;
	}
	
	@Override
	public long get() {
		return elapsedTime + System.currentTimeMillis() - startTime;
	}
}
