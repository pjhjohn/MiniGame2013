package game.dodge;

import game.main.R;

import org.pjhjohn.framework.main.AGameActivity;
import org.pjhjohn.framework.main.AppManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import app.main.AppOption;

public class DodgeGameActivity extends AGameActivity implements OnSeekBarChangeListener{
	private static TextView DIALOG_TEXT;
	private static SeekBar 	SEEKBAR;
	private static int 		INIT_PROGRESS;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppOption.setAlertDialog(getControllerSensitivityAlertDialog());
		setContentView(new DodgeGameView(this));
	}	
	
	//	Implement OnSeekBarChangeListener
	@Override
	public void onProgressChanged(SeekBar bar, int current, boolean userValid){
		AppManager.getController().setSensitivity(current, bar.getMax());
		DIALOG_TEXT.setText("LEVEL : " + bar.getProgress() + "  Value : " + AppManager.getController().getSensitivity());
		DIALOG_TEXT.invalidate();
	}
	@Override 
	public void onStartTrackingTouch(SeekBar bar){
		bar.setProgress((int) (bar.getMax() * AppManager.getController().getProgressRatio()));
	}
	@Override 
	public void onStopTrackingTouch(SeekBar bar){		
	}
	
	// Local Method
	private AlertDialog getControllerSensitivityAlertDialog(){
		View dialog_view = getLayoutInflater().inflate(R.layout.dialog_view, null);
		DIALOG_TEXT = (TextView)dialog_view.findViewById(R.id.textview);
		SEEKBAR = ((SeekBar)dialog_view.findViewById(R.id.seekbar));
		SEEKBAR.setOnSeekBarChangeListener(this);
		SEEKBAR.setProgress((int) (SEEKBAR.getMax() * AppManager.getController().getProgressRatio()));
		INIT_PROGRESS = SEEKBAR.getProgress();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose Sensitivity")
			   .setView(dialog_view)
			   .setPositiveButton("OK", new OnClickListener() {
				   @Override public void onClick(DialogInterface arg0, int arg1) {	INIT_PROGRESS = SEEKBAR.getProgress();	}
			   })
			   .setNegativeButton("CANCEL", new OnClickListener() {
				   @Override public void onClick(DialogInterface arg0, int arg1) {	SEEKBAR.setProgress(INIT_PROGRESS);		}
			   });
		return builder.create();
	}
}