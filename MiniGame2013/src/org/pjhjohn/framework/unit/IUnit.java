package org.pjhjohn.framework.unit;

/* Interface of 2D-Units of this world.
 * Simply, They have position, speed, acceleration, like most of 2D-figures.
 * So, methods for getting/setting components above are declared.
 */
public interface IUnit {
	// Setter
	void setPosition(float pos_x, float pos_y);
	void setSpeed (float speed_x, float speed_y);
	void setSpeedX(float speed_x);
	void setSpeedY(float speed_y);
	void setAcc (float acc_x, float acc_y);
	void setAccX(float acc_x);
	void setAccY(float acc_y);
	// Getter
	float getSpeedX();
	float getSpeedY();
	float getAccX();
	float getAccY();
	// Etc
	void update();
	boolean isCrashed(IUnit another_unit);
}