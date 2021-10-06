import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class GamePrep extends JFrame implements ActionListener, KeyListener {

    private static final long serialVersionUID = 6106269076155338045L;

    //Hero Vars
    private Hero heroAlpha;//Hero object
    private JLabel heroLabel;
    private ImageIcon heroImage;

    //Enemy One
    private Goblin goblin1;
    private JLabel goblin1Label;
    private ImageIcon goblin1Image;

    private JButton HideTardisButton, AnimateButton;

    //Container to hold graphics
    private Container content;

    //ARROW STUFF
    ArrayList<Arrow> heroArrows = new ArrayList<Arrow>();

    //GAME PREP CONSTRUCTOR
    public GamePrep() {
        super("Dungeon Crawler Game");
        setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
        setResizable(false);

        //create hero
        heroAlpha = new Hero();//creates hero
        heroLabel = new JLabel();//creates a label to hold hero img
        heroImage = new ImageIcon(getClass().getResource(heroAlpha.getFilename()));
        heroLabel.setIcon(heroImage);//set hero img to label
        heroLabel.setSize(heroAlpha.getWidth(), heroAlpha.getHeight());// set size of the label

        //create 1st enemy
        goblin1 = new Goblin();
        goblin1Label = new JLabel();
        goblin1Image = new ImageIcon(getClass().getResource(goblin1.getFilename()));
        goblin1Label.setIcon(goblin1Image);
        goblin1Label.setSize(goblin1.getWidth(), goblin1.getHeight());
        goblin1.setGoblinLabel(goblin1Label);// this passes in label into goblin1 object
        goblin1.setHeroAlpha(heroAlpha); //this passes heroAlpha into goblin1 object
        goblin1.setHeroAlphaLabel(heroLabel);// this passes heroAlpha label into goblin 1

        AnimateButton = new JButton("Run");
        AnimateButton.setSize(100, 50);
        AnimateButton.setLocation(GameProperties.SCREEN_WIDTH - 120, GameProperties.SCREEN_HEIGHT - 155);
        add(AnimateButton);
        AnimateButton.addActionListener(this);
        AnimateButton.setFocusable(false);
        goblin1.setAnimationButton(AnimateButton);


        //create Game window
        content = getContentPane();
        content.setBackground(Color.gray);
        setLayout(null);

        //coordinates for the hero
        heroAlpha.setX(0);
        heroAlpha.setY(100);
        add(heroLabel);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

        //ADD Goblin1
        add(goblin1Label);
        goblin1Label.setLocation(goblin1.getX(), goblin1.getY());


        //add key listener and focus window to move chars
        content.addKeyListener(this); //add a key listener
        content.setFocusable(true);//add focus

        //handle closing of program window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public static void main(String[] args) {
        GamePrep myGame = new GamePrep();
        myGame.setVisible(true);

    }

    public void keyTyped(KeyEvent e) {
    }

    //GAME CONTROLLERS
    public void keyPressed(KeyEvent e) {
        int dx = heroAlpha.getX();
        int dy = heroAlpha.getY();
        int direction = heroAlpha.getDirection();

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroUp-32x48.png")));
            heroAlpha.setDirection(1);
            dy -= GameProperties.CHARACTER_STEP;

            if (dy + heroAlpha.getHeight() < 0) {
                dy = GameProperties.SCREEN_HEIGHT;
            }

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroDown_32x48.png")));
            heroAlpha.setDirection(2);

            dy += GameProperties.CHARACTER_STEP;
            if (dy > GameProperties.SCREEN_HEIGHT) dy = -1 * heroAlpha.getHeight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroLeft_32x48.png")));
            heroAlpha.setDirection(3);

            dx -= GameProperties.CHARACTER_STEP;
            if (dx + heroAlpha.getWidth() < 0) dx = GameProperties.SCREEN_WIDTH;

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            heroLabel.setIcon(new ImageIcon(getClass().getResource("heroRight_32x48.png")));
            heroAlpha.setDirection(4);
            dx += GameProperties.CHARACTER_STEP;
            if (dx > GameProperties.SCREEN_WIDTH) dx = -1 * heroAlpha.getWidth();
        }

        // SPACE BAR
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//             heroAlpha.fireArrow(dx,dy,direction); // create arrows using Hero
            //             heroArrows = heroAlpha.getArr_arrowsFlying();
//             Arrow myArrow = new Arrow(dx,dy,direction);
//             heroArrows.add(myArrow);
//             System.out.println(heroArrows);


            //show hero in mermory
//             System.out.println(heroAlpha);
//             goblin1.showHeroMemory();
//             test(dx,dy,direction);
        }

        // THING CHECKER
        if (e.getKeyCode() == KeyEvent.VK_Z) {
//            System.out.println(heroArrows);
//            System.out.println(heroArrows.get(0).filename);//check attribute of object in arraylist
//            goblin1.moveGoblin();


        }


        heroAlpha.setX(dx);
        heroAlpha.setY(dy);
        heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());
    }

    private void test(int x, int y, int d) {
        heroAlpha.attack(x, y, d);
        System.out.println(heroAlpha.getFilename());

    }


    public void keyReleased(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        goblin1.moveGoblin();
    }
}
