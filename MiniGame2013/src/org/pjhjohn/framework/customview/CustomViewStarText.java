package org.pjhjohn.framework.customview;

import org.pjhjohn.framework.ImageObj;
import org.pjhjohn.framework.Option;
import org.pjhjohn.framework.widget.StarText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Align;

public class CustomViewStarText extends CustomView {
	private float textX = 400;
	private float textY = 180;
	private float textSize = 100;

	private float starSpeedMin = (float)0.5;
	private float starSpeedRange = (float)2.5;
	private int   starNumber = 5000;

	private StarText starText;

	public CustomViewStarText(Context context, String text) {
		super(context);
		init(text);
	}
	
	public void update(){
		starText.move();
	}
	@Override 
	public void onDraw(Canvas canvas){
		starText.draw(canvas, ImageObj.Align.CENTER);
	}
	
	private void init(String text){
		starText = new StarText(text);
		starText.setStarNumber(starNumber);
		starText.setStarSpeed(starSpeedMin, starSpeedRange);
		starText.setPosition(textX, textY);
		starText.setTextSize(textSize);
		starText.setBackgroundSize(Option.getDeviceWidth(), Option.getDeviceHeight());
		starText.setStarSize(1, 2);
		starText.setTextAlign(Align.CENTER);
	}
}
