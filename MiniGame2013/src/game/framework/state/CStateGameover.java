package game.framework.state;

import game.framework.ApplicationManager;
import game.framework.ApplicationOption;
import game.framework.SettingButton;
import game.framework.SoundManager;
import game.framework.unit.CUnitFactory;
import game.main.R;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
		m_soundManager.init(ApplicationManager.getContext());
		m_soundManager.addSound(0, R.raw.gamover);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			if(SettingButton.getInstance().isInside(event.getX(), event.getY())) isSettingObjDown = true;
			isActionDown = true;
			break;
		case MotionEvent.ACTION_UP :
			if(isActionDown){
				if(isSettingObjDown){
					AlertDialog dialog = ApplicationManager.getAlertDialog();
					WindowManager.LayoutParams lp = new WindowManager.LayoutParams();  
					lp.copyFrom(dialog.getWindow().getAttributes());  
					lp.width = (int) (ApplicationOption.getDeviceWidth()/(float)1.5);  
					lp.height = WindowManager.LayoutParams.WRAP_CONTENT;  
					dialog.show();  
					Window window = dialog.getWindow();  
					window.setAttributes(lp);  
					window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  
				} else gameManager.setCurrentState(CStatePregame.getInstance());
			} isActionDown = false;
			isSettingObjDown = false;
		} return isActionDown;
	}
	@Override
	public void init() {
		Log.i("ApplicationState","Start Initializing"+this.toString());
		CUnitFactory.getInstance().create(CUnitFactory.UnitType.PLAYER).setBitmap(ApplicationManager.getBitmap(R.drawable.ship_destroyed_mid));
		this.m_soundManager.play(0, SoundManager.PLAY_SINGLE);
	}
	@Override
	public void update(){
		this.gameManager.updateBackground();
	}
}