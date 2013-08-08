package org.pjhjohn.framework.customview;

import org.pjhjohn.framework.ApplicationOption;
import org.pjhjohn.framework.StarText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Align;

public class CustomViewStarText extends CustomView {
	private float textX = 400;
	private float textY = 180;
	private float textSize = 100;

	private float starSpeedMin = 1;
	private float starSpeedRange = 3;
	private int   starNumber = 10000;

	private StarText starText;

	public CustomViewStarText(Context context) {
		super(context);
		init();
	}
	
	public void update(){
		starText.move();
	}
	@Override 
	public void onDraw(Canvas canvas){
		starText.draw(canvas);
	}
	
	private void init(){
		starText = new StarText("Missile Dodge");
		starText.setStarNumber(starNumber);
		starText.setStarSpeed(starSpeedMin, starSpeedRange);
		starText.setPosition(textX, textY);
		starText.setTextSize(textSize);
		starText.setBackgroundSize(ApplicationOption.getDeviceWidth(), ApplicationOption.getDeviceHeight());
		starText.setStarSize(1, 2);
		starText.setTextAlign(Align.CENTER);
	}
}
