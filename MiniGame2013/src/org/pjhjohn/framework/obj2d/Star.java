package org.pjhjohn.framework.obj2d;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import app.main.AppOption;

public class Star extends ImageObj {
	private float left=0, right=AppOption.getDeviceWidth(), top=0, bottom=AppOption.getDeviceHeight();
	private Paint paint = new Paint();
	private Random rand = new Random();
	private float dx;
	private float dy;
	private float width	= (float)1.0;
	private float height= (float)1.0;
	public void setRandomColor(){
		paint.setColor(Color.rgb(64+rand.nextInt(128),64+rand.nextInt(192),64+rand.nextInt(192)));
	}
	@Override
	public void draw(Canvas canvas, ImageObj.Align align){
		canvas.drawRect(posX, posY, posX+width, posY+height, paint);
	}
	public void setSpeed(float dx, float dy){
		this.dx = dx;
		this.dy = dy;
	}
	public void move(){
		this.setPosition(posX + dx, posY + dy);
	}
	@Override
	public void setPosition(float x, float y){
		if(x < left) this.posX = x + (right-left);
		else if(x <= right) this.posX = x;
		else this.posX = x - (right-left);

		if(y < top) this.posY = y + (bottom-top);
		else if(y <= bottom) this.posY = y;
		else this.posY = y - (bottom-top);
	}
	public void setBound(float left, float right, float top, float bottom){
		this.left = left;	this.right = right;	this.top = top;	this.bottom = bottom;
	}
	public void setStarSize(float width, float height){
		this.width = width;
		this.height = height;
	}
}
