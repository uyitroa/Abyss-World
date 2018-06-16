/**
 *
 */
package abyssworld.objects;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import abyssworld.abstracts.GameScreenAbstract;
import abyssworld.app.AbyssWorld;
import abyssworld.enums.PlayerStatus;
import abyssworld.enums.ScreenState;
import abyssworld.enums.TrashBinType;
import abyssworld.utils.AWUtils;

/**
 * @author montimage
 *
 */
public class Level1Screen extends GameScreenAbstract {
	public final static int MAX_PLAYING_TIME = 20000; // 20 seconds
	public final static int MIN_SCORE = 100 ; // minimum score
	public final static long MAX_PAUSE_TIME = 5000;
	public final static int MIN_WINNING_SCORE = 20;
	public final static int GOOD_CLASSIFY_SCORE = 10;
	public final static int BAD_CLASSIFY_SCORE = -3;
	public final static int MIN_DISTANCE_WITH_TRASH_BIN = 10;
	public final static int MOVING_STEP = 5;
	public final static int EFFECT_DISTANCE = 100;
	private static final int NB_GARBAGES = 20;

	private final int EDGELEFT = 700;
	private final int EDGERIGHT = 3000;
	private final int EDGEUP = -200;
	private final int EDGEDOWN = -525;

	// Starting position of player
	private final int xplayer = (int)AbyssWorld.WIDTH/2;
	private final int yplayer = (int)AbyssWorld.HEIGHT/2;

	// player zone
	private final String lv1_background = "image/backgroundfirst.png";
	private final String lv1_map = "image/room1.png";
	Player player;
	TrashBin greenTB ;
	TrashBin yellowTB ;
	TrashBin blueTB ;

	Texture map;
	int x_map = 0;
	int y_map = -300;

	Texture lv1bg;
	int x_bg1 = 0;
	int y_bg1 = 0;

	List<Garbage> listOfGarbage;

	public static final int xMin = 0;
	public static final int yMin = 0;
	public static final int xMax = AbyssWorld.WIDTH;
	public static final int yMax = AbyssWorld.HEIGHT/2;


	public Level1Screen() {
		this.setState(ScreenState.NEW);
		listOfGarbage = new ArrayList<>();
		this.player = new Player("ABYSS", xplayer, yplayer ,this.xMin, this.yMin, this.xMax, this.yMax );

		this.greenTB = new TrashBin(TrashBinType.TB_ORGANIC, (int)AbyssWorld.WIDTH * 1/4, (int) AbyssWorld.HEIGHT*3/8);
		this.yellowTB = new TrashBin(TrashBinType.TB_PLASTIC, (int)AbyssWorld.WIDTH * 2/4, (int) AbyssWorld.HEIGHT*3/8);
		this.blueTB = new TrashBin(TrashBinType.TB_PAPER, (int)AbyssWorld.WIDTH * 3/4, (int) AbyssWorld.HEIGHT*3/8);
	}

	@Override
	public void display() {
		//Draw bg and map
		//draw(lv1bg, x_bg1, y_bg1);
		draw(map, x_map, y_map);

		greenTB.show();
		yellowTB.show();
		blueTB.show();

		for (Garbage garbage : listOfGarbage) {
			garbage.show();
		}

		this.checkAction();

		player.show();

		//this.setState(ScreenState.PASSED);
		checkKey();

		System.out.println("Score : " + this.player.getScore());

	}


	private void generateGarbages(int xMin, int yMin, int xMax, int yMax ) {
		System.out.println("xMin " + xMin + " - " + yMin + " - " + xMax + " - " + yMax);
		for (int i= 0 ; i < NB_GARBAGES; i++) {
			Garbage newGB = GarbagePipe.generateAGarbage(xMin, yMin, xMax, yMax);
			if (newGB != null) {
				newGB.init();
				listOfGarbage.add(newGB);
			}
		}

	}

	private void checkAction() {
		if (this.player.holdingGabarge != null) {
			tryToThrowTheGarbage();
		} else {
			tryToPickAGarbage();
		}
		// Update the status of the player after the action
		this.player.updatePlayerStatus();
		// Check the status of the player after the action
		if (this.player.getStatus() == PlayerStatus.WIN) {
			this.setState(ScreenState.PASSED);
		} else if (this.player.getStatus() == PlayerStatus.LOST){
			this.setState(ScreenState.OVER);
		}
	}



