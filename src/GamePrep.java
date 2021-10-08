import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;


public class GamePrep extends JFrame implements ActionListener, KeyListener {

    private static final long serialVersionUID = 6106269076155338045L;

    //Hero Vars
    private Hero heroAlpha;//Hero object
    private JLabel heroLabel;
    private ImageIcon heroImage;

    //Enemy Alpha
    private Goblin goblinAlpha;
    private JLabel goblinAlphaLabel;
    private ImageIcon goblinAlphaImage;

    //Goblin Beta
    private Goblin goblinBeta;
    private JLabel goblinBetaLabel;
    private ImageIcon goblinBetaImage;

    //Wall One
    private Wall wall1;
    private JLabel wall1Label;
    private ImageIcon wall1Image;

    //TIMER
//    Timer time;

    private JButton HideTardisButton, AnimateButton;

    //Container to hold graphics
    private Container content;


    //ARROW STUFF
//    ArrayList<Arrow> heroArrows = new ArrayList<Arrow>();

    //Walls
    ArrayList<Wall> arr_WallList = new ArrayList<>();

    //GAME PREP CONSTRUCTOR
    public GamePrep() {
        super("Dungeon Crawler Game");
        setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
        setResizable(false);
        //GAME WINDOW
        content = getContentPane();
        content.setBackground(Color.gray);
        setLayout(null);

        //add key listener and focus window to move chars
        content.addKeyListener(this); //add a key listener
        content.setFocusable(true);//add focus

        //TIMER
//        time = new Timer(5, this);
//        time.start();

        //handle closing of program window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ASSETS FOR Game - HERO, ENEMIES, ETC
        //HERO ALPHA
        heroAlpha = new Hero();//creates hero
        heroLabel = new JLabel();//creates a label to hold hero img
        heroImage = new ImageIcon(getClass().getResource(heroAlpha.getFilename()));
        heroLabel.setIcon(heroImage);//set hero img to label
        heroLabel.setSize(heroAlpha.getWidth(), heroAlpha.getHeight());// set size of the label

        //HERO ALPHA COORDINATES ON SCREEN
        heroAlpha.setX(0);
        heroAlpha.setY(300);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());
        add(heroLabel);

        //------ WALL STUFF
        //---- Loop Through Walls to Build Level
        for (int i = 0; i < GameProperties.arr_WallCoordinates.length; i++) {
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

        }

        //Wall Array
//        Wall myWall = new Wall();
//        arr_WallList.add(myWall);
//        arr_WallList.add(new Wall());


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


        //ADD Walls
//        wall1 = new Wall();
//        wall1Label = new JLabel();
//        wall1Image = new ImageIcon(getClass().getResource(wall1.getFilename()));
//        wall1Label.setIcon(wall1Image);
//        wall1Label.setSize(wall1.getWidth(), wall1.getHeight());
//
//        wall1.setHeroAlpha(heroAlpha); //this passes heroAlpha into goblin1 object
//        wall1.setX(200);
//        wall1.setY(200);
//        wall1Label.setLocation(200, 200);
//
////        wall1.setX(wall1Label.getLocation().x);
////        wall1.setY(wall1Label.getLocation().y);
//        add(wall1Label);


