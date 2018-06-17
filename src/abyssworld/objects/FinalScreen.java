/**
 * 
 */
package abyssworld.objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import abyssworld.abstracts.GameScreenAbstract;
import abyssworld.app.AbyssWorld;
import abyssworld.enums.ScreenState;

/**
 * @author montimage
 *
 */
public class FinalScreen extends GameScreenAbstract {

	final String NAME = "Propre";
	final String NAME_PHONE = "image/phone.png";
	final int MIDDLE_X = AbyssWorld.WIDTH / 2 -100;
	final int MIDDLE_Y = AbyssWorld.HEIGHT / 2 - 100;
	
	Texture[] bgSun = new Texture[70];
	private List<StaticScreen> listScreen = new ArrayList<>();
	
	int screenIndex = 0;
	public FinalScreen() {
		this.setState(ScreenState.NEW);
	}
	
	@Override
	public void display() {
		if(listScreen.get(screenIndex).show()) {
			screenIndex++;
		}
		
		if (this.screenIndex == listScreen.size()) {
			this.setState(ScreenState.OVER);
			AbyssWorld.message = null;
		}
		
	}

	@Override
	public void init() {
		this.setState(ScreenState.LOADING_RESOURCE);
		listScreen.add(new BackgroundAttack(NAME, 70));
		listScreen.get(0).init();
		
		listScreen.add(new StaticScreen("    "));
		listScreen.get(1).init();
		
		listScreen.add(new StaticScreen("RING RING RING"));
		listScreen.get(2).init();
		listScreen.get(2).initCharacter();
		
		listScreen.add(new StaticScreen("RING RING RING"));
		listScreen.get(3).init();
		
		listScreen.add(new StaticScreen("                     ", NAME_PHONE));
		listScreen.get(4).init();
		
		listScreen.add(new StaticScreen("   Mom's dead.", MIDDLE_X, MIDDLE_Y, 500, 10));
		listScreen.get(5).init();
		
		this.setState(ScreenState.ONGOING);
	}

}
