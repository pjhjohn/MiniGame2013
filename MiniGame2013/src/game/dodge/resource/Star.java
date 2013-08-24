package game.dodge.resource;

import java.util.Random;

import org.pjhjohn.framework.animatable.AnimatableObj;
import org.pjhjohn.framework.manager.AppManager;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Star extends AnimatableObj{
	private float left, right, top, bottom, dx, dy, width, height;
	private Random rand;
	public Star(){
		super();
		super.pnt = new Paint();
		this.rand = new Random();
		left = top = 0;
		right=AppManager.getDeviceWidth();
		bottom=AppManager.getDeviceHeight();
		width = height = (float) 1.0;
		this.setRandomColor();
	}
	// AnimatableObj Override
	@Override
	public void draw(Canvas canvas, Align align){
		this.draw(canvas);
	}
	@Override
	public void draw(Canvas canvas){
		canvas.drawRect(x, y, x+width, y+height, super.pnt);
	}
	@Override
	public void update(){
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
	
	// Local Methods
	public void setRandomColor(){
		super.pnt.setColor(Color.rgb(64+rand.nextInt(128),64+rand.nextInt(192),64+rand.nextInt(192)));
	}
	public void setSpeed(float dx, float dy){
		this.dx = dx;
		this.dy = dy;
	}
	public void setBound(float left, float right, float top, float bottom){
		this.left = left;	this.right = right;	this.top = top;	this.bottom = bottom;
	}
	public void setStarSize(float width, float height){
		this.width = width;
		this.height = height;
	}
}
