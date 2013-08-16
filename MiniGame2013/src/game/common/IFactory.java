package game.common;

import game.dodge.unit.CUnitFactory;

import org.pjhjohn.framework.unit.AUnit;

/* 2013.08.16 Updated by pjhjohn */
/* 
 * Unit Type is determined in Application.
 * So, Even IFactory is interface, if create method has UnitType input, IFactory should be moved to Application Part.
 * Basically, methods of Factory-Pattern has no input arguments.
 * Instead of that, they has several method name that corresponded to each part of function.
 * This is bad for creating Units - b/c there could be unlimited kinds of units.
 * 
 * So, IFactory has several create method which has arguments of each game's UnitType
 * 		- this will be documented inside each game's factory class
 */

public interface IFactory {
	AUnit create(CUnitFactory.UnitType type);
}