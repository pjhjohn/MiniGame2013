package org.pjhjohn.framework.widget;

import org.pjhjohn.framework.ApplicationManager;
import org.pjhjohn.framework.Option;
import org.pjhjohn.framework.ImageObj;

import game.main.R;

public class SettingButton extends ImageObj {
	private static SettingButton singleton = new SettingButton();
	private SettingButton() {
		super();
		this.setBitmap(ApplicationManager.getBitmap(R.drawable.setting4));
		this.setPosition(this.bitmap.getWidth()/2,Option.getDeviceHeight()-this.bitmap.getHeight()/2);
	}
	public static SettingButton getInstance(){
		return singleton;
	}
	@Override
	public boolean isInside(float x, float y){
		return (y<=Option.getDeviceHeight())&&(x>=0)&&(y>=x+Option.getDeviceHeight()-this.bitmap.getWidth());
	}
}
