/**
 * 
 */
package abyssworld.objects;

import java.util.ArrayList;
import java.util.List;

import abyssworld.abstracts.GameScreenAbstract;
import abyssworld.interfaces.ScreenState;

/**
 * @author montimage
 *
 */
public class IntroductionScreen extends GameScreenAbstract{
	
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
		listScreens.get(this.screenIndex).show();
		this.screenIndex++;
		if (this.screenIndex == listScreens.size()) {
			this.setState(ScreenState.PASSED);
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
