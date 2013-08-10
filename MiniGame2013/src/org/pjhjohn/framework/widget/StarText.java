package org.pjhjohn.framework.widget;

import java.util.Random;

import org.pjhjohn.framework.Option;
import org.pjhjohn.framework.ImageObj;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

/* StarText : drilling text-shaped hole in the background with falling stars behind.						 *
 * To provide this as a meaningful library component, this should be provided as subclass of ANDROID.VIEW	 * 
 * Study view-hierachy, and make it. [NAME'LL BE STARVIEW : contains StarViewThread]						 */
public class StarText extends ImageObj {
	// SuperType variables : (float)super.x | (float)super.y | (Bitmap)super.bitmap
	private String 	text;
	private Paint 	textPaint;
	private Rect 	textBounds;
	private float 	textWidth;
	private float 	textHeight;
	
	private int 	starNumber = 100;
	private float 	starSpeedMin = 1;
	private float 	starSpeedRange = 4;
	private Star[] 	stars;
	
	private final Random rand = new Random();
	
	public static final int FILL_HORIZONTAL = -1;
	public static final int FILL_VERTICAL = -2;
	
	private Bitmap 	mask;
	private Bitmap 	result;
	private Paint 	resultPaint;
	private Rect	srcRect;
	private RectF 	dstRect;
	
	public float left;
	public float right;
	public float top;
	public float bottom;
	
	public void setStarNumber(int num){
		this.starNumber = num;
		stars = new Star[num];
		for(int i=0; i<stars.length; i++){
			stars[i] = new Star();
			stars[i].setRandomColor();
		}
		setStarSpeed(starSpeedMin, starSpeedRange);
		setStarPosition();
	}
	public void setStarSpeed(float min, float range){
		this.starSpeedMin = min;
		this.starSpeedRange = range;
		for(int i=0; i<stars.length; i++) stars[i].setSpeed(0, starSpeedMin + starSpeedRange * rand.nextFloat());
	}
	private void setStarPosition(){
		for(int i=0; i<stars.length; i++){
			switch(this.textPaint.getTextAlign()){
			case LEFT   : left = posX; 			  right = posX + textWidth; 	top = posY + textBounds.top; bottom = posY + textBounds.top + textHeight;	break;
			case CENTER : left = posX - textWidth/2; right = posX + textWidth/2;top = posY + textBounds.top; bottom = posY + textBounds.top + textHeight;	break;
			case RIGHT  : left = posX - textWidth;   right = posX;				top = posY + textBounds.top; bottom = posY + textBounds.top + textHeight;	break;
			}
			stars[i].setBound(left, right, top, bottom);
			stars[i].setPosition(left + rand.nextFloat() * Math.abs(right - left), top + rand.nextFloat() * Math.abs(top - bottom));
		}
	}
	public StarText(String text) {
		this.text = text;
		// Set Default super.bitmap
		bitmap = Bitmap.createBitmap((int)Option.getDeviceWidth(), (int)Option.getDeviceHeight(), Bitmap.Config.ARGB_8888);
		Canvas cTemp = new Canvas(bitmap);
		Paint pTemp = new Paint();
		pTemp.setColor(Color.BLACK);
		cTemp.drawRect(0, 0, Option.getDeviceWidth(), Option.getDeviceHeight(), pTemp);
		setBitmap(bitmap);
		
		setTextSize(40);
		setStarNumber(this.starNumber);
		setStarSize(1,1);
	}
	public void setTextSize(float size){
		this.resultPaint = new Paint();
		this.textPaint = new Paint();
		this.textPaint.setAntiAlias(true);
        this.textPaint.setARGB(255, 255, 255, 255);
        this.textPaint.setFakeBoldText(true);
        this.textPaint.setTypeface(Typeface.create(Typeface.SERIF,Typeface.ITALIC));
        setActualTextSize(size);
		this.textBounds = new Rect();
		this.textPaint.getTextBounds(text, 0, text.length(), textBounds);
		this.textWidth = textBounds.width();
		this.textHeight = textBounds.height();
	}
	@Override
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, srcRect, dstRect, null);
		for(int i=0; i<starNumber; i++) stars[i].draw(canvas);

		mask = null;
		mask = Bitmap.createBitmap((int)this.dstRect.width(), (int)this.dstRect.height(), Bitmap.Config.ARGB_8888);
		Canvas canvasForMask = new Canvas(mask);
		canvasForMask.drawText(text,this.posX,this.posY,this.textPaint);

		Canvas resultCanvas = new Canvas(result);
		resultCanvas.drawBitmap(this.bitmap, srcRect, dstRect, null);
		this.resultPaint.setFilterBitmap(false);
		this.resultPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
		resultCanvas.drawBitmap(mask, 0, 0, this.resultPaint);
		this.resultPaint.setXfermode(null);

		canvas.drawBitmap(result, 0, 0, null);
	}
	public void move(){
		for(int i=0; i<starNumber; i++) stars[i].move();
	}
	@Override
	public void setBitmap(Bitmap _bitmap){
		super.setBitmap(_bitmap);
		this.srcRect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
		this.dstRect = new RectF(0,0,bitmap.getWidth(),bitmap.getHeight());
		this.result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
	}
	public void setBackgroundSize(float width, float height){
		this.dstRect = new RectF(0,0,width,height);
	}
	public void setTextAlign(Paint.Align align){
		this.textPaint.setTextAlign(align);
		this.setStarPosition();
	}
	@Override
	public void setPosition(float x, float y){
		this.posX = x;
		this.posY = y;
		this.setStarPosition();
	}
	public void setStarSize(float width, float height){
		for(int i=0; i<starNumber; i++) stars[i].setStarSize(width, height);
	}
	public float getWidth(){
		return Math.abs(left - right);
	}
	public float getHeight(){
		return Math.abs(top - bottom);
	}
	public float getTextHeight(){
		return this.textHeight;
	}
	public float getTextBoundTop(){
		return this.textBounds.top;
	}
	private void setActualTextSize(float size_in){
		float size = 1;
		switch((int)size_in){
		case StarText.FILL_HORIZONTAL :
			while(true){
				this.textPaint.setTextSize(size);
				this.textPaint.getTextBounds(text, 0, text.length(), textBounds);
				if(textBounds.width()>Option.getDeviceWidth()){
					this.textPaint.setTextSize((float) (size - 0.5));
					break;
				} else size += 0.5;
			} return;
		case StarText.FILL_VERTICAL :
			while(true){
				this.textPaint.setTextSize(size);
				this.textPaint.getTextBounds(text, 0, text.length(), textBounds);
				if(textBounds.height()>Option.getDeviceHeight()){
					this.textPaint.setTextSize((float) (size - 0.5));
					break;
				} else size += 0.5;
			} return;			
		default : this.textPaint.setTextSize(size_in);
		}
	}
}