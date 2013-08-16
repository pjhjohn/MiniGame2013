package app.main;

import org.pjhjohn.framework.view.CustomView;
import org.pjhjohn.framework.view.CustomViewBackground;
import org.pjhjohn.framework.view.CustomViewStarText;
import org.pjhjohn.framework.view.CustomViewSurfaceContainer;

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
	private CustomView[] aCustomView;
	private CustomViewSurfaceContainer customViewContainer;
	
	public AppMainView(Context context) {
		super(context);
		
		aCustomView	   = new CustomView[2];
		aCustomView[0] = new CustomViewStarText(context, "MiniGame2013");		 aCustomView[0].setId(ID_STARTEXT);
		aCustomView[1] = new CustomViewBackground(getContext()); aCustomView[1].setId(ID_BACKGROUND);
		customViewContainer = new CustomViewSurfaceContainer(getContext(), aCustomView);
		this.addView(customViewContainer);
		
		btnSnowCraft	= makeButton("SnowCraft"   , ID_BTN_SNOWCRAFT, RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.CENTER_VERTICAL);
		btnMissleDodge	= makeButton("MissleDodge" , ID_BTN_MISSLEDODGE, RelativeLayout.ABOVE, btnSnowCraft.getId());
		btnPuzzleBubble	= makeButton("PuzzleBubble", ID_BTN_PUZZLEBUBBLE, RelativeLayout.RIGHT_OF, btnSnowCraft.getId());
		btnSetting		= makeButton("Application Setting", ID_BTN_SETTING, RelativeLayout.BELOW, btnSnowCraft.getId());
		this.addView(btnMissleDodge);
		this.addView(btnPuzzleBubble);
		this.addView(btnSnowCraft);
		this.addView(btnSetting);
	}
	private Button makeButton(String text, int id, int verb, int anchor){
		float scale = getContext().getResources().getDisplayMetrics().density;
		LayoutParams buttonLayoutParams = new LayoutParams((int)(160*scale), LayoutParams.WRAP_CONTENT);
		buttonLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		buttonLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		if(verb!=0)	buttonLayoutParams.addRule(verb, anchor);
		Button rtnBtn = new Button(getContext());
		rtnBtn.setBackgroundColor(Color.TRANSPARENT);
		rtnBtn.setLayoutParams(buttonLayoutParams);
		rtnBtn.setTypeface(null, Typeface.ITALIC);
		rtnBtn.setTextColor(Color.WHITE);
		rtnBtn.setTextSize(22);
		rtnBtn.setText(text);
		rtnBtn.setId(id);
		return rtnBtn;
	}
}