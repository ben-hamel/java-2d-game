import javax.swing.*;
import java.awt.*;

public class Goblin extends Sprite {
    //    private Boolean visible, moving;
    private int direction;
    private int health;
    private int stepSize;
    private ImageIcon goblinIcon;
    private JLabel goblinLabel;


    //SETTERS and GETTERS
//    public void setX(int x) {
//        this.x = x;
//        goblinLabel.setLocation(this.x, this.y);
//    }
//
//    public void setWidth(int width) {
//        this.width = width;
//        goblinLabel.setSize(width, this.height);
//    }
//
//    public void setHeight(int height) {
//
//
//    }
//
//    public void setY(int y) {
//        this.y = y;
//        goblinLabel.setLocation(this.x, this.y);
//
//    }

    public Boolean getVisible() {
        return visible;
    }

    public Goblin setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public JLabel getGoblinLabel() {
        return goblinLabel;
    }

    public Goblin setGoblinLabel(JLabel goblinLabel) {
        goblinLabel = goblinLabel;
        return this;
    }

    public int getDirection() {
        return direction;
    }

    public Goblin setDirection(int direction) {
        switch (direction) {
            case GameProperties.UP:
                this.setFilename(GameProperties.Goblin_UP_IMG);
                break;
            case GameProperties.DOWN:
                this.setFilename(GameProperties.Goblin_DOWN_IMG);
                break;
            case GameProperties.LEFT:
                this.setFilename(GameProperties.Goblin_LEFT_IMG);
                break;
            case GameProperties.RIGHT:
                this.setFilename(GameProperties.Goblin_RIGHT_IMG);
                break;
        }
        this.direction = direction;
        return this;
    }

    public int getHealth() {
        return health;
    }

    public Goblin setHealth(int health) {
        this.health = health;
        if (health <= 0) {
            setVisible(false);
        }
        return this;
    }


    //GOBLIN CONSTRUCTOR
    public Goblin() {
        super(200, 0, 40, 40, "goblinAlpha_Down_40x40.png");
        health = 100;
        visible = true;
        stepSize = GameProperties.GOBLIN_STEP;
        direction = 4;
    }

    public Goblin(int newX, int newY) {
        super(newX, newY, 40, 40, "goblinAlpha_Down_40x40.png");
        health = 100;
        visible = true;
        stepSize = GameProperties.GOBLIN_STEP;
        direction = 1;
        if (newX == 0) {
            setFilename("goblinAlpha_Right_40x40.png");
        }
    }

    public Goblin(int x, int y, int direction) {
        super(x, y, 40, 40, true);
        this.health = GameProperties.FULL_HEALTH;
//        this.visible = true;
        this.stepSize = GameProperties.GOBLIN_STEP;
        setDirection(direction);
        goblinLabel = new JLabel();
        goblinIcon = new ImageIcon(getClass().getResource(this.filename));
        goblinLabel.setIcon(goblinIcon);
        goblinLabel.setSize(this.width, this.height);
        goblinLabel.setLocation(x, y);
    }


    //METHODS
    public Rectangle collisionBox() {
        return new Rectangle(x, y, width, height);
    }

    public void lightDamage() {
        int currentHealth = getHealth();
        currentHealth -= GameProperties.LIGHT_DAMAGE;
        setHealth(currentHealth);
        System.out.println("Goblin Damaged: " + health);
        System.out.println("Goblin Visible" + getVisible());
    }

    public void move() {
        int dx = getX();
        int dy = getY();
        int dir = getDirection();
        switch (dir) {
            case GameProperties.UP:
                dy -= stepSize;
                setY(dy);
//               System.out.println("goblin moved to " +getX() + " and " + getY());
                break;
            case GameProperties.DOWN:
                dy += stepSize;
                setY(dy);
//               System.out.println("goblin moved to " +getX() + " and " + getY());
                break;
            case GameProperties.LEFT:
                dx -= stepSize;
                setX(dx);
//               System.out.println("goblin moved to " +getX() + " and " + getY());
                break;
            case GameProperties.RIGHT:
                dx += stepSize;
                setX(dx);
//               System.out.println("goblin moved to " +getX() + " and " + getY());
                break;
        }
    }
}

