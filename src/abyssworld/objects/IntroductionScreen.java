/**
 * 
 */
package abyssworld.objects;

import java.util.ArrayList;
import java.util.List;

import abyssworld.abstracts.GameScreenAbstract;
import abyssworld.enums.ScreenState;

/**
 * @author montimage
 *
 */
public class IntroductionScreen extends GameScreenAbstract{
	
	final String LOADING = "Loading...";
	final String STRING1 = "En 6969, tout a changé. Le monde maintenant possède une seule usine principale, qui s'appelle IBM. Le soleil est devenu une légende.";
	final String STRING2 = "Ma mère est malade à cause de la pollution. Mon père et ma soeur sont morts. D'après le docteur, maman n'a que 3 jours a vivre.";
	final String STRING3 = "Si je veux la sauver, je devrais désactiver le coeur processeur de IBM.";
	final String LOCATION_FIRST = "image/backgroundfirst.png";
	final String LOCATION_DEATH = "image/backgroundDeath.png";
	final String LOCATION_ANGRY = "image/angry.png";
	/**
	 * The current index of Introduction Screen
	 */
	private int screenIndex;
	private List<StaticScreen> listScreens;
	/**
	 * 
	 */
	public IntroductionScreen() {
		this.screenIndex = 0;
		this.listScreens = new ArrayList<>();
		this.setState(ScreenState.NEW);
	}

	@Override
	public void display() {
		if(listScreens.get(this.screenIndex).show()) {
			screenIndex++;
		}
		if (this.screenIndex == listScreens.size()) {
			this.setState(ScreenState.PASSED);
		}
	}

	@Override
	public void init() {
		// TODO Initialize
		this.setState(ScreenState.LOADING_RESOURCE);
		listScreens.add(new StaticScreen(LOADING));
		listScreens.get(0).init();
		listScreens.get(0).initCharacter();
		
		listScreens.add(new StaticScreen(STRING1, LOCATION_FIRST));
		listScreens.get(1).init();
		
		listScreens.add(new StaticScreen(STRING2, LOCATION_DEATH));
		listScreens.get(2).init();
		
		listScreens.add(new StaticScreen(STRING3, LOCATION_ANGRY));
		listScreens.get(3).init();
		
		this.setState(ScreenState.ONGOING);
	}

}
