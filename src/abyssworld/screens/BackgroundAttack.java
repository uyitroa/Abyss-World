package abyssworld.screens;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;

public class BackgroundAttack extends StaticScreen{
	final String FORMAT = ".png";
	String name;
	int numberbg, bgindex;
	Texture[] bg;
	String folder;
	int stringIndex = 0;
	int sleeptime = 200;
	
	public BackgroundAttack(String name, int numberbg) {
		super("ABACRABARA", "k");
		this.name = name;
		this.numberbg = numberbg;
		this.bg = new Texture[numberbg];
		bgindex = 0;
		folder = "image/endscene/";
		this.textContent = new String(new char[numberbg]).replace("\0", " ");
		
	}
	
	public BackgroundAttack(String text, String folder,String name, int numberbg, int sleep) {
		super("ABACRABARA", "k");
		this.name = name;
		this.folder = folder;
		this.textContent = text;
		this.numberbg = numberbg;
		this.bg = new Texture[numberbg];
		sleeptime = sleep;
	}

	@Override
	public boolean show() {
		
		bgTexture = bg[bgindex];
		showBackground();
		TextureImpl.bindNone();
		font.drawString(0, 15, textContent.substring(0, stringIndex));
		bgindex++;
		stringIndex++;
		
		if(bgindex == bg.length) {
			bgindex = 0;
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(sleeptime);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(stringIndex == textContent.length()) {
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
				bg[x] = TextureLoader.getTexture("PNG", new FileInputStream(new File(folder + name + Integer.toString(x) + FORMAT)));
			
			}
			initCharacter();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
