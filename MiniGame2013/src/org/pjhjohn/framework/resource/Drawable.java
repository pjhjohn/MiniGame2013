package org.pjhjohn.framework.resource;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
/* [pjhjohn]
 * Contains basic x,y location, bitmap resource, draw method
 */
public interface Drawable {
	public static enum Align {
		CENTER, UPPER_LEFT, UPPER_RIGHT, LOWER_LEFT, LOWER_RIGHT
	}
	// Position
	public void setPosition(float x, float y);
	public void setX(float x);
	public void setY(float y);
	public float getX();
	public float getY();
	// Resource
	public void setBitmap(Bitmap bmp);
	public Bitmap getBitmap();
	public void setPaint(Paint pnt);
	public Paint getPaint();
	// Draw
	public void draw(Canvas canvas);
	public void draw(Canvas canvas, Align align);
}