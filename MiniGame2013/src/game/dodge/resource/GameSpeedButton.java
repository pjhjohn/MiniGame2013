package game.dodge.resource;

import game.main.R;

import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.resource.DrawableObj;

public class GameSpeedButton extends DrawableObj {
	private static GameSpeedButton singleton = new GameSpeedButton();
	private GameSpeedButton() {
		super();
		this.setBitmap(AppManager.getBitmap(R.drawable.setting4));
		this.setPosition(this.bmp.getWidth()/2,AppManager.getDeviceHeight()-this.bmp.getHeight()/2);
	}
	public static GameSpeedButton getInstance(){
		return singleton;
	}
	@Override
	public boolean isHit(float x, float y){
		return (y<=AppManager.getDeviceHeight())&&(x>=0)&&(y>=x+AppManager.getDeviceHeight()-this.bmp.getWidth());
	}
}
