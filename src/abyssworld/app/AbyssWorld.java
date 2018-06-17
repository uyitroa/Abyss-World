/**
 *
 */
package abyssworld.app;

import java.awt.Font;
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
import org.newdawn.slick.util.ResourceLoader;

import abyssworld.interfaces.GameScreenInterface;
import abyssworld.objects.FinalScreen;
import abyssworld.objects.IntroductionScreen;
import abyssworld.objects.Level1Screen;
import abyssworld.objects.Level2Screen;

/**
 * @author Raishin
 *
 */
public class AbyssWorld {

	public final static int WIDTH = 1440;
	public final static int HEIGHT = 900;
	private static final String WINDOW_TITLE = "Abyss World Game";

	private int current_level = 0;

	private List<GameScreenInterface> listScreens = new ArrayList<>();
	public static Audio bgMusic, endMusic, paper, plastic, organic, pick_garbage;
	TrueTypeFont font;
	boolean gameOver = false;
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

	public void start() {

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

		IntroductionScreen introductionScreen = new IntroductionScreen();
		this.listScreens.add(introductionScreen);

		Level1Screen level1Screen = new Level1Screen();
		this.listScreens.add(level1Screen);

		Level2Screen level2Screen = new Level2Screen();
		this.listScreens.add(level2Screen);

		FinalScreen finalScreen = new FinalScreen();
		this.listScreens.add(finalScreen);

		while (!Display.isCloseRequested() && !gameOver) {

			switch (this.listScreens.get(this.current_level).getState()) {
			case NEW:
				this.listScreens.get(this.current_level).init();
				break;
			case LOADING_RESOURCE:
				// Show loading screen
				break;
			case ONGOING:
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
				this.listScreens.get(this.current_level).display();
				break;
			case PASSED:
				this.current_level++;
				break;
			default:
				gameOver = true;
				if (font != null) font.drawString(WIDTH/2-50, HEIGHT/2-50, "Game OVER!!!!!");
				break;
			}

			Display.update();
			Display.sync(60); // cap fps to 60fps
			if (gameOver) {
				endMusic.playAsMusic(1.0f, 0.0f, false);
				try {
					TimeUnit.MILLISECONDS.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		endMusic.stop();
		Display.destroy();
	}

	public static void main(String[] argv) {
		AbyssWorld timerExample = new AbyssWorld();
		timerExample.start();
	}

	public void initFont() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 40);
		font = new TrueTypeFont(awtFont, true);
	}

	public void initSound() {
		try {
			bgMusic = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sounds/open.ogg"));
			endMusic = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("sounds/close.ogg"));
			plastic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/plastic.wav"));
			organic = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/organic.wav"));
			paper = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/paper.wav"));
			pick_garbage = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("sounds/pick_garbage.wav"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}