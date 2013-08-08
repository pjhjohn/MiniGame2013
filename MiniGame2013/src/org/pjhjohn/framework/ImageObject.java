package org.pjhjohn.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ImageObject {
	protected Bitmap bitmap = null;
	protected float x = 0;
	protected float y = 0;
	public ImageObject(){}
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x - bitmap.getWidth()/2, y - bitmap.getHeight()/2, null);
	}
	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}
	public void setBitmap(Bitmap bitmap){
		this.bitmap = bitmap;
	}
	public Bitmap getBitmap(){
		return this.bitmap;
	}
	public float getDistance(ImageObject obj){
		float dx = this.x - obj.getX();
		float dy = this.y - obj.getY();
		return (float)Math.sqrt(dx*dx+dy*dy);
	}
	public boolean isInside(float x, float y){
		// TODO : IMPLEMENT IF NEEDED
		return false;
	}
}
