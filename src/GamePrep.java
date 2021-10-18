import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class GamePrep extends JFrame implements ActionListener, KeyListener, Runnable {

    private static final long serialVersionUID = 6106269076155338045L;

    //___GAME SCORE
    public int gameScore;

    //---Hero ALPHA
    private Hero heroAlpha;//Hero object
    private JLabel heroLabel;
    private ImageIcon heroImage;

    //---GOBLIN ALPHA
    private Goblin goblinAlpha;
    private JLabel goblinAlphaLabel;
    private ImageIcon goblinAlphaImage;

    //---GOBLIN BETA
    private Goblin goblinBeta;
    private JLabel goblinBetaLabel;
    private ImageIcon goblinBetaImage;

    //--- START BUTTON
    private JButton HideTardisButton, AnimateButton;

    //---Container to hold graphics
    private Container content;

    //--ARRAYLISTS
    public ArrayList<Wall> arr_WallList = new ArrayList<>();
    ArrayList<Goblin> arr_Goblins = new ArrayList<>();

    //Game Thread
    private Thread gameThread;
    private boolean gameOn;

    //---GAME PREP CONSTRUCTOR
    public GamePrep() {
        //GAME WINDOW
        super("Dungeon Crawler Game");
        setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
        setResizable(false);
        content = getContentPane();
        content.setBackground(Color.gray); //window color


        setLayout(null);
        //handle closing of program window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add key listener and focus window to move chars
        content.addKeyListener(this); //add a key listener
        content.setFocusable(true);//add focus


        //_____ TEST CODE START


        //_____ TEST CODE end

        //ASSETS FOR Game - HERO, ENEMIES, ETC
        //___GAME SCORE
        gameScore = 0;
        //HERO ALPHA
        heroAlpha = new Hero();//creates hero
        heroLabel = new JLabel();//creates a label to hold hero img
        heroImage = new ImageIcon(getClass().getResource(heroAlpha.getFilename()));
        heroLabel.setIcon(heroImage);//set hero img to label
        heroLabel.setSize(heroAlpha.getWidth(), heroAlpha.getHeight());// set size of the label

        //HERO ALPHA COORDINATES ON SCREEN
        heroAlpha.setX(280);
        heroAlpha.setY(140);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());
        add(heroLabel);


        //------WALL LOOP: Loop Through Walls to Build Level
        for (int i = 0; i < LevelOneData.arr_WallCoordinates.length; i++) {
            int x = LevelOneData.arr_WallCoordinates[i][0];
            int y = LevelOneData.arr_WallCoordinates[i][1];

            //--- create wall with coordinate from loop
            Wall wall = new Wall(x, y);

            //----ADD Walls to JLabel
            JLabel wallLabel = new JLabel();
            ImageIcon wallImage = new ImageIcon(getClass().getResource(wall.getFilename()));
            wallLabel.setIcon(wallImage);
            wallLabel.setSize(wall.getWidth(), wall.getHeight());
            wall.setHeroAlpha(heroAlpha);
            wallLabel.setLocation(x, y);
            add(wallLabel);

            arr_WallList.add(wall);
        }


        //Goblin Alpha
        goblinAlpha = new Goblin();
        goblinAlphaLabel = new JLabel();
        goblinAlphaImage = new ImageIcon(getClass().getResource(goblinAlpha.getFilename()));
        goblinAlphaLabel.setIcon(goblinAlphaImage);
        goblinAlphaLabel.setSize(goblinAlpha.getWidth(), goblinAlpha.getHeight());
        goblinAlpha.setGoblinLabel(goblinAlphaLabel);// this passes in label into goblin1 object
        goblinAlpha.setHeroAlpha(heroAlpha); //this passes heroAlpha into goblin1 object
        goblinAlpha.setHeroAlphaLabel(heroLabel);// this passes heroAlpha label into goblin 1
        //GOBLIN 1 COORDINATES THEN ADD TO SCREEN
        goblinAlphaLabel.setLocation(goblinAlpha.getX(), goblinAlpha.getY());
        add(goblinAlphaLabel);
        arr_Goblins.add(goblinAlpha);


        //ADD GoblinBeta
        goblinBeta = new Goblin(0, 0);
        goblinBetaLabel = new JLabel();
        goblinBetaImage = new ImageIcon(getClass().getResource(goblinBeta.getFilename()));
        goblinBetaLabel.setIcon(goblinBetaImage);
        goblinBetaLabel.setSize(goblinBeta.getWidth(), goblinBeta.getHeight());
        goblinBeta.setGoblinLabel(goblinBetaLabel);// this passes in label into goblin1 object
        goblinBeta.setHeroAlpha(heroAlpha); //this passes heroAlpha into goblin1 object
        goblinBeta.setHeroAlphaLabel(heroLabel);// this passes heroAlpha label into goblin 1
        //GOBLIN 1 COORDINATES THEN ADD TO SCREEN
        goblinBetaLabel.setLocation(goblinBeta.getX(), goblinBeta.getY());
        add(goblinBetaLabel);
        arr_Goblins.add(goblinBeta);


        //ANIMATE BUTTON STARTS GAME
        AnimateButton = new JButton("Run");
        AnimateButton.setSize(100, 50);
        AnimateButton.setLocation(GameProperties.SCREEN_WIDTH - 120, GameProperties.SCREEN_HEIGHT - 155);
        add(AnimateButton);
        AnimateButton.addActionListener(this);
        AnimateButton.setFocusable(false);
        goblinAlpha.setAnimationButton(AnimateButton);

    }

    //---MAIN METHOD(Start Game)
    public static void main(String[] args) {
        GamePrep myGame = new GamePrep();
        myGame.setVisible(true);
    }

    //EVENT LISTENERS START
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        //-- UP, DOWN, LEFT, RIGHT
        if (e.getKeyCode() == KeyEvent.VK_UP) {
//            walkLogic(1);
            walkUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            walkLogic(2);
            walkDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            walkLogic(3);
            walkLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            walkLogic(4);
            walkRight();
        }

        // SPACE BAR
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //---Fire Hero Arrow Method
            if (gameOn) {
                heroAlpha.fireArrow();
            }

