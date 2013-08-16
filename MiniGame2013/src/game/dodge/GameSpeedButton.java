package game.dodge;

import game.main.R;

import org.pjhjohn.framework.obj2d.ImageObj;
import org.pjhjohn.manager.AppManager;

import app.main.AppOption;

public class GameSpeedButton extends ImageObj {
	private static GameSpeedButton singleton = new GameSpeedButton();
	private GameSpeedButton() {
		super();
		this.setBitmap(AppManager.getBitmap(R.drawable.setting4));
		this.setPosition(this.bitmap.getWidth()/2,AppOption.getDeviceHeight()-this.bitmap.getHeight()/2);
	}
	public static GameSpeedButton getInstance(){
		return singleton;
	}
	@Override
	public boolean isInside(float x, float y){
		return (y<=AppOption.getDeviceHeight())&&(x>=0)&&(y>=x+AppOption.getDeviceHeight()-this.bitmap.getWidth());
	}
}
