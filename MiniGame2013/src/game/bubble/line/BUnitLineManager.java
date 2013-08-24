package game.bubble.line;

import org.pjhjohn.framework.drawable.IDrawable;

import android.graphics.Canvas;
import android.util.Log;

public class BUnitLineManager {
	private BUnitLine[] lineArr;
	private int lineNum;
	private boolean Size;//if true, create line contains 8, else 7
//	private static BUnitLineManager singleton = new BUnitLineManager();
	public BUnitLineManager() {
		lineArr= new BUnitLine[8];
		lineNum = 0;
		Size = true;
	}
//	public static BUnitLineManager getInstance(){ return singleton; }
	public boolean pushDown(){//true if gameover
		if (++lineNum>4)
			return true;
		for(int i=lineNum ; i>0 ;i--){
			lineArr[i]=lineArr[i-1];
			if(lineArr[i]!=null)
				lineArr[i].IncFloor();
		}
		lineArr[0] = new BUnitLine(Size);
		Size = !Size;
		lineArr[0].FillLine();
		return false;
	}
//	public void init(){ singleton = new BUnitLineManager(); 		Log.v("hi","hi");}
	public void drawBall(Canvas canvas){
		for(int i=0 ; i<lineNum ; i++){
			for(int j=0; j<lineArr[i].getContainerSize(); j++){
				if(lineArr[i].getElement(j)!=null)
					lineArr[i].getElement(j).draw(canvas, IDrawable.Align.CENTER);
			}
		}
	}
}
