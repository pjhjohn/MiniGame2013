package game.dodge.resource;

import java.util.Random;

import org.pjhjohn.framework.main.AppManager;
import org.pjhjohn.framework.resource.AnimatableObj;

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
public class StarText extends AnimatableObj {
	// SuperType variables : (float)super.x | (float)super.y | (Bitmap)super.bmp
	// Member variables
	private String 	text;
	private Paint 	textPaint;
	private Rect 	textBound;
	private float 	textWidth;
	private float 	textHeight;
	
	private int 	numOfStars = 100;
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
	
	public float left, right, top, bottom;

	// Constructor
	public StarText(String text) {
		super();
		this.text = text;
		// Set Default super.bitmap
		bmp = Bitmap.createBitmap((int)AppManager.getDeviceWidth(), (int)AppManager.getDeviceHeight(), Bitmap.Config.ARGB_8888);
		Canvas tmpCanvas = new Canvas(bmp);
		Paint tmpPaint = new Paint();
		tmpPaint.setColor(Color.BLACK);
		tmpCanvas.drawRect(0, 0, AppManager.getDeviceWidth(), AppManager.getDeviceHeight(), tmpPaint);
		setBitmap(bmp);
		
		setTextSize(40);
		setStarNumber(this.numOfStars);
		setStarSize(1, 1);
	}
	
	// AnimateObj Override
	@Override
	public void draw(Canvas canvas, Align align){
		this.draw(canvas);
	}
	
	@Override
	public void draw(Canvas canvas){
		canvas.drawBitmap(bmp, srcRect, dstRect, null);
		for(int i=0; i<numOfStars; i++) stars[i].draw(canvas);

		mask = null;
		mask = Bitmap.createBitmap((int)this.dstRect.width(), (int)this.dstRect.height(), Bitmap.Config.ARGB_8888);
		Canvas canvasForMask = new Canvas(mask);
		canvasForMask.drawText(text,this.x,this.y,this.textPaint);
		Canvas resultCanvas = new Canvas(result);
		resultCanvas.drawBitmap(this.bmp, srcRect, dstRect, null);
		this.resultPaint.setFilterBitmap(false);
		this.resultPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
		resultCanvas.drawBitmap(mask, 0, 0, this.resultPaint);
		this.resultPaint.setXfermode(null);
		canvas.drawBitmap(result, 0, 0, null);
	}
	public void update(){
		for(int i=0; i<numOfStars; i++) stars[i].update();
	}
	@Override
	public void setBitmap(Bitmap _bitmap){
		super.setBitmap(_bitmap);
		this.srcRect = new Rect(0,0,bmp.getWidth(),bmp.getHeight());
		this.dstRect = new RectF(0,0,bmp.getWidth(),bmp.getHeight());
		this.result = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
	}
	@Override
	public void setPosition(float x, float y){
		super.x = x;
		super.y = y;
		this.setStarPosition();
	}
	
	// Local Methods
	public void setStarNumber(int numOfStars){
		this.numOfStars = numOfStars;
		stars = new Star[numOfStars];
		for(int i=0; i<stars.length; i++) stars[i] = new Star();
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
			case LEFT   : left = x; 			  right = x + textWidth; 	top = y + textBound.top; bottom = y + textBound.top + textHeight;	break;
			case CENTER : left = x - textWidth/2; right = x + textWidth/2;	top = y + textBound.top; bottom = y + textBound.top + textHeight;	break;
			case RIGHT  : left = x - textWidth;   right = x;				top = y + textBound.top; bottom = y + textBound.top + textHeight;	break;
			}
			stars[i].setBound(left, right, top, bottom);
			stars[i].setPosition(left + rand.nextFloat() * Math.abs(right - left), top + rand.nextFloat() * Math.abs(top - bottom));
		}
	}

	public void setTextSize(float size){
		this.resultPaint = new Paint();
		this.textPaint = new Paint();
		this.textPaint.setAntiAlias(true);
        this.textPaint.setARGB(255, 255, 255, 255);
        this.textPaint.setFakeBoldText(true);
        this.textPaint.setTypeface(Typeface.create(Typeface.SERIF,Typeface.ITALIC));
        setActualTextSize(size);
		this.textBound = new Rect();
		this.textPaint.getTextBounds(text, 0, text.length(), textBound);
		this.textWidth = textBound.width();
		this.textHeight = textBound.height();
	}
	
	public void setBackgroundSize(float width, float height){
		this.dstRect = new RectF(0,0,width,height);
	}
	public void setTextAlign(Paint.Align center){
		this.textPaint.setTextAlign(center);
		this.setStarPosition();
	}
	
	public void setStarSize(float width, float height){
		for(int i=0; i<numOfStars; i++) stars[i].setStarSize(width, height);
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
		return this.textBound.top;
	}
	private void setActualTextSize(float size_in){
		float size = 1;
		switch((int)size_in){
		case StarText.FILL_HORIZONTAL :
			while(true){
				this.textPaint.setTextSize(size);
				this.textPaint.getTextBounds(text, 0, text.length(), textBound);
				if(textBound.width()>AppManager.getDeviceWidth()){
					this.textPaint.setTextSize((float) (size - 0.5));
					break;
				} else size += 0.5;
			} return;
		case StarText.FILL_VERTICAL :
			while(true){
				this.textPaint.setTextSize(size);
				this.textPaint.getTextBounds(text, 0, text.length(), textBound);
				if(textBound.height()>AppManager.getDeviceHeight()){
					this.textPaint.setTextSize((float) (size - 0.5));
					break;
				} else size += 0.5;
			} return;			
		default : this.textPaint.setTextSize(size_in);
		}
	}
}