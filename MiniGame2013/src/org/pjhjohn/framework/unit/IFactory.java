package org.pjhjohn.framework.unit;

import org.pjhjohn.framework.unit.CUnitFactory.UnitType;

public interface IFactory {
	AUnit create(UnitType type);
}