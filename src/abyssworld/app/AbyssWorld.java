/**
 * 
 */
package abyssworld.app;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import abyssworld.interfaces.GameScreenInterface;
import abyssworld.interfaces.ScreenState;
import abyssworld.objects.FinalScreen;
import abyssworld.objects.IntroductionScreen;
import abyssworld.objects.Level1Screen;
import abyssworld.objects.Level2Screen;
import abyssworld.objects.StaticScreen;

/**
 * @author Raishin
 *
 */
public class AbyssWorld {

	private static final int NB_LEVEL = 4;

	private int current_level = 0;

	private List<GameScreenInterface> listScreens = new ArrayList<>();

	public void start() {

		IntroductionScreen introductionScreen = new IntroductionScreen();
		this.listScreens.add(introductionScreen);

		Level1Screen level1Screen = new Level1Screen();
		this.listScreens.add(level1Screen);

		Level2Screen level2Screen = new Level2Screen();
		this.listScreens.add(level2Screen);

		FinalScreen finalScreen = new FinalScreen();
		this.listScreens.add(finalScreen);

		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		while (!Display.isCloseRequested() && this.current_level < NB_LEVEL) {
			
			switch (this.listScreens.get(this.current_level).getState()) {
			case NEW:
				this.listScreens.get(this.current_level).init();
				break;
			case LOADING_RESOURCE:
				// Show loading screen
				break;
			case ONGOING:
				this.listScreens.get(this.current_level).display();
				break;
			case PASSED:
				this.current_level++;
				break;
			default:
				break;
			}

			Display.update();
			Display.sync(60); // cap fps to 60fps
		}
		Display.destroy();
	}

	public static void main(String[] argv) {
		AbyssWorld timerExample = new AbyssWorld();
		timerExample.start();
	}
}