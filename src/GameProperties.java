import org.w3c.dom.ls.LSOutput;

//CONSTANTS
public class GameProperties {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 800;
	public static final int CHARACTER_STEP = 20;

	//Directions
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;


	//Hero Alpha
	public static final String HERO_IMG_UP_FILENAME = "heroUp-32x48.png";
	public static final String HERO_IMG_DOWN_FILENAME = "heroAlpha_Down_30x44.png";
	public static final String HERO_IMG_LEFT_FILENAME = "heroLeft_32x48.png";
	public static final String HERO_IMG_RIGHT_FILENAME = "heroRight_32x48.png";
	public static final int HERO_DAMAGE = 25;


	//Goblins
	public static final String Goblin_UP_IMG = "goblinAlpha_Up_40x40.png";
	public static final String Goblin_DOWN_IMG = "goblinAlpha_Down_40x40.png";
	public static final String Goblin_LEFT_IMG = "goblinAlpha_Left_40x40.png";
	public static final String Goblin_RIGHT_IMG = "goblinAlpha_Right_40x40.png";

	//ARROWS
	public static final int VERTICAL_ARROW_WIDTH = 48;
	public static final int VERTICAL_ARROW_HEIGHT = 133;

	public static final int HORIZONTAL_ARROW_WIDTH = 133;
	public static final int HORIZONTAL_ARROW_HEIGHT = 48;

	public static final int ARROW_STEP = 8;
	public static final int ARROW_DAMAGE = 25;

	//THREAD TIMES
	public static final int GAME_PREP_THREAD_TIME = 10;
	public static final int GOBLIN_THREAD_TIME = 100;


	public static final int LIGHT_DAMAGE = 10;
	public static final int GOBLIN_STEP = 1;
    public static final int SCREEN_X = 0;
    public static final int FULL_HEALTH = 100;
}
