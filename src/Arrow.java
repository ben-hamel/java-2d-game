import javax.swing.*;
import java.util.ArrayList;

public class Arrow extends Sprite {

     public int direction;

    public int getDirection() {
        return direction;
    }

    public Arrow setDirection(int direction) {
        this.direction = direction;
        return this;
    }

    public Arrow(int x, int y, int direction){
        this. x = x;
        this.y = y;
        this.direction = direction;

        if(direction == 1){
            System.out.println("Up Arrow");
            setFilename("arrowUp_48x133.png");
            setWidth(48);
            setHeight(133);
            System.out.println("Arrow cons: x:"+x+", y:"+y+", d:" + direction+", W:"+width+", H:"+height);
        } else if (direction == 2){
            System.out.println("Down Arrow");
            setFilename("arrowDown_48x133.png");
            setWidth(48);
            setHeight(133);
            System.out.println("Arrow cons: x:"+x+", y:"+y+", d:" + direction+", W:"+width+", H:"+height);
        }else if (direction == 3){
            System.out.println("Left Arrow");
            setFilename("arrowLeft_133x48.png");
            setWidth(133);
            setHeight(48);
            System.out.println("Arrow cons: x:"+x+", y:"+y+", d:" + direction+", W:"+width+", H:"+height);
        }else if (direction == 4){
            System.out.println("Right Arrow");
            setFilename("arrowRight_133x48.png");
            setWidth(133);
            setHeight(48);
            System.out.println("Arrow cons: x:"+x+", y:"+y+", d:" + direction+", W:"+width+", H:"+height);
        }
    }
}
