package game.dodge;

import game.dodge.resource.AnimatableObjBackground;
import game.dodge.resource.AnimatableObjStarText;

import org.pjhjohn.framework.main.AnimatableSurfaceView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.RelativeLayout;

public class DodgeView extends RelativeLayout{
	public static final int ID_TOUCH 		= 0x10;
	public static final int ID_JOYSTIC 		= 0x11;
	public static final int ID_TILT 		= 0x12;
	
	private Button btnTouch;
	private Button btnCtrl;
	private Button btnTilt;
	private AnimatableSurfaceView surfaceView;
	
	public DodgeView(Context context) {
		super(context);
		surfaceView = new AnimatableSurfaceView(context, Color.BLACK);
		surfaceView.register("dodge", new AnimatableObjStarText("Dodge"));
		surfaceView.register("background", new AnimatableObjBackground());
		this.addView(surfaceView);
		
		btnTouch = makeButton("Touch" , ID_TOUCH, 0, 0);
		btnCtrl = makeButton("Joystic", ID_JOYSTIC, RelativeLayout.BELOW, btnTouch.getId());
		btnTilt = makeButton("Tilt"   , ID_TILT, RelativeLayout.BELOW, btnCtrl.getId());
		this.addView(btnTouch);
		this.addView(btnCtrl);
		this.addView(btnTilt);
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