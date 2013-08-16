package game.bubble.line;

import game.bubble.unit.BUnitBall;
import game.bubble.unit.BUnitFactory;
import game.bubble.unit.BUnitFactory.UnitType;

import org.pjhjohn.framework.unit.AUnit;
import org.pjhjohn.framework.unit.IFactory;

public class BUnitLine {
	private IFactory unitFactory;
	private AUnit[] ballContainer;
	private int containerSize;
	private int remainingBall;
	private int floor;
	private float radius = BUnitBall.getRadius();
	public BUnitLine(boolean length) {
		unitFactory = (IFactory) BUnitFactory.getInstance();
		// TODO Auto-generated constructor stub
		if(length){
			containerSize = 8;
			ballContainer = new AUnit[8];
		}else{
			containerSize = 7;
			ballContainer = new AUnit[7];
		}
		floor = 0;
	}
	public void FillLine(){
		for(int i=0; i<containerSize ; i++){
			ballContainer[i]=unitFactory.create(UnitType.RAND);
			ballContainer[i].setPosition( radius * (2*i+1), (float)(radius * (1 + floor*Math.sqrt(3))));
		}
		remainingBall = containerSize;
	}
	public void IncFloor(){ 
		floor++;
		for(int i=0; i<containerSize ; i++)
			ballContainer[i].setPosition( radius * (2*i+1), (float)(radius * (1 + floor*Math.sqrt(3))));
	}
	public void Linking(BUnitLine underline){
		for(int i=0; i<containerSize-1 ; i++){
			if(((BUnitBall)ballContainer[i]).getBallType().equals(((BUnitBall)ballContainer[i+1]).getBallType())){
				((BUnitBall)ballContainer[i]).setRR(true);
				((BUnitBall)ballContainer[i+1]).setLL(true);
			}
		}
		if(underline != null){
			if(containerSize==7){
				for(int i=0; i<containerSize ; i++){
					if(underline.getElement(i)!=null && ((BUnitBall)ballContainer[i]).getBallType().equals((((BUnitBall) (underline.getElement(i))).getBallType()))){
						((BUnitBall)ballContainer[i]).setLD(true);
						((BUnitBall)(underline.getElement(i))).setRU(true);
					}
					if(underline.getElement(i+1)!=null &&((BUnitBall)ballContainer[i]).getBallType().equals((((BUnitBall) (underline.getElement(i+1))).getBallType()))){
						((BUnitBall)ballContainer[i]).setRD(true);
						((BUnitBall)(underline.getElement(i+1))).setLU(true);
					}
				}
			}else{
				if(underline.getElement(0)!=null && ((BUnitBall)ballContainer[0]).getBallType().equals((((BUnitBall) (underline.getElement(0))).getBallType()))){
					((BUnitBall)ballContainer[0]).setRD(true);
					((BUnitBall)(underline.getElement(0))).setLU(true);
				}
				for(int i=1; i<containerSize-1 ; i++){
					if(underline.getElement(i-1)!=null && ((BUnitBall)ballContainer[i]).getBallType().equals((((BUnitBall) (underline.getElement(i-1))).getBallType()))){
						((BUnitBall)ballContainer[i]).setLD(true);
						((BUnitBall)(underline.getElement(i-1))).setRU(true);
					}
					if(underline.getElement(i)!=null && ((BUnitBall)ballContainer[i]).getBallType().equals((((BUnitBall) (underline.getElement(i))).getBallType()))){
						((BUnitBall)ballContainer[i]).setRD(true);
						((BUnitBall)(underline.getElement(i))).setLU(true);
					}
				}
				if(underline.getElement(6)!=null && ((BUnitBall)ballContainer[7]).getBallType().equals((((BUnitBall) (underline.getElement(6))).getBallType()))){
					((BUnitBall)ballContainer[7]).setLD(true);
					((BUnitBall)(underline.getElement(6))).setRU(true);
				}
			}
		}
	}
	public void addBall(int i, BUnitBall _ball){
		if(i>=0 && i<containerSize && ballContainer[i]==null){
			ballContainer[i] = _ball;
			remainingBall++;
			//linking 추가하기!!
		}
	}
	public void removeBall(int i){ ballContainer[i]=null; }
	public AUnit[] getContainer(){ return ballContainer; }
	public AUnit getElement(int i){ return ballContainer[i]; }
	public boolean isEmpty() { return (remainingBall==0) ; }//비었으면 true
	
}
