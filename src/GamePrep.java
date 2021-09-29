import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;
//import java.io.Serializable;

public class GamePrep extends JFrame implements ActionListener, KeyListener {

    @Serial
    private static final long serialVersionUID = 6106269076155338045L;

    //game objects
    private Hero heroAlpha;//Hero object



    //create graphic using Label
    private JLabel heroLabel;
    private ImageIcon heroImage;


    //Container to hold graphics
    private Container content;

    //arrow
    private Arrow myArrow; //arrow projectile object
    private JLabel arrowLabel;
    private ImageIcon arrowImage;
    int arrowDirection = 0;

    //game prep constructor
    public GamePrep() {
        super("Dungeon Crawler Game");
        setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
        setResizable(false);


        //create hero and add to content pane
        heroAlpha = new Hero();//creates hero
        heroLabel = new JLabel();//creates a label to hold hero img
        heroImage = new ImageIcon(getClass().getResource(heroAlpha.getFilename()));
        heroLabel.setIcon(heroImage);//set hero img to label
        heroLabel.setSize(heroAlpha.getWidth(), heroAlpha.getHeight());// set size of the label

        //create game window
        content = getContentPane();
        content.setBackground(Color.gray);
        setLayout(null);

        //coordinates for the hero
        heroAlpha.setX(0);
        heroAlpha.setY(100);

        //add hero label to game window
        add(heroLabel);

        //set coordinates for hero label
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());


        content.addKeyListener(this); //add a key listener
        content.setFocusable(true);//add focus

        //test space
//        int dx = heroAlpha.getX();
//        int dy = heroAlpha.getY();
//        int arrowDirection = 0;

        //handle closing of program window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public static void main(String[] args) {
        GamePrep myGame = new GamePrep();
        myGame.setVisible(true);

    }

    //arrow function one
//    public void launchArrow(int dx, int dy, int arrowDirection) {
//        myArrow = new Arrow(16,16);
//        myArrow = new Arrow(16,16);//creates hero
//        arrowLabel = new JLabel();//creates a label to hold hero img
//        arrowImage = new ImageIcon(getClass().getResource(myArrow.getFilename()));
//        arrowLabel.setIcon(arrowImage);//set hero img to label
//        arrowLabel.setSize(myArrow.getWidth(), myArrow.getHeight());// set size of the label
//
//        //coordinates for hero taken from hero position
//        myArrow.setX(dx);
//        myArrow.setY(dy);
//
//        //add hero label to game window
//        add(arrowLabel);
//
//        //set coordinates for hero label
//        arrowLabel.setLocation(myArrow.getX(), myArrow.getY());
//
//        //
//        int x = myArrow.getX();
//        int y = myArrow.getY();
//
//
//        //arrow movement
//        if (arrowDirection == 1) {
//
//        } else if (arrowDirection == 2) {
//            System.out.println("down arrow");
//        } else if(arrowDirection == 3)  {
//            System.out.println("arrow left");
//        }else if(arrowDirection == 4)  {
////            arrowLabel.setIcon(new ImageIcon(getClass().getResource("arrowRight_133x48.png")));
////            arrowLabel.setSize(133,48);
////            arrowLabel.setLocation(myArrow.getX(), myArrow.getY());
////            System.out.println("arrow right");
//////            x += GameProperties.CHARACTER_STEP;
//////           for(int i = x; i < x; i = i + GameProperties.CHARACTER_STEP){
//////                myArrow.setX(i);
//////            }
//
//
//            do {
//                x += GameProperties.CHARACTER_STEP;
//                System.out.println("do"+ x);
//
//                arrowLabel.setSize(133,48);
//                arrowLabel.setLocation(myArrow.getX(), myArrow.getY());
//                arrowLabel.setIcon(new ImageIcon(getClass().getResource("arrowRight_133x48.png")));
//                System.out.println("arrow right");
//            }
//            while (x < GameProperties.SCREEN_WIDTH);
//        } else {
//            System.out.println("arrow nones  "+arrowDirection);
//        }
//
//
//    }

    //arrow function 2
    public void launchArrow(int dx, int dy, int arrowDirection) {
        myArrow = new Arrow(16,16);
        myArrow = new Arrow(16,16);//creates hero
        arrowLabel = new JLabel();//creates a label to hold hero img
        arrowImage = new ImageIcon(getClass().getResource(myArrow.getFilename()));
        arrowLabel.setIcon(arrowImage);//set hero img to label
        arrowLabel.setSize(myArrow.getWidth(), myArrow.getHeight());// set size of the label

        //coordinates for hero taken from hero position
        myArrow.setX(dx);
        myArrow.setY(dy);

        //add hero label to game window
        add(arrowLabel);

        //set coordinates for hero label
        arrowLabel.setLocation(myArrow.getX(), myArrow.getY());

        //
        int x = myArrow.getX();
        int y = myArrow.getY();


        //arrow movement
        if (arrowDirection == 1) {

        } else if (arrowDirection == 2) {
            System.out.println("down arrow");
        } else if(arrowDirection == 3)  {
            System.out.println("arrow left");
        }else if(arrowDirection == 4)  {
            arrowLabel.setIcon(new ImageIcon(getClass().getResource("arrowRight_133x48.png")));
            arrowLabel.setSize(133,48);
            arrowLabel.setLocation(myArrow.getX(), myArrow.getY());
            System.out.println("arrow right");
            x += GameProperties.CHARACTER_STEP;
           for(int i = x; i < x; i = i + GameProperties.CHARACTER_STEP){
                myArrow.setX(i);
            }


            do {
                x += GameProperties.CHARACTER_STEP;
                System.out.println("do"+ x);

                arrowLabel.setSize(133,48);
                arrowLabel.setLocation(myArrow.getX(), myArrow.getY());
                arrowLabel.setIcon(new ImageIcon(getClass().getResource("arrowRight_133x48.png")));
                System.out.println("arrow right");
            }
            while (x < GameProperties.SCREEN_WIDTH);
        } else {
            System.out.println("arrow nones  "+arrowDirection);
        }


    }

    public void keyTyped(KeyEvent e) {
    }


    public void keyPressed(KeyEvent e) {
        int dx = heroAlpha.getX();
        int dy = heroAlpha.getY();
//        int arrowDirection = 0;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroUp-32x48.png")));
            arrowDirection=1;
            System.out.println(arrowDirection);
            dy -= GameProperties.CHARACTER_STEP;
            if (dy + heroAlpha.getHeight() < 0) {
                dy = GameProperties.SCREEN_HEIGHT;
            }
            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroDown_32x48.png")));
            arrowDirection=2;
            dy += GameProperties.CHARACTER_STEP;
            if (dy > GameProperties.SCREEN_HEIGHT) dy = -1 * heroAlpha.getHeight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroLeft_32x48.png")));
            arrowDirection=3;
            dx -= GameProperties.CHARACTER_STEP;
            if (dx + heroAlpha.getWidth() < 0) dx = GameProperties.SCREEN_WIDTH;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroRight_32x48.png")));
            arrowDirection=4;
            dx += GameProperties.CHARACTER_STEP;
            if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();
        }

         if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            launchArrow(dx,dy, arrowDirection );
//             System.out.println(arrowDirection);
            //know direction of hero set shoot direction
            //launch projectile
        }

        heroAlpha.setX(dx);
        heroAlpha.setY(dy);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());
    }

    public void keyReleased(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {

    }
}
