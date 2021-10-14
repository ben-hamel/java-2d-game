import javax.swing.*;
import java.awt.font.GlyphMetrics;
import java.util.ArrayList;

public class Hero extends Sprite implements Runnable{

    public int direction;
    public ArrayList<Arrow> arr_arrowsFlying;
    private Thread heroThread;


    //SETTERS AND GETTERS
    public int getDirection() {
        return direction;
    }

    public Hero setDirection(int direction) {
        this.direction = direction;
        return this;
    }

    //---HERO CONSTRUCTORS
    public Hero() {
        super(30, 44, GameProperties.HERO_ALPHA_DOWN_FILENAME);
        this.arr_arrowsFlying = new ArrayList<>();
        this.direction = 0;
//        startThread();
    }

    //-----Thread Code
    public void startHeroThread() {
        System.out.println("Hero Thread Starter Method");
        heroThread = new Thread(this);
        heroThread.start();

    }

    public void run(){
        System.out.println("Hero Thread running");
        try {
            Thread.sleep(200);
            System.out.println("Hero Thread retriggered");
        } catch (Exception e) {

        }
    }


    //-----Fire Arrow Method
    public void fireArrow() {
        int dir = getDirection();
        int x = getX();
        int y = getY();

        //---Method Tester
//        System.out.println("fireArrow Method Working");

        //create Arrow
        Arrow shotArrow = new Arrow(x, y, dir);

        //Add Arrow to array
        arr_arrowsFlying.add(shotArrow);

    }


}//---end of HERO class
