/**
 *
 */
package abyssworld.objects;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import abyssworld.abstracts.GameEntity;
import abyssworld.enums.PlayerStatus;
import abyssworld.utils.AWUtils;

/**
 * @author montimage
 *
 */
public class Player extends GameEntity{

	final String FOLDER_OF_SPRITE = "image/sprites/";
	final String[] NAME_SPRITE = {"front1", "front2", "front3", "front4", "back1", "back2", "back3", "back4", "left1", "left2", "left3", "left4", "right1", "right2", "right3", "right4"};

	Texture[] texTure = new Texture[16];
	String name;
	Garbage holdingGabarge;
	int score;
	int xMax;
	int yMax;
	int xMin;
	int yMin;
	long startedTime;
	PlayerStatus status;
	String dir = "right";
	int id = 1;
	int timing = 10;
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

		try {
			int pos = 0;
			for(String nameSprite : NAME_SPRITE) {
				texTure[pos] = TextureLoader.getTexture("PNG", new FileInputStream(new File(FOLDER_OF_SPRITE + nameSprite + ".png")));
				pos++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePlayerStatus() {
		if (AWUtils.getTime() - this.startedTime > Level1Screen.MAX_PLAYING_TIME) {
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

	public void show() {
		int pos = Arrays.asList(NAME_SPRITE).indexOf(dir + id);
		// System.out.println(dir + id);
		Texture texture = texTure[pos];

		Color.white.bind();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(this.getX(), this.getY());

			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(this.getX() + texture.getTextureWidth(), this.getY());

			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(this.getX() + texture.getTextureWidth(), this.getY() + texture.getTextureHeight());

			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(this.getX(), this.getY() + texture.getTextureHeight());

		GL11.glEnd();
		if (this.holdingGabarge != null) {
			this.holdingGabarge.setX(this.getX() + 10);
			this.holdingGabarge.setY(this.getY() + 50);
			this.holdingGabarge.show();
		}
	}

	public void dirUp() {
		dir = "back";
		if(timing == 0) {
			timing = 10;
			if(id == 4) {
				id = 1;
			} else {
				id++;
			}
		} else {
			timing -= 1;
		}
	}

	public void dirDown() {
			dir = "front";
			if(timing == 0) {
				timing = 10;
				if(id == 4) {
					id = 1;
				} else {
					id++;
				}
			} else {
				timing -= 1;
			}
	}

	public void dirRight() {
			dir = "right";
			if(timing == 0) {
				timing = 10;
				if(id == 4) {
					id = 1;
				} else {
					id++;
				}
			} else {
				timing -= 1;
			}
	}

	public void dirLeft() {
			dir = "left";
			if(timing == 0) {
				timing = 10;
				if(id == 4) {
					id = 1;
				} else {
					id++;
				}
			} else {
				timing -= 1;
			}
	}


}
