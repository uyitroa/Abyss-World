/**
 * 
 */
package abyssworld.utils;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 * @author montimage
 *
 */
public final class AWUtils {
	/**
	 * Get distance between 2 points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static int getDistance(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)) ; 
	}
	
	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public static long getTime() {
	    return System.nanoTime() / 1000000;
	}
	
	public static void draw(Texture texture, int x, int y) {
		Color.white.bind();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(x, y);

			GL11.glTexCoord2f(1, 0);
			GL11.glVertex2f(x + texture.getTextureWidth(), y);

			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(x + texture.getTextureWidth(), y + texture.getTextureHeight());

			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(x, y + texture.getTextureHeight());

		GL11.glEnd();
	}
}