        //ANIMATE BUTTON STARTS GAME
        AnimateButton = new JButton("Run");
        AnimateButton.setSize(100, 50);
        AnimateButton.setLocation(GameProperties.SCREEN_WIDTH - 120, GameProperties.SCREEN_HEIGHT - 155);
        add(AnimateButton);
        AnimateButton.addActionListener(this);
        AnimateButton.setFocusable(false);
        goblinAlpha.setAnimationButton(AnimateButton);

    }


    public static void main(String[] args) {
        GamePrep myGame = new GamePrep();
        myGame.setVisible(true);

    }

    //GAME CONTROLLERS
    public void keyTyped(KeyEvent e) {
    }


    public void keyPressed(KeyEvent e) {
//        int dx = heroAlpha.getX();
//        int dy = heroAlpha.getY();
//        int direction = heroAlpha.getDirection();

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            walkUp();
//            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroUp-32x48.png")));
//            heroAlpha.setDirection(1);
//            dy -= GameProperties.CHARACTER_STEP;
//
//            if (dy + heroAlpha.getHeight() < 0) {
//                dy = GameProperties.SCREEN_HEIGHT;
//            }

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            walkDown();
//            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroDown_32x48.png")));
//            heroAlpha.setDirection(2);
//
////            dy += GameProperties.CHARACTER_STEP;
////            if (dy > GameProperties.SCREEN_HEIGHT) dy = -1 * heroAlpha.getHeight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            walkLeft();
//            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroLeft_32x48.png")));
//            heroAlpha.setDirection(3);
//
//            dx -= GameProperties.CHARACTER_STEP;
//            if (dx + heroAlpha.getWidth() < 0) dx = GameProperties.SCREEN_WIDTH;


        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            walkRight();
//            boolean test;
//            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroRight_32x48.png")));
//            test = wall1.detectHeroCollision();
//            heroAlpha.setDirection(4);
//            if (test == true) {
//                System.out.println("Wall Boundry!");
//            } else {
//                wall1.detectHeroCollision();
//                dx += GameProperties.CHARACTER_STEP;
//                wall1.detectHeroCollision();
//                if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();
//            }


//            wall1.detectHeroCollision();
//            dx += GameProperties.CHARACTER_STEP;
//            wall1.detectHeroCollision();
//            if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();
        }

        // SPACE BAR
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

        }

        // THING CHECKER
        if (e.getKeyCode() == KeyEvent.VK_Z) {

        }


    }




    public void keyReleased(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {

//           wall1.detectHeroCollision();
        goblinAlpha.moveGoblin();
        goblinBeta.moveGoblin();
//        test2();
//        test3();
//        repaint();

    }


    private void test(int x, int y, int d) {
//        heroAlpha.attack(x, y, d);
        System.out.println(heroAlpha.getFilename());

    }

    private void test2() {
//        heroAlpha.attack(x, y, d);
        System.out.println("TEST");

    }

    private void walkUp() {
        int test;
        int b;
        int dx = heroAlpha.getX();
        int dy = heroAlpha.getY();
        int dr = heroAlpha.getDirection();

        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroUp-32x48.png")));

        heroAlpha.setDirection(1);

//        b = arr_WallList.get(0).detectHeroCollision();
//
        b = 0;
//
//        b = wall1.detectHeroCollision();

        if (b == 1 && dr == 1) {
            System.out.println("Wall Boundry!");
        } else {
            dy -= GameProperties.CHARACTER_STEP;
            if (dy + heroAlpha.getHeight() < 0) {
                dy = GameProperties.SCREEN_HEIGHT;
            }
        }

        heroAlpha.setX(dx);
        heroAlpha.setY(dy);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

    }

    private void walkDown() {
//        boolean test;
        int b;
        int dx = heroAlpha.getX();
        int dy = heroAlpha.getY();
        int dr = heroAlpha.getDirection();

        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroAlpha_Down_30x44.png")));
        heroAlpha.setDirection(2);

//        b = wall1.detectHeroCollision();
//        b = arr_WallList.get(0).detectHeroCollision();
        b = 0;
        if (b == 2 && dr == 2) {
            System.out.println("Wall Boundry!");
        } else {
            dy += GameProperties.CHARACTER_STEP;
            if (dy > GameProperties.SCREEN_HEIGHT) dy = -1 * heroAlpha.getHeight();
        }

        heroAlpha.setX(dx);
        heroAlpha.setY(dy);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

    }

    private void walkLeft() {
//        boolean test;
        int b;
        int dx = heroAlpha.getX();
        int dy = heroAlpha.getY();
        int dr = heroAlpha.getDirection();

        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroLeft_32x48.png")));

        heroAlpha.setDirection(3);

//        b = wall1.detectHeroCollision();
//        b = arr_WallList.get(0).detectHeroCollision();
        b = 0;

        if (b == 3 && dr == 3) {
            System.out.println("Wall Boundry!");
        } else {
            dx -= GameProperties.CHARACTER_STEP;
            if (dx + heroAlpha.getWidth() < 0) dx = GameProperties.SCREEN_WIDTH;
        }

        heroAlpha.setX(dx);
        heroAlpha.setY(dy);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

    }

    private void walkRight() {
        boolean test;
        int b;
        int dx = heroAlpha.getX();
        int dy = heroAlpha.getY();
        int dr = heroAlpha.getDirection();

        heroLabel.setIcon(new ImageIcon(getClass().getResource("heroRight_32x48.png")));

        heroAlpha.setDirection(4);

//        b = wall1.detectHeroCollision();
//        b = arr_WallList.get(0).detectHeroCollision();
        b = 0;

        if (b == 4 && dr == 4) {
            System.out.println("Wall Boundry!");
        } else {
//            wall1.detectHeroCollision();
            dx += GameProperties.CHARACTER_STEP;
//            wall1.detectHeroCollision();
            if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();
        }

        heroAlpha.setX(dx);
        heroAlpha.setY(dy);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

    }


//    private void test3(){
//        wall1.detectHeroCollision();
//    }

//    public void paint(Graphics g) {
//        super.paint(g);
//        System.out.println("Repainting ");
//
//    }
}
