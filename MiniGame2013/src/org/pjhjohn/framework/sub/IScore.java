package org.pjhjohn.framework.sub;

public interface IScore {
	public void reset();
	public long get();
	public long hiScore();
	public long timeScore(long time);
	public void increase(long scoreEarned);
	public void setHiScore(long score);
}