//            heroAlpha.fireArrow();
//            System.out.println(heroAlpha.arr_arrowsFlying.size());

        }

        // Z CHECK SOME DATA
        if (e.getKeyCode() == KeyEvent.VK_Z) {
//                addWinner();
        }


    }

    public void keyReleased(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        goblinAlpha.moveGoblin(this);
        goblinBeta.moveGoblin(this);
//        heroAlpha.startHeroThread();
        startGame();
        repaint();
    }
    //---EVENT LISTENERS END


    //---WALK LOGIC START
    private void walkUp() {
//        int dx = heroAlpha.getX();
        int dy = heroAlpha.getY();

        //set hero direction
        heroAlpha.setDirection(1);

        //todo when hero direction is set, make setter update filename
        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroUp-32x48.png")));

        //set player to new step
        dy = dy - GameProperties.CHARACTER_STEP;
        heroAlpha.setY(dy);

        //This code checks for collision with wall from new locations
        for (int i = 0; i < arr_WallList.size(); i++) {
            Rectangle r = arr_WallList.get(i).r;

            //if collision with hero, set hero to bottom of rectangle
            if (heroAlpha.r.intersects(r)) {
                dy = (int) r.getMaxY();
            }
        }//end of for loop

        //if character goes past screen, round-robin
        if (dy + heroAlpha.getHeight() < 0) {
            dy = GameProperties.SCREEN_HEIGHT;
        }

        heroAlpha.setY(dy);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

    }

    private void walkDown() {
        int dy = heroAlpha.getY();

        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroAlpha_Down_30x44.png")));
        heroAlpha.setDirection(2);

        //set player to new step
        dy = dy + GameProperties.CHARACTER_STEP;
        heroAlpha.setY(dy);

        //This code checks for collision with wall from new locations
        for (int i = 0; i < arr_WallList.size(); i++) {
            Rectangle r = arr_WallList.get(i).r;

            //if collision with hero, set hero to bottom of rectangle
            if (heroAlpha.r.intersects(r)) {
                dy = (int) r.getMinY() - heroAlpha.getHeight();
            }
        }//end of for loop

        //if character goes past screen, round-robin
        if (dy > GameProperties.SCREEN_HEIGHT) dy = -1 * heroAlpha.getHeight();

        heroAlpha.setY(dy);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

    }

    private void walkLeft() {

        int dx = heroAlpha.getX();

        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroLeft_32x48.png")));

        heroAlpha.setDirection(3);

        //move character by game step and set
        dx -= GameProperties.CHARACTER_STEP;
        heroAlpha.setX(dx);

        //This code checks for collision with wall from new locations
        for (int i = 0; i < arr_WallList.size(); i++) {
            Rectangle r = arr_WallList.get(i).r;

            if (r.intersects(heroAlpha.r)) {
                dx = (int) r.getMaxX();
            }
        }//end of for loop


        //if character goes past screen, round-robin
        if (dx + heroAlpha.getWidth() < 0) dx = GameProperties.SCREEN_WIDTH;

        heroAlpha.setX(dx);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

    }

    private void walkRight() {
        int dx = heroAlpha.getX();

        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroRight_32x48.png")));
        heroAlpha.setDirection(4);

        //move character by game step and set
        dx += GameProperties.CHARACTER_STEP;
        heroAlpha.setX(dx);

        //This code checks for collision with wall from new locations
        for (int i = 0; i < arr_WallList.size(); i++) {
            Rectangle r = arr_WallList.get(i).r;

            //if collision with hero, set hero to bottom of rectangle
            if (r.intersects(heroAlpha.r)) {
                dx = (int) r.getX() - r.width;
            }
        }//end of for loop

        //if character goes past screen, round-robin
        if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();

        heroAlpha.setX(dx);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

    }
    //---WALK LOGIC END

    //------------------------------------
    //----METHODS START