	private void tryToPickAGarbage() {
		int minDistance = AbyssWorld.WIDTH;
		Garbage closestGarbage = null;
		for (Garbage garbage : listOfGarbage) {
			int gbDistance = AWUtils.getDistance(this.player.getX(), this.player.getY(), garbage.getX(), garbage.getY());
			if (gbDistance < minDistance) {
				minDistance = gbDistance;
				closestGarbage = garbage;
			};
		}
		if (closestGarbage != null && minDistance < Level1Screen.EFFECT_DISTANCE) {
			System.out.println("Pick a garbage");
			this.listOfGarbage.remove(closestGarbage);
			this.player.pickAGarbage(closestGarbage);
		}

	}

	private void tryToThrowTheGarbage() {
		int minDistance = AbyssWorld.WIDTH;
		TrashBin closestTB = null;

		// Check the green trash bin
		int tbDistance = AWUtils.getDistance(this.player.getX(), this.player.getY(), this.greenTB.getX(), this.greenTB.getY());
		if (tbDistance < minDistance) {
			minDistance = tbDistance;
			closestTB = this.greenTB;
		};

		// Check the yellow trash bin
		tbDistance = AWUtils.getDistance(this.player.getX(), this.player.getY(), this.yellowTB.getX(), this.yellowTB.getY());
		if (tbDistance < minDistance) {
			minDistance = tbDistance;
			closestTB = this.yellowTB;
		};

		// Check the blue trash bin
		tbDistance = AWUtils.getDistance(this.player.getX(), this.player.getY(), this.blueTB.getX(), this.blueTB.getY());
		if (tbDistance < minDistance) {
			minDistance = tbDistance;
			closestTB = this.blueTB;
		};
		if (closestTB != null && minDistance < Level1Screen.EFFECT_DISTANCE) {
			this.player.throwAgarbageToATrashBin(closestTB);
		}

	}

	@Override
	public void init() {
		this.setState(ScreenState.LOADING_RESOURCE);
		// Load background image and the map for the game
		try {
			map = TextureLoader.getTexture("PNG", new FileInputStream(new File(lv1_map)));
			lv1bg = TextureLoader.getTexture("PNG", new FileInputStream(new File(lv1_background)));
		} catch(Exception e) {
			e.printStackTrace();
		}

		this.player.init();
		this.greenTB.init();
		this.yellowTB.init();
		this.blueTB.init();

		// Generate garbages
		this.generateGarbages( AbyssWorld.WIDTH/2, (int) AbyssWorld.HEIGHT*5/8, AbyssWorld.WIDTH, AbyssWorld.HEIGHT/2);
		this.setState(ScreenState.ONGOING);
		this.player.setStatus(PlayerStatus.PLAYING);
	}

	public void draw(Texture texture, int x, int y) {
		Color.white.bind();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(x, y);

			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(x + texture.getTextureWidth(), y);

			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(x + texture.getTextureWidth(), y + texture.getTextureHeight());

			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(x, y + texture.getTextureHeight());

		GL11.glEnd();
	}

	public void checkKey() {
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if(x_map < EDGELEFT) {
				greenTB.moveRight();
				yellowTB.moveRight();
				blueTB.moveRight();

				for(Garbage gb : listOfGarbage) {
					gb.moveRight();
				}

				x_map += MOVING_STEP;
				x_bg1 += 1;
			}
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if(x_map + map.getTextureWidth() > EDGERIGHT) {
				greenTB.moveLeft();
				yellowTB.moveLeft();
				blueTB.moveLeft();

				for(Garbage gb : listOfGarbage) {
					gb.moveLeft();
				}

				x_map -= MOVING_STEP;
				x_bg1 -= 1;

			}
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			if(y_map < EDGEUP) {
				greenTB.moveDown();
				yellowTB.moveDown();
				blueTB.moveDown();

				for(Garbage gb : listOfGarbage) {
					gb.moveDown();
				}

				y_map += MOVING_STEP;
				y_bg1 += 1;
			}
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			if(y_map > EDGEDOWN) {
				greenTB.moveUp();
				yellowTB.moveUp();
				blueTB.moveUp();

				for(Garbage gb : listOfGarbage) {
					gb.moveUp();
				}

				y_map -= MOVING_STEP;
				y_bg1 -= 1;
			}
		}
	}

}
