import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Arrow extends Sprite {
    public int direction;
    public JLabel arrowLabel;
    public ImageIcon arrowImage;
    private boolean visible;
    private int stepSize;


//    SETTER AND GETTER


//    public Arrow setDirection(int direction) {
//        this.direction = direction;
//        if (direction == 1) {
////            System.out.println("Up Arrow");
//            this.setFilename("arrowUp_48x133.png");
//            this.setWidth(48);
//            this.setHeight(133);
////            System.out.println("Arrow cons: x:" + x + ", y:" + y + ", d:" + direction + ", W:" + width + ", H:" +
////            height);
//        } else if (direction == 2) {
////            System.out.println("Down Arrow");
//            setFilename("arrowDown_48x133.png");
//            setWidth(48);
//            setHeight(133);
////            System.out.println("Arrow cons: x:" + x + ", y:" + y + ", d:" + direction + ", W:" + width + ", H:" +
////            height);
//        } else if (direction == 3) {
////            System.out.println("Left Arrow");
//            setFilename(GameProperties.ARROW_LEFT_IMG);
//            setWidth(67);
//            setHeight(48);
////            setWidth(133);
////            setHeight(48);
////            System.out.println("Arrow cons: x:" + x + ", y:" + y + ", d:" + direction + ", W:" + width + ", H:" +
////            height);
//        } else if (direction == 4) {
////            System.out.println("Right Arrow");
//            setFilename("arrowRight_133x48.png");
//            setWidth(133);
//            setHeight(48);
////            System.out.println("Arrow cons: x:" + x + ", y:" + y + ", d:" + direction + ", W:" + width + ", H:" +
////            height);
//        }
//        return this;
//    }

    public int getDirection() {
        return direction;
    }

    public JLabel getArrowLabel() {
        return arrowLabel;
    }

    public Arrow setArrowLabel(JLabel arrowLabel) {
        this.arrowLabel = arrowLabel;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public Arrow setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    //CONSTRUCTOR
    public Arrow(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.visible = true;
        this.stepSize = GameProperties.ARROW_STEP;

        switch (direction) {
            case 1:
                setFilename(GameProperties.ARROW_UP_IMG);
                setWidth(GameProperties.VERTICAL_ARROW_WIDTH);
                setHeight(GameProperties.VERTICAL_ARROW_HEIGHT);
                setY(y);
                setX(x);

                break;
            case 2:
                setFilename(GameProperties.ARROW_DOWN_IMG);
                setWidth(GameProperties.VERTICAL_ARROW_WIDTH);
                setHeight(GameProperties.VERTICAL_ARROW_HEIGHT);
                setY(y);
                setX(x);
                break;
            case 3:
                setFilename(GameProperties.ARROW_LEFT_IMG);
                setWidth(GameProperties.HORIZONTAL_ARROW_WIDTH);
                setHeight(GameProperties.HORIZONTAL_ARROW_HEIGHT);
                setY(y);
                setX(x);
                break;
            case 4:
                setFilename(GameProperties.ARROW_RIGHT_IMG);
                setWidth(GameProperties.HORIZONTAL_ARROW_WIDTH);
                setHeight(GameProperties.HORIZONTAL_ARROW_HEIGHT);
                setY(y);
                setX(x);
                break;
        }

        // Arrow Graphics
        arrowLabel = new JLabel();
        arrowImage = new ImageIcon(getClass().getResource(getFilename()));
        arrowLabel.setIcon(arrowImage);
        arrowLabel.setSize(this.width, this.height);
        arrowLabel.setLocation(this.x, this.y);
    }

    public void move() {
        int dx = getX();
        int dy = getY();
        int dir = getDirection();
        switch (dir) {
            case GameProperties.UP:
//                System.out.println("Arrow moved UP");
                dy -= stepSize;
                if (dy <= 0) {
                    setVisible(false);
                }
                setY(dy);
                break;
            case GameProperties.DOWN:
//                System.out.println("Arrow moved Down");
                dy += stepSize;
                if (dy >= GameProperties.SCREEN_HEIGHT) {
                    setVisible(false);
                }
                setY(dy);
                break;
            case GameProperties.LEFT:
//                System.out.println("Arrow moved LEFT");
                dx -= stepSize;
                if (dx <= 0) {
                    setVisible(false);
                }
                setX(dx);
                break;
            case GameProperties.RIGHT:
//                System.out.println("Arrow moved RIGHT");
                dx += stepSize;
                if (dx + width > GameProperties.SCREEN_WIDTH) {
                    setVisible(false);
                }
                setX(dx);
//                System.out.println("goblin moved to " +getX() + " and " + getY());
                break;
        }
    }

    public Rectangle collisionBox() {
        return new Rectangle(x, y, width, height);
    }

}
