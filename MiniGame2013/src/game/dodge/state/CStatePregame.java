package game.dodge.state;

import game.dodge.GameSpeedButton;
import game.dodge.unit.CUnitFactory;
import game.main.R;

import org.pjhjohn.framework.state.AState;
import org.pjhjohn.framework.state.IState;
import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.manager.AppManager;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import app.main.AppOption;


// Disable Previous Thread & Assign New Thread
public class CStatePregame extends AState {
	private boolean isActionDown = false;
	private boolean isSettingBtnDown = false;
	private CStatePregame(){ super(); }
	private static IState singleton = new CStatePregame();
	public  static IState getInstance(){ return singleton; }	
	@Override
	public void init() {
		// Initialize player unit
		AUnit player = CUnitFactory.getInstance().create(CUnitFactory.UnitType.PLAYER);
		player.setBitmap(AppManager.getBitmap(R.drawable.ship));
		player.setSpeedX(0);
		player.setSpeedY(0);
		player.setAccX(0);
		player.setAccY(0);
		// Initialize controller unit
		AppManager.getController().init();
		// Initialize game Process
		this.gameManager.onGameReady();
	}
	// Touch SettingBtn : Alert Dialog | Other : To CStatePlaying
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			if(GameSpeedButton.getInstance().isInside(event.getX(), event.getY())) isSettingBtnDown = true;
			isActionDown = true;
			break;
		case MotionEvent.ACTION_UP :
			if(isActionDown){
				if(isSettingBtnDown){
					AlertDialog dialog = AppManager.getAlertDialog();
					WindowManager.LayoutParams lp = new WindowManager.LayoutParams();  
					lp.copyFrom(dialog.getWindow().getAttributes());  
					lp.width = (int) (AppOption.getDeviceWidth()/(float)1.5);  
					lp.height = WindowManager.LayoutParams.WRAP_CONTENT;  
					dialog.show();
					Window window = dialog.getWindow();  
					window.setAttributes(lp);  
					window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  
				} else gameManager.setState(CStatePlaying.getInstance());
			} isActionDown = false;
			isSettingBtnDown = false;
		} return isActionDown;
	}
	@Override
	public void update(){
		this.gameManager.updateBackground();
	}
}