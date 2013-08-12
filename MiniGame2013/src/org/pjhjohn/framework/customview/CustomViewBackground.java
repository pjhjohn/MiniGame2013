package org.pjhjohn.framework.customview;

import java.util.Random;

import org.pjhjohn.framework.ImageObj;
import org.pjhjohn.framework.Option;
import org.pjhjohn.framework.widget.Star;

import android.content.Context;
import android.graphics.Canvas;

public class CustomViewBackground extends CustomView {
	private Star[]	mStars;
	private Random	mRand = new Random();
	public CustomViewBackground(Context context) {
		super(context);
		mStars = new Star[Option.Dodge.NUMBER_OF_STAR];
		for (int i = 0; i < Option.Dodge.NUMBER_OF_STAR; i++){
			mStars[i] = new Star();
			mStars[i].setRandomColor();
			mStars[i].setPosition(Option.getDeviceWidth()*mRand.nextFloat(), Option.getDeviceHeight()*mRand.nextFloat());
			mStars[i].setSpeed(0,2*(Option.Dodge.STAR_SPEED_MIN + Option.Dodge.STAR_SPEED_RANGE * mRand.nextFloat()));
		}
	}
	
	public void update(){
		for (int i = 0; i < mStars.length; i++) mStars[i].move();
	}
	@Override
	public void onDraw(Canvas _canvas) {
		for (int i = 0; i < Option.Dodge.NUMBER_OF_STAR; i++) this.mStars[i].draw(_canvas, ImageObj.Align.CENTER);
	}
}