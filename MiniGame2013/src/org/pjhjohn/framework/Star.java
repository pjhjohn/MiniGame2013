package org.pjhjohn.framework;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Star extends ImageObject {
	private float left=0, right=ApplicationOption.getDeviceWidth(), top=0, bottom=ApplicationOption.getDeviceHeight();
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
	public void draw(Canvas canvas){
		canvas.drawRect(x, y, x+width, y+height, paint);
	}
	public void setSpeed(float dx, float dy){
		this.dx = dx;
		this.dy = dy;
	}
	public void move(){
		this.setPosition(x + dx, y + dy);
	}
	@Override
	public void setPosition(float x, float y){
		if(x < left) this.x = x + (right-left);
		else if(x <= right) this.x = x;
		else this.x = x - (right-left);

		if(y < top) this.y = y + (bottom-top);
		else if(y <= bottom) this.y = y;
		else this.y = y - (bottom-top);
	}
	public void setBound(float left, float right, float top, float bottom){
		this.left = left;	this.right = right;	this.top = top;	this.bottom = bottom;
	}
	public void setStarSize(float width, float height){
		this.width = width;
		this.height = height;
	}
}
