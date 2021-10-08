import java.util.ArrayList;

public class Hero extends Sprite {
    ArrayList<Arrow> arr_arrowsFlying;
    public int direction;
    protected String attackFilename;

//    public ArrayList<Arrow> getArr_arrowsFlying() {
//        return arr_arrowsFlying;
//    }

    public int getDirection() {
        return direction;
    }

    public Hero setDirection(int direction) {
        this.direction = direction;
        return this;
    }

    public Hero() {
        super(30, 44, "heroAlpha_Down_30x44.png");
        this.arr_arrowsFlying = new ArrayList<>();
        this.direction = 0;
    }

    public void fireArrow(int x, int y, int d) {
//        if (d == 1) {
//            Arrow heroArrow = new Arrow(x, y, d);
//            heroArrow.setFilename("arrowUp_48x133.png");
//            arr_arrowsFlying.add(heroArrow);
//        } else if (d == 2) {
//            Arrow heroArrow = new Arrow(x, y, d);
//            heroArrow.setFilename(" heroArrow.setFilename(\"arrowUp_48x133.png\");");
//            arr_arrowsFlying.add(heroArrow);
//        }
////        Arrow heroArrow = new Arrow(x,y,d);
////        arr_arrowsFlying.add(heroArrow);
//    }

//    public String getAttackFilename() {
//        return attackFilename;
//    }
//
//    public Hero setAttackFilename(String attackFilename) {
//        this.attackFilename = attackFilename;
//        return this;
//    }
//
//    public void attack(int x, int y, int d) {
//        if (direction == 1) {
//            getDirection();
//            System.out.println("Up Attack");
//            setAttackFilename("arrowUp_48x133.png");
//
//
//        } else if (direction == 2) {
//            setDirection(2);
//            System.out.println("Down Arrow");
//            setAttackFilename("arrowDown_48x133.png");
//
//        } else if (direction == 3) {
//            setDirection(3);
//            System.out.println("Left Arrow");
//            setAttackFilename("arrowLeft_133x48.png");
//
//        } else if (direction == 4) {
//            setDirection(1);
//            System.out.println("Right Arrow");
//            setAttackFilename("arrowRight_133x48.png");
//        }
    }
}
