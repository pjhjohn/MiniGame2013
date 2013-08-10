package org.pjhjohn.framework.unit;

import game.dodge.unit.CUnitFactory.UnitType;

public interface IFactory {
	AUnit create(UnitType type);
	/* For Further testing
	 * AUnit create(UnitType u_type, GameType g_type);
	 * This might allow same type in different game.
	 * Management file needed for implementation.
	 * Type Definition is in file -> decode -> appManager is holding. 
	 */
}