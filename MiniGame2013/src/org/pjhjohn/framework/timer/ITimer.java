package org.pjhjohn.framework.timer;

public interface ITimer {
	public void reset();	// Reset Timer
	public void start();	// Start Timer
	public long pause();	// Pause Timer & return time in ms
	public long stop();		// Stop Timer & return time in ms
	public long get();		// Get Current Elipsed Time in ms
}
