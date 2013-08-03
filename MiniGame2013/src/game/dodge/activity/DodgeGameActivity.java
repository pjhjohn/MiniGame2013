package game.dodge.activity;

import game.framework.ApplicationManager;
import game.dodge.main.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class DodgeGameActivity extends Activity implements OnSeekBarChangeListener{
	private DodgeGameView 	m_view;
	private static TextView DIALOG_TEXT;
	private static SeekBar 	SEEKBAR;
	private static int 		INIT_PROGRESS;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		// Set Resource - ResourceManager.getContext()
		ApplicationManager.setContext(this);
		ApplicationManager.setAlertDialog(getControllerSensitivityAlertDialog());
		// Set ControllerMode
		m_view = new DodgeGameView(this);
		setContentView(m_view);
	}
	protected void onDestroy(){
		super.onDestroy();
		m_view.onDestroy();		// unregister sensors
	}
	
	//	Implement OnSeekBarChangeListener
	@Override
	public void onProgressChanged(SeekBar _seekbar, int _current, boolean _userValid) {
		ApplicationManager.getController().setSensitivity(_current, _seekbar.getMax());
		DIALOG_TEXT.setText("LEVEL : "+_seekbar.getProgress()+"  Value : "+ApplicationManager.getController().getSensitivity());
		DIALOG_TEXT.invalidate();
	}
	@Override public void onStartTrackingTouch(SeekBar _seekbar) {
		_seekbar.setProgress((int) (_seekbar.getMax() * ApplicationManager.getController().getProgressRatio()));
	}
	@Override public void onStopTrackingTouch(SeekBar _seekbar) {}
	private AlertDialog getControllerSensitivityAlertDialog(){
		View dialog_view = getLayoutInflater().inflate(R.layout.dialog_view, null);
		DIALOG_TEXT = (TextView)dialog_view.findViewById(R.id.textview);
		SEEKBAR = ((SeekBar)dialog_view.findViewById(R.id.seekbar));
		SEEKBAR.setOnSeekBarChangeListener(this);
		SEEKBAR.setProgress((int) (SEEKBAR.getMax() * ApplicationManager.getController().getProgressRatio()));
		INIT_PROGRESS = SEEKBAR.getProgress();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Choose Sensitivity");
		builder.setView(dialog_view);
		builder.setPositiveButton("OK", new OnClickListener() {
			@Override public void onClick(DialogInterface arg0, int arg1) {
				INIT_PROGRESS = SEEKBAR.getProgress();
			}
		});
		builder.setNegativeButton("CANCEL", new OnClickListener() {
			@Override public void onClick(DialogInterface arg0, int arg1) {
				SEEKBAR.setProgress(INIT_PROGRESS);
			}
		});
		return builder.create();
	}
}