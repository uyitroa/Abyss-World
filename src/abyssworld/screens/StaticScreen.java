/**
 * 
 */
package abyssworld.screens;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;
/**
 * @author montimage
 *
 */
public class StaticScreen {
	
	final String LOCATION_BLACK_BG = "image/black.png";
	String backgroundImage;
	String textContent;
	int suspenseLetter = 300; //pls don't ask me why
	Texture bgTexture;
	static TrueTypeFont font;
	int textPosition = 3;
	int textPositionx = 0;
	int textPositiony = 30;
	int sleep = 0;
	
	public StaticScreen(String txt, String bg) {
		this.backgroundImage = bg;
		this.textContent = txt;
	}
	
	public StaticScreen(String txt) {
		this.textContent = txt;
		this.backgroundImage = LOCATION_BLACK_BG;
	}
	
	public StaticScreen(String txt, int x, int y, int sleep) {
		this.textContent = txt;
		this.textPositionx = x;
		this.textPositiony = y;
		this.sleep = sleep;
		this.backgroundImage = LOCATION_BLACK_BG;
	}
	
	public StaticScreen(String txt, int x, int y, int sleep, int suspenseLetter) {
		this.textContent = txt;
		this.textPositionx = x;
		this.textPositiony = y;
		this.sleep = sleep;
		this.backgroundImage = LOCATION_BLACK_BG;
		this.suspenseLetter = suspenseLetter;
	}
	
	public void showBackground() {
		//TODO: draw current screen 
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, bgTexture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(0, 0);

			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(bgTexture.getTextureWidth(), 0);
			
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(bgTexture.getTextureWidth(), bgTexture.getTextureHeight());
			
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(0, bgTexture.getTextureHeight());
			
		GL11.glEnd();
			
	}
	
	public boolean show() {
		Color.white.bind();

		try {
			int sleepingTime = 50;
			if(textPosition == textContent.length() + 1) {
				sleepingTime = 2000;
			} else if (this.textContent.charAt(textPosition - 3) == '.') {
				sleepingTime = 500;
			}
			
			if(suspenseLetter > this.textPosition) {
				TimeUnit.MILLISECONDS.sleep(sleepingTime + sleep);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(textPosition == textContent.length() + 1) {
			return true;
		}
		showBackground();
		TextureImpl.bindNone();
		font.drawString(textPositionx, textPositiony, this.textContent.substring(0, textPosition));
		textPosition++;
		return false;
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
		try {
			bgTexture = TextureLoader.getTexture("PNG", new FileInputStream(new File(backgroundImage)));
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void initCharacter() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
		
	}
	
	

}
