/**
 * 
 */
package abyssworld.utils;

/**
 * @author montimage
 *
 */
public final class AWUtils {
	/**
	 * Get distance between 2 points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static int getDistance(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)) ; 
	}
	
	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public static long getTime() {
	    return System.nanoTime() / 1000000;
	}
}
