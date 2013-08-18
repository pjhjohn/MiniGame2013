package org.pjhjohn.framework.view;

import java.util.Random;

import org.pjhjohn.framework.obj2d.ImageObj;
import org.pjhjohn.framework.obj2d.Star;
import org.pjhjohn.manager.AppManager;

import android.content.Context;
import android.graphics.Canvas;
import app.main.AppOption;

public class CustomViewBackground extends CustomView {
	private Star[]	mStars;
	private Random	mRand = new Random();
	public CustomViewBackground(Context context) {
		super(context);
		mStars = new Star[AppOption.Dodge.NUMBER_OF_STAR];
		for (int i = 0; i < AppOption.Dodge.NUMBER_OF_STAR; i++){
			mStars[i] = new Star();
			mStars[i].setRandomColor();
			mStars[i].setPosition(AppManager.getDeviceWidth()*mRand.nextFloat(), AppManager.getDeviceHeight()*mRand.nextFloat());
			mStars[i].setSpeed(0,2*(AppOption.Dodge.STAR_SPEED_MIN + AppOption.Dodge.STAR_SPEED_RANGE * mRand.nextFloat()));
		}
	}
	
	public void update(){
		for (int i = 0; i < mStars.length; i++) mStars[i].move();
	}
	@Override
	public void onDraw(Canvas _canvas) {
		for (int i = 0; i < AppOption.Dodge.NUMBER_OF_STAR; i++) this.mStars[i].draw(_canvas, ImageObj.Align.CENTER);
	}
}