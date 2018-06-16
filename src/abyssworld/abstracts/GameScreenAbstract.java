package abyssworld.abstracts;

import abyssworld.enums.ScreenState;
import abyssworld.interfaces.GameScreenInterface;

public abstract class GameScreenAbstract implements GameScreenInterface{
	/**
	 * To mark if the player has passed the current level
	 */
	private ScreenState state;
	
	/**
	 * Get current state
	 * @return the current state
	 */
	public ScreenState getState() {
		return this.state;
	}
	
	/**
	 * Set state for current level
	 * @param newState
	 */
	public void setState(ScreenState newState) {
		this.state = newState;
	}
}
