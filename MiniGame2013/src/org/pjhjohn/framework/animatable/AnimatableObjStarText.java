package org.pjhjohn.framework.animatable;


import org.pjhjohn.framework.manager.AppManager;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class AnimatableObjStarText extends AnimatableObj {
	private float textX = 400;
	private float textY = 180;
	private float textSize = 100;

	private float starSpeedMin = (float)0.5;
	private float starSpeedRange = (float)2.5;
	private int   starNumber = 5000;

	private StarText starText;
	public AnimatableObjStarText(String text) {
		init(text);
	}
	@Override
	public void update(){
		starText.update();
	}
	@Override
	public void draw(Canvas canvas){
		Log.i("AnimatableObjStarText", "Before Draw");
		starText.draw(canvas);
		Log.i("AnimatableObjStarText", "After Draw");
	}
	
	// Local Method
	private void init(String text){
		starText = new StarText(text);
		starText.setStarNumber(starNumber);
		starText.setStarSpeed(starSpeedMin, starSpeedRange);
		starText.setPosition(textX, textY);
		starText.setTextSize(textSize);
		starText.setBackgroundSize(AppManager.getDeviceWidth(), AppManager.getDeviceHeight());
		starText.setStarSize(1, 2);
		starText.setTextAlign(Paint.Align.CENTER);
	}
}
