package game.dodge.state;

import game.dodge.GameSpeedButton;
import game.dodge.unit.CUnitFactory;
import game.dodge.unit.CUnitTypePlayer;
import game.main.R;

import org.pjhjohn.framework.manager.AState;
import org.pjhjohn.framework.manager.AppManager;
import org.pjhjohn.framework.manager.IState;
import org.pjhjohn.framework.manager.SoundManager;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import app.main.AppOption;

// Disable Thread Enable Flag
public class CStateGameover extends AState {
	private SoundManager m_soundManager = SoundManager.getInstance();
	private boolean isActionDown;
	private boolean isSettingObjDown;	
	
	private static IState singleton = new CStateGameover();
	public  static IState getInstance(){ return singleton; }
	private CStateGameover(){ 
		super();
		this.isActionDown = false;
		this.isSettingObjDown = false;
		m_soundManager.init(AppManager.getContext());
		m_soundManager.addSound(0, R.raw.gamover);
	}
	@Override
	public boolean update(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			if(GameSpeedButton.getInstance().isHit(event.getX(), event.getY())) isSettingObjDown = true;
			isActionDown = true;
			break;
		case MotionEvent.ACTION_UP :
			if(isActionDown){
				if(isSettingObjDown){
					AlertDialog dialog = AppOption.getAlertDialog();
					dialog.show();  
										
					WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();  
					layoutParams.copyFrom(dialog.getWindow().getAttributes());  
					layoutParams.width = (int) (AppManager.getDeviceWidth()/(float)1.5);  
					layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;  
					
					Window window = dialog.getWindow();
					window.setAttributes(layoutParams);  
					window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  
				} else AppManager.setState(CStatePregame.getInstance());
			} isActionDown = false;
			isSettingObjDown = false;
		} return isActionDown;
	}
	@Override
	public void init() {
		Log.i("ApplicationState","Start Initializing"+this.toString());
		CUnitFactory.getInstance().create(CUnitTypePlayer.getInstance()).setBitmap(AppManager.getBitmap(R.drawable.ship_destroyed_mid));
		this.m_soundManager.play(0, SoundManager.PLAY_SINGLE);
	}
	@Override
	public void update(){
		this.gameManager.updateBackground();
	}
}