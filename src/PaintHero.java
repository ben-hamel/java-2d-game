import javax.swing.*;
import java.awt.*;

public class PaintHero extends JPanel {

    int x, y, width, height;
    private Color color;

    public PaintHero(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = x + 50;
        this.height = y = 60;
        this.color = Color.CYAN;
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,width,height);

    }
}
