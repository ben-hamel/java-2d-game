import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class GamePrep extends JFrame implements ActionListener, KeyListener {

    private static final long serialVersionUID = 6106269076155338045L;

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

    //--ARROWS
//    ArrayList<Arrow> heroArrows = new ArrayList<Arrow>();

    //---WALLS
    public ArrayList<Wall> arr_WallList = new ArrayList<>();

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

        //ASSETS FOR Game - HERO, ENEMIES, ETC
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

        //------ WALL STUFF-----Loop Through Walls to Build Level
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


        //ENEMY ONE
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

    //CONTROLS
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

        }

        // Z CHECK SOME DATA
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            System.out.println(heroAlpha.x);
            System.out.println(heroAlpha.y);
//            heroAlpha.fireArrow();

//            System.out.println(wall);
        }


    }

    public void keyReleased(KeyEvent e) {
    }

    //start threads
    public void actionPerformed(ActionEvent e) {
        goblinAlpha.moveGoblin();
        goblinBeta.moveGoblin();
    }

//    private void walkLogic(int direction) {
//        boolean collision = false;
//        int dx = heroAlpha.getX();
//        int dy = heroAlpha.getY();
//        int nextUp = heroAlpha.y - GameProperties.CHARACTER_STEP;
//        int nextDown = heroAlpha.y + GameProperties.CHARACTER_STEP;
//        int nextRight = heroAlpha.x - GameProperties.CHARACTER_STEP;
//        int nextLeft = heroAlpha.x + GameProperties.CHARACTER_STEP;
//        boolean upperWall = false;
//        boolean downwardWall = false;
//        boolean leftWall = false;
//        boolean rightWall = false;
//
//
//        //set hero direction
//        heroAlpha.setDirection(direction);
//
//        //todo when hero direction is set, make setter update filename
//        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroUp-32x48.png")));
//
//        //----New code
//
//        //LOOP FOR COLLISON
//        for (int i = 0; i < arr_WallList.size(); i++) {
//            Rectangle r = arr_WallList.get(i).r;
//            int y = arr_WallList.get(i).y;
//            int x = arr_WallList.get(i).x;
//
//            if (heroAlpha.r.intersects(r)) {
//                collision = true;
//
//            }
//
//            if (heroAlpha.direction == 1 && (nextUp == y)) {
//                upperWall = true;
//
//                System.out.println("upper Wall" + upperWall);
//
//            }
//
//            if (heroAlpha.direction == 2 && (nextDown == x)) {
//                downwardWall = true;
//                System.out.println("downwardWall" + downwardWall);
//
//            }
//
//            if (heroAlpha.direction == 3 && (nextLeft == x)) {
//                leftWall = true;
//
//
//                System.out.println("left Wall" + leftWall);
//
//            }
//
//            if (heroAlpha.direction == 4 && (nextRight == x)) {
//                rightWall = true;
//                System.out.println("right Wall" + rightWall);
//            }
//        }//----- end of loop
//
//        //WALK Direction
//
//        if (collision == true && upperWall == true && heroAlpha.direction == 1) {
//            System.out.println("hit wall");
//        } else {
//            if (heroAlpha.getDirection() == 1) {
//                dy -= GameProperties.CHARACTER_STEP;
//                System.out.println("up worked");
////                if (dy + heroAlpha.getHeight() < 0) {
////                    dy = GameProperties.SCREEN_HEIGHT;
//            } else if (heroAlpha.getDirection() == 2) {
//                dy += GameProperties.CHARACTER_STEP;
//                System.out.println("down worked");
////                    if (dy > GameProperties.SCREEN_HEIGHT) dy = -1 * heroAlpha.getHeight();
//            } else if (heroAlpha.getDirection() == 3) {
//                dx -= GameProperties.CHARACTER_STEP;
//                System.out.println("left worked");
////                    if (dx + heroAlpha.getWidth() < 0) dx = GameProperties.SCREEN_WIDTH;
//            } else if (heroAlpha.getDirection() == 4) {
//                dx += GameProperties.CHARACTER_STEP;
//                System.out.println("right worked");
////                    if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();
//            }
//        }
//
//
//        if (collision == true && downwardWall == true && heroAlpha.direction == 2) {
//            System.out.println("hit wall");
//        } else {
//            if (heroAlpha.getDirection() == 1) {
//                dy -= GameProperties.CHARACTER_STEP;
//                System.out.println("up worked");
////                if (dy + heroAlpha.getHeight() < 0) {
////                    dy = GameProperties.SCREEN_HEIGHT;
//            } else if (heroAlpha.getDirection() == 2) {
//                dy += GameProperties.CHARACTER_STEP;
//                System.out.println("down worked");
////                    if (dy > GameProperties.SCREEN_HEIGHT) dy = -1 * heroAlpha.getHeight();
//            } else if (heroAlpha.getDirection() == 3) {
//                dx -= GameProperties.CHARACTER_STEP;
//                System.out.println("left worked");
////                    if (dx + heroAlpha.getWidth() < 0) dx = GameProperties.SCREEN_WIDTH;
//            } else if (heroAlpha.getDirection() == 4) {
//                dx += GameProperties.CHARACTER_STEP;
//                System.out.println("right worked");
////                    if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();
//            }
//        }
//
//
//        if (collision == true && leftWall == true && heroAlpha.direction == 3) {
//            System.out.println("hit wall");
//        } else {
//            if (heroAlpha.getDirection() == 1) {
//                dy -= GameProperties.CHARACTER_STEP;
//                System.out.println("up worked");
////                if (dy + heroAlpha.getHeight() < 0) {
////                    dy = GameProperties.SCREEN_HEIGHT;
//            } else if (heroAlpha.getDirection() == 2) {
//                dy += GameProperties.CHARACTER_STEP;
//                System.out.println("down worked");
////                    if (dy > GameProperties.SCREEN_HEIGHT) dy = -1 * heroAlpha.getHeight();
//            } else if (heroAlpha.getDirection() == 3) {
//                dx -= GameProperties.CHARACTER_STEP;
//                System.out.println("left worked");
////                    if (dx + heroAlpha.getWidth() < 0) dx = GameProperties.SCREEN_WIDTH;
//            } else if (heroAlpha.getDirection() == 4) {
//                dx += GameProperties.CHARACTER_STEP;
//                System.out.println("right worked");
////                    if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();
//            }
//        }
//
//
//        if (collision == true && rightWall == true && heroAlpha.direction == 4) {
//            System.out.println("hit wall");
//        } else {
//            if (heroAlpha.getDirection() == 1) {
//                dy -= GameProperties.CHARACTER_STEP;
//                System.out.println("up worked");
////                if (dy + heroAlpha.getHeight() < 0) {
////                    dy = GameProperties.SCREEN_HEIGHT;
//            } else if (heroAlpha.getDirection() == 2) {
//                dy += GameProperties.CHARACTER_STEP;
//                System.out.println("down worked");
////                    if (dy > GameProperties.SCREEN_HEIGHT) dy = -1 * heroAlpha.getHeight();
//            } else if (heroAlpha.getDirection() == 3) {
//                dx -= GameProperties.CHARACTER_STEP;
//                System.out.println("left worked");
////                    if (dx + heroAlpha.getWidth() < 0) dx = GameProperties.SCREEN_WIDTH;
//            } else if (heroAlpha.getDirection() == 4) {
//                dx += GameProperties.CHARACTER_STEP;
//                System.out.println("right worked");
////                    if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();
//            }
//        }
//
//
//        heroAlpha.setX(dx);
//        heroAlpha.setY(dy);
//        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());
//    }//end of walk logic


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
        System.out.println("old x" + dx);

        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroLeft_32x48.png")));

        heroAlpha.setDirection(3);

        //move character by game step and set
        dx -= GameProperties.CHARACTER_STEP;
        heroAlpha.setX(dx);

        //This code checks for collision with wall from new locations
        for (int i = 0; i < arr_WallList.size(); i++) {
            Rectangle r = arr_WallList.get(i).r;

//            System.out.println("r x"+r.getX());
            //if collision with hero, set hero to bottom of rectangle

            if (r.intersects(heroAlpha.r)) {
                dx = (int) r.getMaxX();
                System.out.println("dx new" + dx);
//                System.out.println("r x"+r.getX());
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
                System.out.println("interect x" + dx);
            }
        }//end of for loop

        //if character goes past screen, round-robin
        if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();

        heroAlpha.setX(dx);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

    }

}// end of class, don't delete------

