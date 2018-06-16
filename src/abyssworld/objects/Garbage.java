/**
 * 
 */
package abyssworld.objects;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import abyssworld.abstracts.GameEntity;
import abyssworld.enums.GarbageType;
import abyssworld.enums.TrashBinType;

/**
 * @author montimage
 *
 */
public class Garbage extends GameEntity{

	private GarbageType type;
	private String imageSrc;
	private TrashBinType tbType;
	private Texture textTure;
	
	public Garbage(GarbageType type, int x, int y) {
		this.render(x, y);
		this.type = type;
		switch(type) {
		case GB_PLASTIC_1:
			this.imageSrc = "images/gb_plastic_1.png";
		case GB_PLASTIC_2:
			this.imageSrc = "images/gb_plastic_2.png";
		case GB_PLASTIC_3:
			this.imageSrc = "images/gb_plastic_3.png";
			this.tbType = TrashBinType.TB_PLASTIC;
			break;
		case GB_ORGANIC_1:
			this.imageSrc = "images/gb_organic_1.png";
		case GB_ORGANIC_2:
			this.imageSrc = "images/gb_organic_2.png";
		case GB_ORGANIC_3:
			this.imageSrc = "images/gb_organic_3.png";
			this.tbType = TrashBinType.TB_ORGANIC;
			break;
		case GB_PAPER_1:
			this.imageSrc = "images/gb_paper_1.png";
		case GB_PAPER_2:
			this.imageSrc = "images/gb_paper_2.png";
		case GB_PAPER_3:
			this.imageSrc = "images/gb_paper_3.png";
			this.tbType = TrashBinType.TB_PAPER;
			break;
		}
	}
	
	public TrashBinType getTrashBinType() {
		return this.tbType;
	}

	/**
	 * @return the type
	 */
	public GarbageType getType() {
		return type;
	}

	/**
	 * @return the imageSrc
	 */
	public String getImageSrc() {
		return imageSrc;
	}

	public void printToScreen() {
		System.out.println("Gabarge: ");
		System.out.println("x: " + this.getX());
		System.out.println("y: " + this.getY());
		System.out.println("type: " + this.getType());
		System.out.println("TrashBin Type: " + this.getTrashBinType());
		System.out.println("Image src: " + this.getImageSrc());		
	}
	
	public void show() {
		Color.white.bind();
		// Draw the background
		textTure.bind();
		GL11.glBegin(GL11.GL_QUADS);
	        GL11.glTexCoord2f(0,0);
	        GL11.glVertex2f(this.getX(),this.getY());
	        GL11.glTexCoord2f(1,0);	        
	        GL11.glVertex2f(textTure.getTextureWidth(),0);
	        GL11.glTexCoord2f(1,1);
	        GL11.glVertex2f(textTure.getTextureWidth(),textTure.getTextureHeight());
	        GL11.glTexCoord2f(0,1);
	        GL11.glVertex2f(0,textTure.getTextureHeight());
	    GL11.glEnd();
	}

	@Override
	public void init() {
		try {
            // load texture from PNG file
            this.textTure = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(this.getImageSrc()));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