//    public void refreshScreen() {
////        SwingUtilities.updateComponentTreeUI(this);
//        repaint();
//    }

    // MOVE ARROWS
    public void arrowLogic() {
        if (heroAlpha.arr_HeroArrowsFlying.size() > 0) {
            //LOOP through Arrows Array
            for (int i = 0; i < heroAlpha.arr_HeroArrowsFlying.size(); i++) {
                //---VARS
                int arrowX = heroAlpha.arr_HeroArrowsFlying.get(i).getX();
                int arrowY = heroAlpha.arr_HeroArrowsFlying.get(i).getY();
                int arrowDirection = heroAlpha.arr_HeroArrowsFlying.get(i).getDirection();
                Rectangle arrowRectangle = heroAlpha.arr_HeroArrowsFlying.get(i).getRectangle();
                boolean collision = false;
                int wallSize = arr_WallList.size();


                //---CODE
                //check Arrow for collision with walls
                for (int j = 0; j < wallSize; j++) {
                    Rectangle wallRectangle = arr_WallList.get(j).getR();
                    if (wallRectangle.intersects(arrowRectangle)) {
                        collision = true;
                        System.out.println("wall collision");
                    }
                }//end of  Wall loop

                //GOBLIN LOOP: Check for Goblin Collision
                for (int k = 0; k < arr_Goblins.size(); k++) {
                    Rectangle goblinRectangle = arr_Goblins.get(k).getRectangle();
                    Boolean goblinVisible = arr_Goblins.get(k).getVisible();


                    if (goblinRectangle.intersects(arrowRectangle) && goblinVisible == true) {
                        collision = true;
                        int health = arr_Goblins.get(k).getHealth();
                        health -= GameProperties.ARROW_DAMAGE;
                        arr_Goblins.get(k).setHealth(health);
                        System.out.println("goblin hit");
                        System.out.println(health);
                        System.out.println(goblinVisible);
                    } else if (goblinVisible == false) {
                        gameScore += 10;
                        arr_Goblins.get(k).removeGoblinLabel();
                        arr_Goblins.remove(k);
                        System.out.println("Game Score: " + gameScore);

                    }

                }
//                if (goblinAlpha.getRectangle().intersects(arrowRectangle) && goblinAlpha.getVisible() == true) {
//                    collision = true;
//                    int health = goblinAlpha.getHealth();
//                    health -= GameProperties.ARROW_DAMAGE;
//                    goblinAlpha.setHealth(health);
//
//                }

                // if collision remove arrow, no collision then move the arrow
                if (collision == true) {
                    System.out.println("collision true");
                    JLabel targetLabel = heroAlpha.arr_HeroArrowsFlying.get(i).arrowLabel;
                    Container parent = targetLabel.getParent();
                    parent.remove(targetLabel);
                    parent.validate();
                    parent.repaint();
                    heroAlpha.arr_HeroArrowsFlying.remove(i);
                } else {
                    //move arrows
                    switch (arrowDirection) {
                        case 1:
                            arrowY -= GameProperties.ARROW_STEP;
                            heroAlpha.arr_HeroArrowsFlying.get(i).setY(arrowY);
                            heroAlpha.arr_HeroArrowsFlying.get(i).arrowLabel.setLocation(arrowX, arrowY);
                            add(heroAlpha.arr_HeroArrowsFlying.get(i).arrowLabel);
                            break;
                        case 2:
                            arrowY += GameProperties.ARROW_STEP;
                            heroAlpha.arr_HeroArrowsFlying.get(i).setY(arrowY);
                            heroAlpha.arr_HeroArrowsFlying.get(i).arrowLabel.setLocation(arrowX, arrowY);
                            add(heroAlpha.arr_HeroArrowsFlying.get(i).arrowLabel);
                            break;
                        case 3:
                            arrowX -= GameProperties.ARROW_STEP;
                            heroAlpha.arr_HeroArrowsFlying.get(i).setX(arrowX);
                            heroAlpha.arr_HeroArrowsFlying.get(i).arrowLabel.setLocation(arrowX, arrowY);
                            add(heroAlpha.arr_HeroArrowsFlying.get(i).arrowLabel);
                            break;
                        case 4:
                            arrowX += GameProperties.ARROW_STEP;
                            heroAlpha.arr_HeroArrowsFlying.get(i).setX(arrowX);
                            heroAlpha.arr_HeroArrowsFlying.get(i).arrowLabel.setLocation(arrowX, arrowY);
                            add(heroAlpha.arr_HeroArrowsFlying.get(i).arrowLabel);
                            break;
                    }
                }

            }//end of Arrow loop
        }


//        System.out.println(heroAlpha.arr_arrowsFlying.size());
//        SwingUtilities.updateComponentTreeUI(this);
    }


    //REMOVE GOBLIN
    public void removeGoblin() {
//        if (goblinAlpha.getVisible() == false) {
//            remove(goblinAlphaLabel);
////            Container parent = goblinAlphaLabel.getParent();
////            parent.remove(goblinAlphaLabel);
////            parent.validate();
////            parent.repaint();
////            goblinAlpha.r = null;
////            heroAlpha.arr_arrowsFlying.remove(i);
//        }

        if (goblinAlpha.getVisible() == false) {
//            goblinAlpha.getGameFrame(this);
        }
    }

    //METHODS END

    //---THREAD START
    public void startGame() {
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
        gameOn = true;
    }

    public void run() {
        System.out.println("game on");
        while (gameOn) {
            arrowLogic();

            if (arr_Goblins.size() == 0) {
                System.out.println("Won the Game");
                gameOn = false;
                String playerName = JOptionPane.showInputDialog("Add your Name");
                getWinner(playerName, gameScore);

            }


            try {
                Thread.sleep(GameProperties.GAME_PREP_THREAD_TIME);
                repaint();
            } catch (Exception e) {

            }
        }
    }//run() end code block


    //THREAD END


    //Database Stuff
    public void getWinner(String name, int score) {
        System.out.println("Player Name: " + name);
        System.out.println("Score: " + score);


//
//        String playerName = JOptionPane.showInputDialog("Add your Name");
//        repaint();
    }

    public void writeWinnerToDatabase() {

    }


}// end of class, don't delete------

