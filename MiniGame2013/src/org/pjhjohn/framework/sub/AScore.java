package org.pjhjohn.framework.sub;

public abstract class AScore implements IScore{
	protected long HiScore;
	protected long currentScore;
	protected ITimer gameTimer;
	
	public AScore() {
		this.gameTimer = AGameTimer.getInstance();
		this.HiScore = 0;
		this.currentScore = 0;
	}

	@Override
	public void reset() {
		if(this.get() > this.HiScore) this.HiScore = this.get();
		this.currentScore = 0;
	}

	@Override
	public long get() {
		return this.timeScore(this.gameTimer.get()) + currentScore;
	}
	
	@Override
	public long hiScore() {
		long score = this.timeScore(this.gameTimer.get()) + this.currentScore;
		if(score > this.HiScore) this.HiScore = score;
		return this.HiScore;
	}

	@Override
	public abstract long timeScore(long time);

	@Override
	public void increase(long scoreEarned) {
		this.currentScore += scoreEarned;
	}

	@Override
	public void setHiScore(long score) {
		this.HiScore = score;
	}
}
