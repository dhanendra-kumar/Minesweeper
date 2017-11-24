package com.logicaldk.enums;
/**
 * A state a tile may be in. 
 * 
 */
public enum TileState {
	/**
	 * A tile is hidden when it is not visible by player and has not been flagged.
	 * A hidden tile's value is the constant "UNKNOWN_VALUE"
	 */
	Hidden,
	/**
	 * A tile is exposed when it has been clicked on by a user.
	 * A tiles value when exposed may be either:
	 * 		 An integer between 0-9 or one of the constant's "BOMB_VALUE, EMPTY_VALUE"
	 */
	Exposed,
	/**
	 * A tile is flagged when it is flagged by the user.
	 * A flagged tiles value will be "FLAG_VALUE"
	 */
	Flagged
};