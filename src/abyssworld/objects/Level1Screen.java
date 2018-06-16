/**
 * 
 */
package abyssworld.objects;

import java.io.IOException;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import abyssworld.abstracts.GameScreenAbstract;
import abyssworld.app.AbyssWorld;
import abyssworld.enums.PlayerStatus;
import abyssworld.enums.ScreenState;
import abyssworld.enums.TrashBinType;

/**
 * @author montimage
 *
 */
public class Level1Screen extends GameScreenAbstract {
	public final static int MAX_PLAYING_TIME = 20; // 20 seconds
	public final static int MIN_SCORE = 100 ; // minimum score
	public final static long MAX_PLAY_TIME = 20;
	public final static int MIN_WINNING_SCORE = 100;
	public final static int GOOD_CLASSIFY_SCORE = 10;
	public final static int BAD_CLASSIFY_SCORE = -3;
	public final static int MIN_DISTANCE_WITH_TRASH_BIN = 10;
	public final static int MOVING_STEP = 5;
	
	// Starting position of player
	private final int xplayer = 0;
	private final int yplayer = (int)AbyssWorld.HEIGHT/2;
	
	// Position of the TrashBin
	
	// player zone
	private final String lv1_background = "image/backgroundfirst.png";
	private final String lv1_map = "image/phone.png";
	
	Player player;
	TrashBin greenTB ;
	TrashBin yellowTB ;
	TrashBin blueTB ;
	List<Garbage> listOfGarbage;
	
	final int xMin = 0;
	final int yMin = 0;
	final int xMax = AbyssWorld.WIDTH;
	final int yMax = AbyssWorld.HEIGHT/2;
	private Texture backgroundTexture;
	private Texture mapTexture;
	
	public Level1Screen() {
		this.setState(ScreenState.NEW);
		this.player = new Player("ABYSS", xplayer, yplayer ,this.xMin, this.yMin, this.xMax, this.yMax );
		
		this.greenTB = new TrashBin(TrashBinType.TB_ORGANIC, (int)AbyssWorld.WIDTH * 1/4, (int) AbyssWorld.HEIGHT*3/4);
		this.yellowTB = new TrashBin(TrashBinType.TB_PLASTIC, (int)AbyssWorld.WIDTH * 2/4, (int) AbyssWorld.HEIGHT*3/4);
		this.blueTB = new TrashBin(TrashBinType.TB_PAPER, (int)AbyssWorld.WIDTH * 3/4, (int) AbyssWorld.HEIGHT*3/4);
	}

	@Override
	public void display() {
		Color.white.bind();
		// Draw the background
		backgroundTexture.bind();
		GL11.glBegin(GL11.GL_QUADS);
	        GL11.glTexCoord2f(0,0);
	        GL11.glVertex2f(100,100);
	        GL11.glTexCoord2f(1,0);	        
	        GL11.glVertex2f(backgroundTexture.getTextureWidth(),0);
	        GL11.glTexCoord2f(1,1);
	        GL11.glVertex2f(backgroundTexture.getTextureWidth(),backgroundTexture.getTextureHeight());
	        GL11.glTexCoord2f(0,1);
	        GL11.glVertex2f(0,backgroundTexture.getTextureHeight());
	    GL11.glEnd();
	    
	    // Draw the map
		mapTexture.bind();
		GL11.glBegin(GL11.GL_QUADS);
	        GL11.glTexCoord2f(0,0);
	        GL11.glVertex2f(100,100);
	        GL11.glTexCoord2f(1,0);	        
	        GL11.glVertex2f(100+mapTexture.getTextureWidth(),100);
	        GL11.glTexCoord2f(1,1);
	        GL11.glVertex2f(100+mapTexture.getTextureWidth(),100+mapTexture.getTextureHeight());
	        GL11.glTexCoord2f(0,1);
	        GL11.glVertex2f(100,100+mapTexture.getTextureHeight());
	        
	    GL11.glEnd();
		player.show();
		greenTB.show();
		yellowTB.show();
		blueTB.show();
		//this.setState(ScreenState.PASSED);
	}


	@Override
	public void init() {
		this.setState(ScreenState.LOADING_RESOURCE);
		// Load background image and the map for the game
		try {
            // load texture from PNG file
            this.backgroundTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(this.lv1_background));
            this.mapTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(this.lv1_map));
        } catch (IOException e) {
            e.printStackTrace();
        }
		this.player.init();
		this.greenTB.init();
		this.yellowTB.init();
		this.blueTB.init();
		System.out.println("Loading resource for level1");
		this.setState(ScreenState.ONGOING);
		this.player.setStatus(PlayerStatus.PLAYING);
	}

}
