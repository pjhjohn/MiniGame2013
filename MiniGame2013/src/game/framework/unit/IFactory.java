package game.framework.unit;

import game.framework.unit.CUnitFactory.UnitType;

public interface IFactory {
	AUnit create(UnitType type);
}