/**
 * 
 */
package abyssworld.objects;

/**
 * @author montimage
 *
 */
public class StaticScreen {
	String backgroundImage;
	String textContent;
	
	public StaticScreen(String txt, String bg) {
		this.backgroundImage = bg;
		this.textContent = txt;
	}
	
	public StaticScreen(String txt) {
		this.textContent = txt;
	}
	
	public void show() {
		//TODO: draw current screen 
		System.out.println("Show" + this.textContent);
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
	
	public void init() {
		System.out.println("INIT");
	}
	
	

}
