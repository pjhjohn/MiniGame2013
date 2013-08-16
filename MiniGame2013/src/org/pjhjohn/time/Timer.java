package org.pjhjohn.time;

public class Timer implements ITimer{
	public long startTime;
	public long timeLength;
	@Override
	public void reset() {
		timeLength = 0;
	}

	@Override
	public void start() {
		this.startTime = System.currentTimeMillis();
	}

	@Override
	public long pause() {
		timeLength += System.currentTimeMillis() - startTime;
		return timeLength;
	}
	
	@Override
	public long stop() {
		long time = pause();
		reset();
		return time;
	}
	
	@Override
	public long get() {
		return timeLength + System.currentTimeMillis() - startTime;
	}
}
