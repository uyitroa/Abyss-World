/**
 * 
 */
package abyssworld.objects;

/**
 * @author montimage
 *
 */
public class StaticScreen {
	int id;
	String backgroundImage;
	String textContent;
	
	public StaticScreen(int id, String bg, String txt) {
		this.id = id;
		this.backgroundImage = bg;
		this.textContent = txt;
	}
	
	public void show() {
		//TODO: draw current screen 
		System.out.println("Show screen: " + this.getId());
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the backgroundImage
	 */
	public String getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * @return the textContent
	 */
	public String getTextContent() {
		return textContent;
	}
	
	

}
