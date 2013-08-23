package app.main;

import org.pjhjohn.framework.animatable.AnimatableObjBackground;
import org.pjhjohn.framework.animatable.AnimatableObjStarText;
import org.pjhjohn.framework.animatable.AnimatableSurfaceView;
import org.pjhjohn.framework.manager.AppManager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.RelativeLayout;

public class AppMainView extends RelativeLayout{
	public static final int ID_BACKGROUND 		= 0x01;
	public static final int ID_BTN_MISSLEDODGE 	= 0x10;
	public static final int ID_BTN_PUZZLEBUBBLE = 0x11;
	public static final int ID_BTN_SNOWCRAFT 	= 0x12;
	public static final int ID_BTN_SETTING 		= 0x13;
	public static final int ID_STARTEXT			= 0x20;
	
	private Button btnMissleDodge;
	private Button btnPuzzleBubble;
	private Button btnSnowCraft;
	private Button btnSetting;
	private AnimatableSurfaceView surfaceView;
	
	public AppMainView(Context context) {
		super(context);
		// Register SurfaceView
		surfaceView = new AnimatableSurfaceView(getContext(), Color.BLACK);
		surfaceView.register("2013", new AnimatableObjStarText("MiniGame2013"));
		surfaceView.register("background", new AnimatableObjBackground());
		this.addView(surfaceView);
		// Register Normal Views
		btnSnowCraft	= makeButton("SnowCraft"   , ID_BTN_SNOWCRAFT, RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.CENTER_VERTICAL);
		btnMissleDodge	= makeButton("MissleDodge" , ID_BTN_MISSLEDODGE, RelativeLayout.ABOVE, btnSnowCraft.getId());
		btnPuzzleBubble	= makeButton("PuzzleBubble", ID_BTN_PUZZLEBUBBLE, RelativeLayout.BELOW, btnSnowCraft.getId());
		btnSetting		= makeButton("Application Setting", ID_BTN_SETTING, RelativeLayout.BELOW, btnPuzzleBubble.getId());
		this.addView(btnMissleDodge);
		this.addView(btnPuzzleBubble);
		this.addView(btnSnowCraft);
		this.addView(btnSetting);
	}
	private Button makeButton(String text, int id, int verb, int anchor){
		LayoutParams buttonLayoutParams = new LayoutParams(AppManager.dp2px(160), LayoutParams.WRAP_CONTENT);
		buttonLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		buttonLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		if(verb!=0)	buttonLayoutParams.addRule(verb, anchor);
		Button button = new Button(getContext());
		button.setBackgroundColor(Color.TRANSPARENT);
		button.setLayoutParams(buttonLayoutParams);
		button.setTypeface(null, Typeface.ITALIC);
		button.setTextColor(Color.WHITE);
		button.setTextSize(22);
		button.setText(text);
		button.setId(id);
		return button;
	}
}