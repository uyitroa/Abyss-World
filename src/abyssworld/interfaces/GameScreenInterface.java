/**
 * 
 */
package abyssworld.interfaces;

import abyssworld.enums.ScreenState;

/**
 * @author montimage
 *
 */
public interface GameScreenInterface {

	/**
	 * Draw current level
	 */
	public void display();	
	
	/**
	 * Initialize resources for current level
	 */
	public void init();
	
	/**
	 * Get state of current level
	 * @return
	 */
	public ScreenState getState();
}
