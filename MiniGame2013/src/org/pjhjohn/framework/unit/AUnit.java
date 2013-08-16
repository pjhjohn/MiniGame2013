package org.pjhjohn.framework.unit;

import org.pjhjohn.framework.obj2d.ImageObj;

public abstract class AUnit extends ImageObj implements IUnit {
	
	protected float speedX, speedY, accX, accY;
	
	public AUnit(){
		this.setSpeed(0, 0);
		this.setAcc(0, 0);
	}
	
	// IUnit Implementation
	public void update() {
		this.setPosition(this.posX + this.speedX, this.posY + this.speedY);
		this.setSpeed(this.speedX + this.accX, this.speedY + this.accY);
		// Accelerator as break : Stop at speed zero
		if(Math.abs(this.getSpeedX())<Math.abs(accX)){
			this.setAccX(0);
			this.setSpeedX(0);
		}
		if(Math.abs(this.getSpeedY())<Math.abs(accY)){
			this.setAccY(0);
			this.setSpeedY(0);
		}
	}
	
	// Speed
	public void setSpeed (float speed_x, float speed_y){
		this.speedX = speed_x;
		this.speedY = speed_y;
	}
	public void setSpeedX(float speed_x){
		this.speedX = speed_x;		
	}	
	public void setSpeedY(float speed_y){
		this.speedY = speed_y;
	}
	public float getSpeedX(){
		return this.speedX;
	}	
	public float getSpeedY(){
		return this.speedY;
	}
	
	
	// Acceleration
	public void setAcc (float acc_x, float acc_y){
		this.accX = acc_x;
		this.accY = acc_y;
	}
	public void setAccX(float acc_x){
		this.accX = acc_x;
	}	
	public void setAccY(float acc_y){
		this.accY = acc_y;
	}
	public float getAccX(){
		return this.accX;
	}
	public float getAccY(){
		return this.accY;
	}
	
	// Etc
	public abstract boolean isCrashed(IUnit _target);
	
	// ImageObj Override
	@Override
	public void setPosition(float x, float y){
		super.setPosition(x, y);
	}
}
