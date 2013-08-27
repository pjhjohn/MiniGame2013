package org.pjhjohn.framework.resource;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class DrawableObj implements Drawable{
	protected float x, y;
	protected Bitmap bmp;
	protected Paint pnt;
	public DrawableObj(){
		this.x = 0;
		this.y = 0;
		this.bmp = null;
		this.pnt = null;
	}
	@Override
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	@Override
	public void setX(float x){
		this.x = x;
	}
	@Override
	public void setY(float y){
		this.y = y;
	}
	@Override
	public float getX(){
		return this.x;
	}
	@Override
	public float getY(){
		return this.y;
	}	
	@Override
	public void setBitmap(Bitmap bitmap){
		this.bmp = bitmap;
	}
	@Override
	public Bitmap getBitmap(){
		return this.bmp;
	}
	@Override
	public void setPaint(Paint pnt){
		this.pnt = pnt;
	}
	@Override
	public Paint getPaint(){
		return this.pnt;
	}
	
	@Override
	public void draw(Canvas canvas){
		canvas.drawBitmap(this.bmp, this.x, this.y, this.pnt);
	}
	@Override
	public void draw(Canvas canvas, Align align){
		switch(align){
		case UPPER_LEFT  : canvas.drawBitmap(bmp, x, y, this.pnt);
		case UPPER_RIGHT : canvas.drawBitmap(bmp, x - bmp.getWidth(), y, this.pnt);
		case LOWER_LEFT  : canvas.drawBitmap(bmp, x, y - bmp.getHeight(), this.pnt);
		case LOWER_RIGHT : canvas.drawBitmap(bmp, x - bmp.getWidth(), y - bmp.getHeight(), this.pnt);
		case CENTER 		: canvas.drawBitmap(bmp, x - bmp.getWidth()/2, y - bmp.getHeight()/2, this.pnt); 
		}
	}

	// Local Methods
	public boolean isHit(float x, float y){ // IMPLEMENT IF NEEDED
		return false;
	}
	public float distanceTo(Drawable another){
		float dx = this.x - another.getX();
		float dy = this.y - another.getY();
		return (float)Math.sqrt(dx*dx+dy*dy);
	}
}
