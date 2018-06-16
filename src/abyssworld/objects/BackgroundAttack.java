package abyssworld.objects;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class BackgroundAttack extends StaticScreen{
	final String FOLDER = "image/endscene/";
	final String FORMAT = ".png";
	String name;
	int numberbg, bgindex;
	Texture[] bg;
	
	public BackgroundAttack(String name, int numberbg) {
		super("ABACRABARA", "k");
		this.name = name;
		this.numberbg = numberbg;
		this.bg = new Texture[numberbg];
		bgindex = 0;
	}
	
	@Override
	public boolean show() {
		
		bgTexture = bg[bgindex];
		showBackground();
		bgindex++;
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(bgindex == bg.length) {
			try {
				TimeUnit.MILLISECONDS.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void init() {
		try {
			for(int x = 0; x < this.numberbg; x++) {
				bg[x] = TextureLoader.getTexture("PNG", new FileInputStream(new File(FOLDER + name + Integer.toString(x) + FORMAT)));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
