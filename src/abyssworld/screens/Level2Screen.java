/**
 *
 */
package abyssworld.screens;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import abyssworld.abstracts.GameScreenAbstract;
import abyssworld.app.AbyssWorld;
import abyssworld.entities.Player;
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
	final String question_img = "image/question.png";
	final String answer1_img = "image/answer1.png";
	final String answer2_img = "image/answer2.png";

	boolean xPlayerMove = false;
	boolean yPlayerMove = false;

	Player player;

	Texture map;
	int x_map = 0;
	int y_map = 300;

	Texture lv1bg;
	int x_bg1 = 0;
	int y_bg1 = 0;
	
	Texture qTxt;
	Texture a1Txt;
	Texture a2Txt;


	public static final int xMin = 0;
	public static final int yMin = 0;
	public static final int xMax = AbyssWorld.WIDTH;
	public static final int yMax = AbyssWorld.HEIGHT/2;

	TrueTypeFont font;

	private boolean openQuestion = false;

	private int answer = 0;

	public Level2Screen() {
		this.setState(ScreenState.NEW);
		this.player = new Player("ABYSS", xplayer, yplayer ,this.xMin, this.yMin, this.xMax, this.yMax );
	}

	@Override
	public void display() {
//		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		if (this.openQuestion) {
			AWUtils.draw(qTxt, 350, 50);
			AWUtils.draw(a1Txt, 300, 200);
			AWUtils.draw(a2Txt, 800, 230);
			player.show();
			if (this.answer == 1) {
				AbyssWorld.message = "Congraturation, you have the CORE-PROCESSOR!!!";
				this.player.setStatus(PlayerStatus.WIN);
				this.setState(ScreenState.PASSED);
			} else if (this.answer == 2){
				AbyssWorld.message = "Wrong answer! YOU'VE LOSE!";
				this.player.setStatus(PlayerStatus.LOST);
				this.setState(ScreenState.OVER);
			} else {
				checkKey2();
				this.checkAnswer();
			}
			
		} else {
			AWUtils.draw(map, x_map, y_map);
			player.show();
			checkKey();
			this.checkAction();
		}
	}

	private void checkAnswer() {
		System.out.println("x " + this.player.getX() + " - y " + this.player.getY());
		if (this.player.getY() > 300 && this.player.getY() < 410) {
			if (this.player.getX() > 310 && this.player.getX() < 460) {
				this.answer  = 1;
				System.out.println("choose answer 1");
			}else if (this.player.getX() > 800 && this.player.getX() < 1000) {
				this.answer  = 2;
				System.out.println("choose answer 2ss");
			}
		}
	}

	private void checkAction() {
		if (y_map > 250 && x_map < -800) {
			this.openQuestion  = true;
			System.out.println("Open question");
		}
	}


	@Override
	public void init() {
		this.setState(ScreenState.LOADING_RESOURCE);
		// Load background image and the map for the game
		try {
			map = TextureLoader.getTexture("PNG", new FileInputStream(new File(lv2_map)));
			lv1bg = TextureLoader.getTexture("PNG", new FileInputStream(new File(lv2_background)));
			qTxt = TextureLoader.getTexture("PNG", new FileInputStream(new File(question_img)));
			a1Txt = TextureLoader.getTexture("PNG", new FileInputStream(new File(answer1_img)));
			a2Txt = TextureLoader.getTexture("PNG", new FileInputStream(new File(answer2_img)));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// Initialize font
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
		
		this.player.init();

		// Generate garbages
		this.player.setStatus(PlayerStatus.PLAYING);
		this.player.startedTime = AWUtils.getTime();
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
	
	public void checkKey2() {
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.player.moveLeft();
			player.dirLeft();
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.player.moveRight();
			player.dirRight();
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.player.moveUp();
			player.dirUp();
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.player.moveDown();
			player.dirDown();
		}
	}

}
