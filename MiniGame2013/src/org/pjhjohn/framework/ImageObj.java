package org.pjhjohn.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class ImageObj {
	protected Bitmap bitmap = null;
	protected float posX = 0;
	protected float posY = 0;
	public static enum Align {
		CENTER, LOWER_LEFT, LOWER_RIGHT, UPPER_LEFT, UPPER_RIGHT
	}

	public void setPosition(float x, float y){
		this.posX = x;
		this.posY = y;
	}
	public float getX(){
		return this.posX;
	}
	public float getY(){
		return this.posY;
	}	
	
	public void setBitmap(Bitmap bitmap){
		this.bitmap = bitmap;
	}
	public Bitmap getBitmap(){
		return this.bitmap;
	}
	
	public float getDistance(ImageObj obj){
		float dx = this.posX - obj.getX();
		float dy = this.posY - obj.getY();
		return (float)Math.sqrt(dx*dx+dy*dy);
	}
	public boolean isInside(float x, float y){ // IMPLEMENT IF NEEDED
		return false;
	}
	

	public void draw(Canvas canvas, ImageObj.Align align){
		switch(align){
		case UPPER_LEFT  : canvas.drawBitmap(bitmap, posX, posY, null);
		case UPPER_RIGHT : canvas.drawBitmap(bitmap, posX - bitmap.getWidth(), posY, null);
		case LOWER_LEFT  : canvas.drawBitmap(bitmap, posX, posY - bitmap.getHeight(), null);
		case LOWER_RIGHT : canvas.drawBitmap(bitmap, posX - bitmap.getWidth(), posY - bitmap.getHeight(), null);
		case CENTER 	 : canvas.drawBitmap(bitmap, posX - bitmap.getWidth()/2, posY - bitmap.getHeight()/2, null); 
		}
	}
}
