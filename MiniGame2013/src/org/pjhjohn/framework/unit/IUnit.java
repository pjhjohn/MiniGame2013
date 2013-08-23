package org.pjhjohn.framework.unit;

/* [pjhjohn]
 * Interface of 2D-Unit.
 * Only Contains Physically-moving functions.
 * Drawing/Animating methods are taken by another interface.
 */
public interface IUnit {
	// Setter
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
	boolean isCrashed(IUnit another_unit);
}