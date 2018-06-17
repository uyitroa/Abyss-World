/**
 * 
 */
package abyssworld.entities;

import java.io.File;
import java.io.FileInputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import abyssworld.abstracts.GameEntity;
import abyssworld.enums.TrashBinType;
import abyssworld.screens.Level1Screen;

/**
 * @author montimage
 *
 */
public class TrashBin extends GameEntity{
	
	private TrashBinType type;
	private String imageSrc;
	private Texture texture;
	public TrashBin(TrashBinType type, int x, int y) {
		this.render(x, y);
		this.type = type;
		switch(type) {
			case TB_PLASTIC:
				this.imageSrc = "image/tb_plastic.png";
				break;
			case TB_ORGANIC:
				this.imageSrc = "image/tb_organic.png";
				break;
			case TB_PAPER:
				this.imageSrc = "image/tb_paper.png";
				break;
		}
	}
	
	public TrashBinType getType() {
		return this.type;
	}
	
	public String getImageSrc() {
		return this.imageSrc;
	}
	
	public void moveUP() {
		this.setY(this.getY() - Level1Screen.MOVING_STEP);
	}
	
	public void moveDown() {
		this.setY(this.getY() + Level1Screen.MOVING_STEP);
	}
	
	public void moveRight() {
		this.setX(this.getX() + Level1Screen.MOVING_STEP);
	}
	
	public void moveLeft() {
		this.setX(this.getX() - Level1Screen.MOVING_STEP);
	}

	@Override
	public void init() {
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(imageSrc)));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void show() {
		Color.white.bind();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(this.getX(), this.getY());
			
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(this.getX() + texture.getTextureWidth(), this.getY());
			
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(this.getX() + texture.getTextureWidth(), this.getY() + texture.getTextureHeight());
			
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(this.getX(), this.getY() + texture.getTextureHeight());
			
		GL11.glEnd();
	}
	
	public Texture getTexture() {
		return this.texture;
	}
	
}
