import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Arrow extends Sprite {
    public int direction;
    public JLabel arrowLabel;
    public ImageIcon arrowImage;
    private boolean visible;


    //SETTER AND GETTER
    public Arrow setDirection(int direction) {
        this.direction = direction;
        if (direction == 1) {
//            System.out.println("Up Arrow");
            this.setFilename("arrowUp_48x133.png");
            this.setWidth(48);
            this.setHeight(133);
//            System.out.println("Arrow cons: x:" + x + ", y:" + y + ", d:" + direction + ", W:" + width + ", H:" + height);
        } else if (direction == 2) {
//            System.out.println("Down Arrow");
            setFilename("arrowDown_48x133.png");
            setWidth(48);
            setHeight(133);
//            System.out.println("Arrow cons: x:" + x + ", y:" + y + ", d:" + direction + ", W:" + width + ", H:" + height);
        } else if (direction == 3) {
//            System.out.println("Left Arrow");
            setFilename("arrowLeft_133x48.png");
            setWidth(133);
            setHeight(48);
//            System.out.println("Arrow cons: x:" + x + ", y:" + y + ", d:" + direction + ", W:" + width + ", H:" + height);
        } else if (direction == 4) {
//            System.out.println("Right Arrow");
            setFilename("arrowRight_133x48.png");
            setWidth(133);
            setHeight(48);
//            System.out.println("Arrow cons: x:" + x + ", y:" + y + ", d:" + direction + ", W:" + width + ", H:" + height);
        }
        return this;
    }

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

        switch (direction) {
            case 1:
                setFilename("arrowUp_48x133.png");
                setWidth(GameProperties.VERTICAL_ARROW_WIDTH);
                setHeight(GameProperties.VERTICAL_ARROW_HEIGHT);
                break;
            case 2:
                setFilename("arrowDown_48x133.png");
                setWidth(GameProperties.VERTICAL_ARROW_WIDTH);
                setHeight(GameProperties.VERTICAL_ARROW_HEIGHT);
                break;
            case 3:
                setFilename("arrowLeft_133x48.png");
                setWidth(GameProperties.HORIZONTAL_ARROW_WIDTH);
                setHeight(GameProperties.HORIZONTAL_ARROW_HEIGHT);
                break;
            case 4:
                setFilename("arrowRight_133x48.png");
                setWidth(GameProperties.HORIZONTAL_ARROW_WIDTH);
                setHeight(GameProperties.HORIZONTAL_ARROW_HEIGHT);
                break;
        }

        // Arrow Graphics
        arrowLabel = new JLabel();
        arrowImage = new ImageIcon(getClass().getResource(getFilename()));
        arrowLabel.setIcon(arrowImage);
        arrowLabel.setSize(this.width,this.height);
        arrowLabel.setLocation(this.x,this.y);
    }

}
