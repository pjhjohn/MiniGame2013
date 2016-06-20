package game.framework.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
/*
 * Just add update & draw method : 
 * so that SurfaceContainer has sub-classes of THIS. 
 * 						&
 * SurfaceThread controls these Classes through SurfaceContainer.
 */
public abstract class CustomView extends View {
	public CustomView(Context context) {
		super(context);
	}
	public abstract void update();
	public void draw(Canvas canvas){
		this.onDraw(canvas);
	}
}
