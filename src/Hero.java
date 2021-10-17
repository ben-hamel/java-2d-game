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

    //-----Thread Code
//    public void startHeroThread() {
//        System.out.println("Hero Thread Starter Method");
//        heroThread = new Thread(this);
//        heroThread.start();
//    }

//    public void run(){
//        System.out.println("Hero Thread running");
//        try {
//            Thread.sleep(200);
//            System.out.println("Hero Thread retriggered");
//        } catch (Exception e) {
//
//        }
//    }


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


}//---end of HERO class
