package game.dodge;

import game.main.R;

import org.pjhjohn.framework.obj2d.ImageObj;
import org.pjhjohn.manager.AppManager;

public class GameSpeedButton extends ImageObj {
	private static GameSpeedButton singleton = new GameSpeedButton();
	private GameSpeedButton() {
		super();
		this.setBitmap(AppManager.getBitmap(R.drawable.setting4));
		this.setPosition(this.bitmap.getWidth()/2,AppManager.getDeviceHeight()-this.bitmap.getHeight()/2);
	}
	public static GameSpeedButton getInstance(){
		return singleton;
	}
	@Override
	public boolean isInside(float x, float y){
		return (y<=AppManager.getDeviceHeight())&&(x>=0)&&(y>=x+AppManager.getDeviceHeight()-this.bitmap.getWidth());
	}
}
