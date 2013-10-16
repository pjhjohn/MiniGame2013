package game.bubble.line;

import game.bubble.unit.BUnitBall;
import game.bubble.unit.BUnitFactory;
import game.bubble.unit.BUnitTypeRandBall;

import org.pjhjohn.framework.resource.AUnit;
import org.pjhjohn.framework.resource.IFactory;

public class BUnitLine {
	private IFactory unitFactory;
	private BUnitBall[] container;
	private int remainingBall;
	private int floor;
	private float radius = BUnitBall.getRadius();
	public int size(){ return container.length; }
	public BUnitLine(boolean length) {
		unitFactory = BUnitFactory.getInstance();
		if(length) container = new BUnitBall[8];
		else container = new BUnitBall[7];
		floor = 0;
	}
	public void fillLine(){
		for(int i = 0; i < container.length; i++) container[i] = (BUnitBall)unitFactory.create(BUnitTypeRandBall.getInstance());
		for(int i = 0; i < container.length; i++) container[i].setPosition(radius * (2*i + (container.length==8?1:2)), (float)(radius * (1 + floor*Math.sqrt(3))));
		remainingBall = container.length;
	}
	public void incFloor(){ 
		floor++;
		for(AUnit ball : container) if(ball!=null) ball.setY((float)(radius * (1 + floor*Math.sqrt(3))));
	}
	public void linking(BUnitLine nextLine){
		for(int i = 0; i < container.length-1 ; i++){
			if(container[i].getBallType().equals(container[i+1].getBallType())){
				container[i].setRR(true);
				container[i+1].setLL(true);
			}
		}
		if(nextLine==null) return;
		// For non-NULL nextLine
		if(container.length==7){
			for(int i=0; i<container.length ; i++){
				if(nextLine.get(i)!=null && container[i].getBallType().equals(nextLine.get(i).getBallType())){
					container[i].setLD(true);
					nextLine.get(i).setRU(true);
				}
				if(nextLine.get(i+1)!=null &&container[i].getBallType().equals(nextLine.get(i+1).getBallType())){
					container[i].setRD(true);
					nextLine.get(i+1).setLU(true);
				}
			}
		} else if(container.length==8) {	
			if(nextLine.get(0)!=null && container[0].getBallType().equals(nextLine.get(0).getBallType())){
				container[0].setRD(true);
				nextLine.get(0).setLU(true);
			}
			for(int i=1; i<container.length-1 ; i++){
				if(nextLine.get(i-1)!=null && container[i].getBallType().equals(nextLine.get(i-1).getBallType())){
					container[i].setLD(true);
					nextLine.get(i-1).setRU(true);
				}
				if(nextLine.get(i)!=null && container[i].getBallType().equals(nextLine.get(i).getBallType())){
					container[i].setRD(true);
					nextLine.get(i).setLU(true);
				}
			}
			if(nextLine.get(6)!=null && container[7].getBallType().equals(nextLine.get(6).getBallType())){
				container[7].setLD(true);
				nextLine.get(6).setRU(true);
			}
		}
	
	}
	public void add(int index, BUnitBall ballToAdd){
		if(index>=0 && index<container.length && container[index]==null){
			container[index] = ballToAdd;
			remainingBall++;
			//linking 추가하기!!
		}
	}
	public void remove(int index){
		container[index] = null;
	}
	public BUnitBall[] getContainer(){
		return container;
	}
	public BUnitBall get(int index){
		return container[index];
	}
	public boolean isEmpty() {
		return (remainingBall==0);
	}	
}
