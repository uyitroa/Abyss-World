/**
 * 
 */
package abyssworld.abstracts;

/**
 * @author montimage
 *
 */
public abstract class GameEntity {
	int x;
	int y;
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public void render(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	abstract public void init();
	abstract public void show();
}
