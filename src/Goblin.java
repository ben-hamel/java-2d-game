import javax.swing.*;

public class Goblin extends Sprite implements Runnable {
    private Boolean visible, moving;
    private Thread t;
    private JLabel GoblinLabel;
    private Hero heroAlpha;
    private JLabel heroAlphaLabel;
    private JButton animationButton;
    private int direction;

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

    //SETTERs and GETTERS
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

    //GOBLIN CONSTRUCTOR
    public Goblin() {
        super(200,0,32, 48, "Goblin_Left_124x138.png");
    }


    public void moveGoblin() {
        t = new Thread(this, "Goblin Thread");
        t.start();
    }

    public int getDirection() {
        return direction;
    }

    public Goblin setDirection(int direction) {
        this.direction = direction;
        return this;
    }

    public void run() {
        this.moving = true;

        GoblinLabel.setIcon(new ImageIcon(getClass().getResource("Goblin_Left_124x138.png")));
//        DoctorLabel.setIcon(new ImageIcon( getClass().getResource("dw12.png")));

        while (moving) {
            //movement routine
            int tx = this.x;
            int ty = this.y;
            int dir = direction;

//            tx += GameProperties.CHARACTER_STEP;

            if (tx > 700 ) {
               direction = 3;
            } else if (tx < 200){
                direction = 4;
            }

            if (dir == 4) {
                System.out.println("true");
                tx += GameProperties.CHARACTER_STEP;
            } else if (dir == 3) {
                tx -= GameProperties.CHARACTER_STEP;
            } else {
                tx += GameProperties.CHARACTER_STEP;
            }

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

    private void detectHeroCollision() {
        if (this.r.intersects(heroAlpha.getRectangle())) {
            System.out.println("Boom!");
            this.moving = false;
//            animationButton.setText("Run");
//            TardisLabel.setIcon( new ImageIcon(getClass().getResource("redTardis.png"))  );
//            DoctorLabel.setIcon( new ImageIcon(getClass().getResource("redDw12.png"))  );
        }
    }

    public void showHeroMemory() {
        System.out.println(this.heroAlpha);
    }
}

