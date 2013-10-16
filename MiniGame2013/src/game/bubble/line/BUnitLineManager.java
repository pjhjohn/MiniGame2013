package game.bubble.line;

import game.bubble.unit.BUnitBall;

import org.pjhjohn.framework.resource.Drawable;

import android.graphics.Canvas;

public class BUnitLineManager {
	private BUnitLine[] lineArray;
	private int numOfLines;
	private boolean size;			// Line Contains #[true:8|false:7]
	private final int lineLimit = 12;
	public BUnitLineManager() {
		lineArray = new BUnitLine[lineLimit+1];
		numOfLines = 0;
		size = true;
	}
	public boolean pushDown(){		// True for Gameover
		if (++numOfLines > lineLimit) return true;
		for(int i = numOfLines; i > 0; i--){
			lineArray[i] = lineArray[i-1];
			if(lineArray[i]!=null) lineArray[i].incFloor();
		}
		lineArray[0] = new BUnitLine(size);
		size = !size;
		lineArray[0].fillLine();
		return false;
	}
	public void drawBall(Canvas canvas){
		for(BUnitLine line : lineArray) for(int i = 0; i < line.size(); i++) {
			if(line.get(i) != null) line.get(i).draw(canvas, Drawable.Align.CENTER);
		}
	}
	public boolean insertBall(BUnitBall mv){
		return true;
	}
}
