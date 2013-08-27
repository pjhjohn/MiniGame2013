package org.pjhjohn.framework.resource;



public abstract class AUnit extends AnimatableObj implements IUnit {
	protected float speedX, speedY, accX, accY;	// Speed/Acceleration variables
	public AUnit(){
		super();
		this.setSpeed(0, 0);
		this.setAcc(0, 0);
	}
	// AnimatableObj Override
	@Override
	public void update() {
		this.setPosition(this.x + this.speedX, this.y + this.speedY);
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
		
	// IUnit Implementation
	@Override
	public void setSpeed (float speed_x, float speed_y){
		this.speedX = speed_x;
		this.speedY = speed_y;
	}
	@Override
	public void setSpeedX(float speed_x){
		this.speedX = speed_x;		
	}
	@Override
	public void setSpeedY(float speed_y){
		this.speedY = speed_y;
	}
	@Override
	public void setAcc (float acc_x, float acc_y){
		this.accX = acc_x;
		this.accY = acc_y;
	}
	@Override
	public void setAccX(float acc_x){
		this.accX = acc_x;
	}	
	@Override
	public void setAccY(float acc_y){
		this.accY = acc_y;
	}
	@Override
	public float getSpeedX(){
		return this.speedX;
	}
	@Override
	public float getSpeedY(){
		return this.speedY;
	}
	@Override
	public float getAccX(){
		return this.accX;
	}
	@Override
	public float getAccY(){
		return this.accY;
	}
	public abstract boolean isCrashed(IUnit another_unit);
}
