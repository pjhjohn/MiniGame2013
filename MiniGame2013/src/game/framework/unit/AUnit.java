package game.framework.unit;

import game.framework.ImageObject;

public abstract class AUnit extends ImageObject implements IUnit {
	protected float sx = 0;
	protected float sy = 0;
	protected float ax = 0;
	protected float ay = 0;
	@Override
	public void move() {
		this.setPosition(this.x + this.sx, this.y + this.sy);
		this.setSpeedX(this.sx + this.ax);
		this.setSpeedY(this.sy + this.ay);
		// Accelerator as break : Stop at speed zero
		if(Math.abs(this.getSpeedX())<Math.abs(ax)){
			this.setAccX(0);
			this.setSpeedX(0);
		}
		if(Math.abs(this.getSpeedY())<Math.abs(ay)){
			this.setAccY(0);
			this.setSpeedY(0);
		}
	}
	@Override
	public void setSpeedX(float dx) {
		this.sx = dx;		
	}
	@Override
	public void setSpeedY(float dy) {
		this.sy = dy;
	}
	@Override
	public void setAccX(float ddx){
		this.ax = ddx;
	}
	@Override
	public void setAccY(float ddy){
		this.ay = ddy;
	}
	@Override
	public float getSpeedX(){
		return this.sx;
	}
	@Override
	public float getSpeedY(){
		return this.sy;
	}
	@Override
	public abstract boolean isCrashed(IUnit _target);
	@Override
	public void initPosition(float x, float y){
		super.setPosition(x, y);
	}
}
