package game.snowcraft;

import game.dodge.resource.AnimatableObjBackground;
import game.dodge.resource.AnimatableObjStarText;

import org.pjhjohn.framework.view.AnimatableSurfaceView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SnowCraftView extends RelativeLayout{
	public static final int ID_BACKGROUND 	= 0x01;
	public static final int ID_BTN1 		= 0x10;
	public static final int ID_BTN2 		= 0x11;
	public static final int ID_BTN3 		= 0x12;
	public static final int ID_BTN4 		= 0x13;
	public static final int ID_STARTEXT 	= 0x20;
	
	private Button btnTouch;
	private Button btnCtrl;
	private Button btnTilt;
	private Button btnRank;
	private AnimatableSurfaceView surfaceView;
	
	public SnowCraftView(Context context) {
		super(context);
		surfaceView = new AnimatableSurfaceView(context, Color.BLACK);
		surfaceView.register("snow", new AnimatableObjStarText("SnowCraft"));
		surfaceView.register("background", new AnimatableObjBackground());
		this.addView(surfaceView);
		
		btnTouch = makeButton("Touch"  , ID_BTN1, 0, 0);
		btnCtrl = makeButton("Joystic", ID_BTN2, RelativeLayout.BELOW, btnTouch.getId());
		btnTilt = makeButton("Tilt"   , ID_BTN3, RelativeLayout.BELOW, btnCtrl.getId());
		btnRank = makeButton("Ranking", ID_BTN4, RelativeLayout.BELOW, btnTilt.getId());
		this.addView(btnTouch);
		this.addView(btnCtrl);
		this.addView(btnTilt);
		this.addView(btnRank);
	}
	private Button makeButton(String text, int id, int verb, int anchor){
		float scale = getContext().getResources().getDisplayMetrics().density;
		LayoutParams buttonLayoutParams = new LayoutParams((int)(110*scale), LayoutParams.WRAP_CONTENT);
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