package com.logicaldk;

import com.logicaldk.enums.*;

/**
 * A representation of a tile in the game Minesweeper
 * 
 * @author Michael Sergio
 */
public interface MinesweeperTile {

	/**
	 * Gets a tile's state
	 * @return the state of the current tile
	 */
	TileState getState();

	/**
	 * Returns non-null references to tiles surrounding the current tile.
	 * The references are the centered around the current tile in a 3 x 3 style excluding this tile.
	 * If tile is against a wall 
	 * @return all surronding tiles
	 */
	MinesweeperTile[] getSurroundingTiles();

	int BOMB_VALUE = 0xBAD;
	int FLAG_VALUE = Integer.MIN_VALUE + 1;
	int UNKNOWN_VALUE = "Sergio".hashCode(); // Ox9366E38D
	int EMPTY_VALUE = 0;

	int getValue();
}