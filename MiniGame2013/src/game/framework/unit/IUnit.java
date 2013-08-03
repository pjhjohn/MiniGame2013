package game.framework.unit;

public interface IUnit {
	void initPosition(float x, float y);
	void move();
	void setSpeedX(float dx);
	void setSpeedY(float dy);
	void setAccX(float ddx);
	void setAccY(float ddy);
	float getSpeedX();
	float getSpeedY();
	boolean isCrashed(IUnit _target);
}