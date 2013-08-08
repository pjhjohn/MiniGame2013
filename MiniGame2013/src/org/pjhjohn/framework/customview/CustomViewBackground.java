package org.pjhjohn.framework.customview;

import java.util.Random;

import org.pjhjohn.framework.ApplicationOption;
import org.pjhjohn.framework.Star;

import android.content.Context;
import android.graphics.Canvas;

public class CustomViewBackground extends CustomView {
	private Star[]	mStars;
	private Random	mRand = new Random();
	public CustomViewBackground(Context context) {
		super(context);
		mStars = new Star[ApplicationOption.NUMBER_OF_STAR];
		for (int i = 0; i < ApplicationOption.NUMBER_OF_STAR; i++){
			mStars[i] = new Star();
			mStars[i].setRandomColor();
			mStars[i].setPosition(ApplicationOption.getDeviceWidth()*mRand.nextFloat(), ApplicationOption.getDeviceHeight()*mRand.nextFloat());
			mStars[i].setSpeed(0,2*(ApplicationOption.STAR_SPEED_MIN + ApplicationOption.STAR_SPEED_RANGE * mRand.nextFloat()));
		}
	}
	
	public void update(){
		for (int i = 0; i < mStars.length; i++) mStars[i].move();
	}
	@Override
	public void onDraw(Canvas _canvas) {
		for (int i = 0; i < ApplicationOption.NUMBER_OF_STAR; i++) this.mStars[i].draw(_canvas);
	}
}