import java.util.ArrayList;

public class Hero extends Sprite {

    public int direction;
    public ArrayList<Arrow> arr_HeroArrowsFlying;

    //    private Thread heroThread;
    GamePrep gameFrame;


    //SETTERS AND GETTERS
    public int getDirection() {
        return direction;
    }

    public Hero setDirection(int direction) {
        this.direction = direction;
        return this;
    }

    public GamePrep getGameFrame() {
        return gameFrame;
    }

    public Hero setGameFrame(GamePrep gameFrame) {
        this.gameFrame = gameFrame;
        return this;
    }

    //---HERO CONSTRUCTORS
    public Hero() {
        super(30, 44, GameProperties.HERO_ALPHA_DOWN_FILENAME);
        this.arr_HeroArrowsFlying = new ArrayList<>();
        this.direction = 0;
//        startThread();

    }

    //-----Fire Arrow Method
    public void fireArrow() {
        int dir = getDirection();
        int x = getX();
        int y = getY();

        //create Arrow
        Arrow shotArrow = new Arrow(x, y, dir);

        //Add Arrow to array
        arr_HeroArrowsFlying.add(shotArrow);


    }

    public void move(int direction) {
        this.direction = direction;

        switch (this.direction) {
            case 1:
                this.y -= GameProperties.CHARACTER_STEP;
                break;
            case 2:
                this.y += GameProperties.CHARACTER_STEP;
                break;
            case 3:
                this.x -= GameProperties.CHARACTER_STEP;
                break;
            case 4:
                this.x += GameProperties.CHARACTER_STEP;
                break;
        }
    }

}//---end of HERO class
