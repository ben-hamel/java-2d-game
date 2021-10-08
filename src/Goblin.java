import javax.swing.*;

public class Goblin extends Sprite implements Runnable {
    private Boolean visible, moving;
    private Thread t;
    private JLabel GoblinLabel;
    private Hero heroAlpha;
    private JLabel heroAlphaLabel;
    private JButton animationButton;
    private int direction;
    private int health;
    private boolean right;


    //SETTERS and GETTERS
    public Boolean getVisible() {
        return visible;
    }

    public Goblin setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Boolean getMoving() {
        return moving;
    }

    public Goblin setMoving(Boolean moving) {
        this.moving = moving;
        return this;
    }

    public JLabel getGoblinLabel() {
        return GoblinLabel;
    }

    public Goblin setGoblinLabel(JLabel goblinLabel) {
        GoblinLabel = goblinLabel;
        return this;
    }

    public Hero getHeroAlpha() {
        return heroAlpha;
    }

    public Goblin setHeroAlpha(Hero heroAlpha) {
        this.heroAlpha = heroAlpha;
        return this;
    }

    public JLabel getHeroAlphaLabel() {
        return heroAlphaLabel;
    }

    public Goblin setHeroAlphaLabel(JLabel temp) {
        this.heroAlphaLabel = temp;
        return this;
    }

    public Thread getT() {
        return t;
    }

    public Goblin setT(Thread t) {
        this.t = t;
        return this;
    }


    public JButton getAnimationButton() {
        return animationButton;
    }

    public Goblin setAnimationButton(JButton animationButton) {
        this.animationButton = animationButton;
        return this;
    }

    public int getDirection() {
        return direction;
    }

    public Goblin setDirection(int direction) {
        this.direction = direction;
        return this;
    }

    public int getHealth() {
        return health;
    }

    public Goblin setHealth(int health) {
        this.health = health;
        return this;
    }


    //GOBLIN CONSTRUCTOR
    public Goblin() {
        super(200, 0, 40, 40, "goblinAlpha_Down_40x40.png");
        health = 100;
    }

    public Goblin(int newX, int newY) {
        super(newX, newY, 40, 40, "goblinAlpha_Down_40x40.png");
        health = 100;
        if (newX == 0) {
            setFilename("goblinAlpha_Right_40x40.png");

        }
    }


    //Thread Part 2
    public void moveGoblin() {
        t = new Thread(this, "Goblin Thread");
        t.start();
    }

    // Thread Part 2
    public void run() {
        this.moving = true;

//        GoblinLabel.setIcon(new ImageIcon(getClass().getResource("goblinAlpha_Left_40x40.png")));


        while (moving) {
            //movement routine
            int tx = this.x;
            int ty = this.y;
            int dir = direction;


//            tx += GameProperties.CHARACTER_STEP;

            //TODO Figure out what type of Goblin and based on this set move direction and how far to walk
            if (tx > 700) {
                direction = 3;
                GoblinLabel.setIcon(new ImageIcon(getClass().getResource("goblinAlpha_Left_40x40.png")));
            } else if (tx < 200) {
                direction = 4;
                GoblinLabel.setIcon(new ImageIcon(getClass().getResource("goblinAlpha_Right_40x40.png")));
            }

            //Walk LEFT AND RIGHT
            if (dir == 4) {
//                System.out.println("true");
                tx += GameProperties.CHARACTER_STEP;
            } else if (dir == 3) {
                tx -= GameProperties.CHARACTER_STEP;
            } else {
                tx += GameProperties.CHARACTER_STEP;
            }

            //WALK UP AND DOWN


            //if character walks off screen
//            if (tx > GameProperties.SCREEN_WIDTH) {
//                tx = -1 * this.width;
//            }


            this.setX(tx);
            this.setY(ty);

            GoblinLabel.setLocation(this.x, this.y);
            this.detectHeroCollision();

            try {
                Thread.sleep(200);
            } catch (Exception e) {

            }
        }
    }


    //METHODS
    private void detectHeroCollision() {
        int currentHealth = getHealth();
        if (this.r.intersects(heroAlpha.getRectangle())) {
            System.out.println("Boom!");
            currentHealth = currentHealth - 50;
            setHealth(currentHealth);
            if (currentHealth == 0) {
                this.moving = false;
            }
//            this.moving = false;
//            animationButton.setText("Run");
        }
    }

    //SHOW HERO LOCATION IN MEMORY
//    public void showHeroMemory() {
//        System.out.println("hero memory in goblin is: " + this.heroAlpha);
//    }

//    public void move(){
//        if (x == 700)
//            right = true;
//        if (y == 0)
//            right = false;
//        if (right)
//            x--;
//        else
//            x++;
//    }

}

