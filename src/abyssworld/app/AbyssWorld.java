/**
 *
 */
package abyssworld.app;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import abyssworld.enums.ScreenState;
import abyssworld.interfaces.GameScreenInterface;
import abyssworld.objects.FinalScreen;
import abyssworld.objects.Level2Screen;
import abyssworld.utils.AWUtils;

/**
 * @author Raishin
 *
 */
public class AbyssWorld {
	
	// Window size
	public final static int WIDTH = 1440;
	public final static int HEIGHT = 900;
	private static final String WINDOW_TITLE = "Abyss World Game";
	public static final long WAITING_TIME_BETWEEN_LEVEL = 5000;
	
	// Current game level
	private int current_level = 0;
	
	// List of game level
	private List<GameScreenInterface> listScreens = new ArrayList<>();
	
	// List of effect sounds
	public static Audio bgMusic, endMusic, paper, plastic, organic, pick_garbage;
	
	TrueTypeFont font;
	boolean gameOver = false;
	private long startedTime;
	
	private final String game_level = "image/game-level.png";
	Texture txtGameLevel;
	public static String message = null;
	
	// Initilize GL environment
	public void initGL() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	// Start the game
	public void start() {
		startedTime = AWUtils.getTime();
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(WINDOW_TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		initSound();
		initGL();
		initFont();
		initGraphical();
		
		// Create the game levels
//		IntroductionScreen introductionScreen = new IntroductionScreen();
//		this.listScreens.add(introductionScreen);

//		Level1Screen level1Screen = new Level1Screen();
//		this.listScreens.add(level1Screen);

		Level2Screen level2Screen = new Level2Screen();
		this.listScreens.add(level2Screen);

		FinalScreen finalScreen = new FinalScreen();
		this.listScreens.add(finalScreen);

		
		// Start the game loop
		while (!Display.isCloseRequested() && !gameOver) {

			switch (this.listScreens.get(this.current_level).getState()) {
				case NEW:
					startedTime = AWUtils.getTime(); // Reset time
					this.listScreens.get(this.current_level).init();
					break;
				case ONGOING:
					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
					this.listScreens.get(this.current_level).display();
					break;
				case PASSED:
					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
					if (AWUtils.getTime() - startedTime > 2*AbyssWorld.WAITING_TIME_BETWEEN_LEVEL) {
						startedTime = AWUtils.getTime();
					}
					// Draw passed screen
					
					AWUtils.draw(txtGameLevel, 0, 0);
					TextureImpl.bindNone();
					int xOffset = 500;
					if (message == null && this.current_level < listScreens.size() - 1) {
						message = "Next level";
						xOffset = 300;
					}
					
					if (message != null) font.drawString(WIDTH/2 - xOffset , HEIGHT/2 + 100, message);
					if (AWUtils.getTime() - startedTime >= AbyssWorld.WAITING_TIME_BETWEEN_LEVEL/2) {
						this.current_level++;				
					}
					break;
				case OVER:
					gameOver = true;
					TextureImpl.bindNone();
					if (message == null) message = "Game OVER!!!!";
					if (font != null) font.drawString(WIDTH/2-300, HEIGHT/2 + 100, message);
					break;
				case LOADING_RESOURCE:
					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
					AWUtils.draw(txtGameLevel, 0, 0);
					TextureImpl.bindNone();
					if (message == null) message = "Loading ...";
					if (font != null) font.drawString(WIDTH/2-100, HEIGHT/2 + 100, message);
					if (AWUtils.getTime() - startedTime >= AbyssWorld.WAITING_TIME_BETWEEN_LEVEL) {
						this.listScreens.get(this.current_level).setState(ScreenState.ONGOING);						
					}
					break;
				default:
					break;
			}

			// Update the display
			Display.update();
			Display.sync(60); // cap fps to 60fps
			
			if (gameOver) {
				endMusic.playAsMusic(1.0f, 0.0f, false);
				try {
					// Stop for some time before finish the game
					TimeUnit.MILLISECONDS.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				endMusic.stop();
			}
		}
		
		Display.destroy();
	}

	private void initGraphical() {
		try {
			txtGameLevel = TextureLoader.getTexture("PNG", new FileInputStream(new File(game_level)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// Main function
	public static void main(String[] argv) {
		AbyssWorld abyssWorld = new AbyssWorld();
		abyssWorld.start();
	}

	// Initialize font
	public void initFont() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 40);
		font = new TrueTypeFont(awtFont, true);
	}

	// Initialize sounds resources
	public void initSound() {
		try {
			bgMusic = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sounds/open.ogg"));
			endMusic = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sounds/close.ogg"));
			plastic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/plastic.wav"));
			organic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/organic.wav"));
			paper = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/paper.wav"));
			pick_garbage = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/pick_garbage.wav"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}