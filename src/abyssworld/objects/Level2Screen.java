/**
 * 
 */
package abyssworld.objects;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import abyssworld.abstracts.GameScreenAbstract;
import abyssworld.app.AbyssWorld;
import abyssworld.enums.PlayerStatus;
import abyssworld.enums.ScreenState;
import abyssworld.utils.AWUtils;

/**
 * @author montimage
 *
 */
public class Level2Screen extends GameScreenAbstract {
	
	public final static int MOVING_STEP = 5;
	
	// Starting position of player
	private final int xplayer = (int)AbyssWorld.WIDTH/2;
	private final int yplayer = (int)AbyssWorld.HEIGHT*5/8;
	
	final String lv2_background = "image/backgroundfirst.png";
	final String lv2_map = "image/lv2_map.png";
	
	boolean xPlayerMove = false;
	boolean yPlayerMove = false;
	
	Player player;
	
	Texture map;
	int x_map = 0;
	int y_map = 300;

	Texture lv1bg;
	int x_bg1 = 0;
	int y_bg1 = 0;

	
	public static final int xMin = 0;
	public static final int yMin = 0;
	public static final int xMax = AbyssWorld.WIDTH;
	public static final int yMax = AbyssWorld.HEIGHT/2;

	TrueTypeFont font;

	private boolean openQuestion = false;

	public Level2Screen() {
		this.setState(ScreenState.NEW);
		this.player = new Player("ABYSS", xplayer, yplayer ,this.xMin, this.yMin, this.xMax, this.yMax );
		initFont();
	}
	
	@Override
	public void display() {
		if (this.openQuestion) {
			font.drawString(10, 10, "Which is more importance?");
		} else {
//			draw(lv1bg, x_bg1, y_bg1);
			draw(map, x_map, y_map);
			player.show();
			//this.setState(ScreenState.PASSED);
			checkKey();
			this.checkAction();
		}
	}
	
	private void checkAction() {
		System.out.println("a - b " + this.player.getY() + '/'+ (y_map + map.getTextureHeight() * 3/8 - 2 * MOVING_STEP));;
		if (this.player.getY() < y_map + map.getTextureHeight() * 3/8 - 2 * MOVING_STEP) {
			if (this.player.getX() > x_map + map.getTextureWidth() - 2 * MOVING_STEP - 250) {
				this.openQuestion  = true;
			}
		}
	}


	@Override
	public void init() {
		this.setState(ScreenState.LOADING_RESOURCE);
		// Load background image and the map for the game
		try {
			map = TextureLoader.getTexture("PNG", new FileInputStream(new File(lv2_map)));
			lv1bg = TextureLoader.getTexture("PNG", new FileInputStream(new File(lv2_background)));
		} catch(Exception e) {
			e.printStackTrace();
		}

		this.player.init();

		// Generate garbages
		this.setState(ScreenState.ONGOING);
		this.player.setStatus(PlayerStatus.PLAYING);
		this.player.startedTime = AWUtils.getTime();
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
				if (x_map + MOVING_STEP >= this.player.getX()) {
					xPlayerMove = true;
				} else {
					x_map += MOVING_STEP;
					x_bg1 += 1;
					player.dirLeft();
				}
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if (x_map + map.getImageWidth() - 250 - MOVING_STEP <= this.player.getX()) {
				xPlayerMove = true;
			} else {
				x_map -= MOVING_STEP;
				x_bg1 -= 1;
				player.dirRight();
			}
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			if (y_map + map.getImageHeight()*1/4 + MOVING_STEP >= this.player.getY()) {
				yPlayerMove = true;
			} else {
				y_map += MOVING_STEP;
				y_bg1 += 1;
				player.dirUp();
			}
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			if (y_map + map.getImageHeight() - 200 - MOVING_STEP <= this.player.getY()) {
				yPlayerMove = true;
			} else {
				y_map -= MOVING_STEP;
				y_bg1 -= 1;
				player.dirDown();
			}
		}
	}
	
	private void initFont() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
	}

}
