package com.logicaldk.enums;

/**
 * State of the Game
 * @author Dhanendra Kumar
 */
public enum GameState {
	/**
	 * A pre-game state
	 */
	Waiting,
	/**
	 * The game during it's play state
	 */
	Playing,
	/**
	 * A post-game state representing a loss
	 */
	Lost,
	/**
	 * A post-game lost representing a win
	 */
	Won
};