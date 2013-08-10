package game.bubble;

import org.pjhjohn.framework.customview.CustomView;
import org.pjhjohn.framework.customview.CustomViewBackground;
import org.pjhjohn.framework.customview.CustomViewStarText;
import org.pjhjohn.framework.customview.CustomViewSurfaceContainer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BubbleView extends RelativeLayout{
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
	private CustomView[] surfaceViews;
	private CustomViewSurfaceContainer surfContainer;
	
	public BubbleView(Context context) {
		super(context);
		surfaceViews = new CustomView[2];
		surfaceViews[0] = new CustomViewStarText(context, "PuzzleBubble");			surfaceViews[0].setId(ID_STARTEXT);
		surfaceViews[1] = new CustomViewBackground(getContext());	surfaceViews[1].setId(ID_BACKGROUND);
		surfContainer = new CustomViewSurfaceContainer(getContext(), surfaceViews);
		this.addView(surfContainer);
		
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