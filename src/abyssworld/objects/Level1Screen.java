/**
 * 
 */
package abyssworld.objects;

import java.util.List;

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
	private final String lv1_background = "images/level1_background.png";
	private final String lv1_map = "images/level1_background.png";
	
	Player player;
	TrashBin greenTB ;
	TrashBin yellowTB ;
	TrashBin blueTB ;
	List<Garbage> listOfGarbage;
	
	final int xMin = 0;
	final int yMin = 0;
	final int xMax = AbyssWorld.WIDTH;
	final int yMax = AbyssWorld.HEIGHT/2;
	
	public Level1Screen() {
		this.setState(ScreenState.NEW);
		this.player = new Player("ABYSS", xplayer, yplayer ,this.xMin, this.yMin, this.xMax, this.yMax );
		
		this.greenTB = new TrashBin(TrashBinType.TB_ORGANIC, (int)AbyssWorld.WIDTH * 1/4, (int) AbyssWorld.HEIGHT*3/4);
		this.yellowTB = new TrashBin(TrashBinType.TB_PLASTIC, (int)AbyssWorld.WIDTH * 2/4, (int) AbyssWorld.HEIGHT*3/4);
		this.blueTB = new TrashBin(TrashBinType.TB_PAPER, (int)AbyssWorld.WIDTH * 3/4, (int) AbyssWorld.HEIGHT*3/4);
	}

	@Override
	public void display() {
		System.out.println("Draw the frame of Level1Screen");
		this.setState(ScreenState.PASSED);
	}


	@Override
	public void init() {
		this.setState(ScreenState.LOADING_RESOURCE);
		// Load background image and the map for the game
		
		this.player.init();
		this.greenTB.init();
		this.yellowTB.init();
		this.blueTB.init();
		System.out.println("Loading resource for level1");
		this.setState(ScreenState.ONGOING);
		this.player.setStatus(PlayerStatus.PLAYING);
	}

}
