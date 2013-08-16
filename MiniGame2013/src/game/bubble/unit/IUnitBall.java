package game.bubble.unit;

import game.bubble.unit.BUnitFactory.UnitType;

public interface IUnitBall {
	public UnitType getBallType();
	public void setRU(boolean _ru);
	public void setLU(boolean _lu);
	public void setRR(boolean _rr);
	public void setLL(boolean _ll);
	public void setRD(boolean _rd);
	public void setLD(boolean _ld);
	public boolean getRU();
	public boolean getLU();
	public boolean getRR();
	public boolean getLL();
	public boolean getRD();
	public boolean getLD();
	//public void setMoving(boolean _mv);
}
