import java.awt.*;
import java.util.ArrayList;

public class Hero extends Sprite {

    public int direction;
    private int stepSize;
    public ArrayList<Arrow> arr_HeroArrowsFlying;
    private boolean visible;

    //SETTERS AND GETTERS

    public boolean isVisible() {
        return visible;
    }

    public Hero setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public int getDirection() {
        return direction;
    }

    public Hero setDirection(int direction) {
        this.direction = direction;
        return this;
    }


    //---HERO CONSTRUCTORS
    public Hero() {
        super(30, 44, GameProperties.HERO_IMG_DOWN_FILENAME);
        this.arr_HeroArrowsFlying = new ArrayList<>();
        this.direction = 0; this.stepSize = GameProperties.CHARACTER_STEP;
        this.visible = true;
    }

    //-----Fire Arrow Method
    public void fireArrow() {
        int dir = getDirection();
        int x = getX();
        int y = getY();

        //test arrow
        System.out.println("arrow shot");

        //create Arrow
        Arrow shotArrow = new Arrow(x, y, dir);

        //Add Arrow to array
        arr_HeroArrowsFlying.add(shotArrow);
    }

    public void move(int direction) {
        this.direction = direction;

        switch (this.direction) {
            case 1:
                this.y -= stepSize;
                break;
            case 2:
                this.y += stepSize;
                break;
            case 3:
                this.x -= stepSize;
                break;
            case 4:
                this.x += stepSize;
                break;
        }
    }

    public Rectangle collisionBox(){
        return new Rectangle(x,y,width,height);
    }

}//---end of HERO class
