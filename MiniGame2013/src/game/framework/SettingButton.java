package game.framework;

import game.main.R;

public class SettingButton extends ImageObject {
	private static SettingButton singleton = new SettingButton();
	private SettingButton() {
		super();
		this.setBitmap(ApplicationManager.getBitmap(R.drawable.setting4));
		this.setPosition(this.bitmap.getWidth()/2,ApplicationOption.getDeviceHeight()-this.bitmap.getHeight()/2);
	}
	public static SettingButton getInstance(){
		return singleton;
	}
	@Override
	public boolean isInside(float x, float y){
		return (y<=ApplicationOption.getDeviceHeight())&&(x>=0)&&(y>=x+ApplicationOption.getDeviceHeight()-this.bitmap.getWidth());
	}
}
