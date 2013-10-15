package game.bubble.line;

import game.bubble.unit.BUnitBall;

import org.pjhjohn.framework.resource.Drawable;

import android.graphics.Canvas;

public class BUnitLineManager {
	private BUnitLine[] lineArr;
	private int lineNum;
	private boolean Size;			// Line Contains #[true:8|false:7]
	private final int lineLimit = 12;
	public BUnitLineManager() {
		lineArr = new BUnitLine[lineLimit+1];
		lineNum = 0;
		Size = true;
	}
	public boolean pushDown(){		// True for Gameover
		if (++lineNum>lineLimit)
			return true;
		for(int i = lineNum; i > 0; i--){
			lineArr[i] = lineArr[i-1];
			if(lineArr[i]!=null) lineArr[i].IncFloor();
		}
		lineArr[0] = new BUnitLine(Size);
		Size = !Size;
		lineArr[0].FillLine();
		return false;
	}
	public void drawBall(Canvas canvas){
		for(int i=0 ; i<lineNum ; i++){
			for(int j=0; j<lineArr[i].getContainerSize(); j++){
				if(lineArr[i].getElement(j)!=null)
					lineArr[i].getElement(j).draw(canvas, Drawable.Align.CENTER);
			}
		}
	}
	public boolean insertBall(BUnitBall mv){
		return true;
	}
}
