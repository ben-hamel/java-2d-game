import javax.swing.*;
import java.awt.*;

public class MyDrawPanel extends JPanel {

    public void paintComponent(Graphics g){
        Image image = new ImageIcon("resources/heroRight_32x48.png").getImage();

        g.drawImage(image, 400, 400,this);
//
//        g.setColor(Color.CYAN);
//
//        g.fillRect(20,400,500,500);

    }
}
