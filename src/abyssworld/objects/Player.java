/**
 * 
 */
package abyssworld.objects;

import abyssworld.abstracts.GameEntity;
import abyssworld.enums.PlayerStatus;
import abyssworld.utils.AWUtils;

/**
 * @author montimage
 *
 */
public class Player extends GameEntity{
	
	String name;
	Garbage holdingGabarge;
	int score;
	int xMax;
	int yMax;
	int xMin;
	int yMin;
	long startedTime;	
	PlayerStatus status;
	/**
	 * 
	 */
	public Player(String name, int x, int y, int xMin, int yMin, int xMax, int yMax) {
		this.name = name;
		this.render(x, y);
		this.holdingGabarge = null;
		this.score = 0;
		this.xMax = xMax;
		this.yMax = yMax;
		this.xMin = xMin;
		this.yMin = yMin;
		this.startedTime = AWUtils.getTime();
		this.status = PlayerStatus.CREATED;
	}
	
	/**
	 * Move up
	 */
	public void moveUp() {
		if (this.getY() + Level1Screen.MOVING_STEP < this.yMax) {
			this.setY(this.getY() + Level1Screen.MOVING_STEP);
		}
	}
	
	/**
	 * Move down
	 */
	public void moveDown() {
		if (this.getY() - Level1Screen.MOVING_STEP > this.yMin) {
			this.setY(this.getY() - Level1Screen.MOVING_STEP);
		}
	}
	
	/**
	 * Move up
	 */
	public void moveRight() {
		if (this.getX() + Level1Screen.MOVING_STEP < this.xMax) {
			this.setX(this.getX() + Level1Screen.MOVING_STEP);
		}
	}
	
	/**
	 * Move down
	 */
	public void moveLeft() {
		if (this.getX() - Level1Screen.MOVING_STEP > this.xMin) {
			this.setX(this.getY() - Level1Screen.MOVING_STEP);
		}
	}

	
	public boolean pickAGarbage(Garbage gb) {
		if(this.holdingGabarge == null) {
			this.holdingGabarge = gb;
			return true;
		}else {
			System.out.println("Cannot hold more than 1 garbage");
			return false;
		}		
	}
	
	public boolean throwAgarbageToATrashBin(TrashBin tb) {
		if (this.holdingGabarge != null){
			if (AWUtils.getDistance(this.getX(), this.getY(), tb.getX(), tb.getY()) < Level1Screen.MIN_DISTANCE_WITH_TRASH_BIN) {
				// Calculate the score
				int score = 0;
				if (this.holdingGabarge.getTrashBinType() == tb.getType()) {
					score = Level1Screen.GOOD_CLASSIFY_SCORE;
				}else {
					score = Level1Screen.BAD_CLASSIFY_SCORE;
				}
				this.updateScore(score);
				// throw the garbage
				this.holdingGabarge = null;
				return true;
			};
						
		} else {
			System.out.println("No garbage to throw");
		}
		
		return false;
	}

	private void updateScore(int score) {
		this.score += score;		
	}
	
	public int getScore() {
		return this.score;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void init() {
		
		// TODO Loading the resource
	}
	
	public void notifyPlayerStatus() {
		if (AWUtils.getTime() - this.startedTime > Level1Screen.MAX_PLAY_TIME) {
			this.setStatus(PlayerStatus.LOST);
		}else {
			if (this.getScore() > Level1Screen.MIN_WINNING_SCORE) {
				this.setStatus(PlayerStatus.WIN);
			}
		}
	}

	/**
	 * @return the status
	 */
	public PlayerStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(PlayerStatus status) {
		this.status = status;
	}	
}
