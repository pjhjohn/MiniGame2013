package org.pjhjohn.framework.animatable;

import java.util.Random;

import org.pjhjohn.framework.manager.AppManager;

import android.graphics.Canvas;
import android.util.Log;
import app.main.AppOption;

public class AnimatableObjBackground extends AnimatableObj {
	private Star[]	stars;
	private Random	rand = new Random();
	public AnimatableObjBackground(){
		Log.v("AnimatableObjBackground", "Constructor");
		stars = new Star[AppOption.Dodge.NUMBER_OF_STAR];
		for(int i = 0; i < stars.length; i++) {
			stars[i] = new Star();
			stars[i].setRandomColor();
			stars[i].setPosition(AppManager.getDeviceWidth()*rand.nextFloat(), AppManager.getDeviceHeight()*rand.nextFloat());
			stars[i].setSpeed(0, 2*(AppOption.Dodge.STAR_SPEED_MIN + AppOption.Dodge.STAR_SPEED_RANGE * rand.nextFloat()));
		}
		Log.v("AnimatableOBjBackground", "Star Created");
	}
	@Override
	public void update(){
		Log.v("AnimatableObjBackground", "update");
		if(stars==null) return;
		else for(int i = 0; i < stars.length; i++) stars[i].update();
	}
	@Override
	public void draw(Canvas canvas) {
		Log.v("AnimatableObjBackground", "draw");
		if(stars==null) return;
		else for(int i = 0; i < stars.length; i++) stars[i].draw(canvas);
	}
}