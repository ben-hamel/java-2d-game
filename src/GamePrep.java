import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;
import java.sql.*;
import java.util.ArrayList;


public class GamePrep extends JFrame implements ActionListener, KeyListener, Runnable {

    @Serial
    private static final long serialVersionUID = 6106269076155338045L;

    //___GAME SCORE
    public int gameScore;

    //---Hero ALPHA
    private Hero heroAlpha;//Hero object
    public static JLabel heroLabel;
    private ImageIcon heroImage;

    //--- START BUTTON
    private JButton HideTardisButton, AnimateButton;

    //---Container to hold graphics
    private Container content;

    //--ARRAYLISTS
    public ArrayList<Wall> arr_WallList = new ArrayList<>();
    public ArrayList<Goblin> arr_Goblins = new ArrayList<>();
    public ArrayList<JLabel> arr_WinnerNameScore = new ArrayList<>();

    //Game Thread
    private Thread gameThread;
    private boolean gameOn;

    //---GAME PREP CONSTRUCTOR
    public GamePrep() {
        //GAME WINDOW
        super("Dungeon Crawler Game");
        setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);// set size of JFrame
        setResizable(false);// window resize disabled
        content = getContentPane();
        content.setBackground(Color.gray); //JFrame window color
        setLayout(null);//make null layout in JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//handle closing of program window

        //add key listener and focus window to move chars
        content.addKeyListener(this); //add a key listener
        content.setFocusable(true);//add focus


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


        //------BUILD WALLS: Loop Through Walls to Build Level
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

        //BUILD Goblins
        //Instantiate Goblins
        for (int[] goblins : LevelOneData.arr_GoblinPositions) {
            int x = goblins[0];
            int y = goblins[1];
            int direction = goblins[2];

            Goblin goblin = new Goblin(x, y, direction);

            add(goblin.getGoblinLabel());

            arr_Goblins.add(goblin);
        }


        //ANIMATE BUTTON STARTS GAME
        AnimateButton = new JButton("Run");
        AnimateButton.setSize(100, 50);
        AnimateButton.setLocation(GameProperties.SCREEN_WIDTH - 120, GameProperties.SCREEN_HEIGHT - 155);
        add(AnimateButton);
        AnimateButton.addActionListener(this);
        AnimateButton.setFocusable(false);
//        goblinAlpha.setAnimationButton(AnimateButton);

    }

    //---MAIN METHOD to Start Game
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
            heroAlpha.move(GameProperties.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            heroAlpha.move(GameProperties.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            heroAlpha.move(GameProperties.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            heroAlpha.move(GameProperties.RIGHT);
        }

        // SPACE BAR
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //---Fire Hero Arrow Method
            if (gameOn) {
                heroAlpha.fireArrow();
            }
        }

        // Z CHECK SOME DATA
        if (e.getKeyCode() == KeyEvent.VK_Z) {
//            goblinAlpha.setFilename(GameProperties.Goblin_UP_IMG);
            System.out.println(arr_Goblins);
            makeAJPanel();
        }


    }

    public void keyReleased(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        startGame();
//        repaint();
    }
    //---EVENT LISTENERS END


    //METHODS END

    //---THREAD START
    public void startGame() {
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
        gameOn = true;
    }

    public void run() {
        System.out.println("game on");
        //GAME LOOP
        while (gameOn) {
            //TODO MOVE SPRITES
            //Move Goblins
            for (Goblin goblin : arr_Goblins) {
                goblin.move();
            }

            //MOVE ARROWS
            if (heroAlpha.arr_HeroArrowsFlying.size() > 0) {
                for (Arrow arrow : heroAlpha.arr_HeroArrowsFlying) {
                    arrow.move();
                }//end of loop
            }//end of if

            //CHECK COLLISIONS
            checkCollision();


            // UPDATE GUI
            //todo set new label locations
            //hero GUI
            switch (heroAlpha.getDirection()) {
                case GameProperties.UP:
                    heroLabel.setIcon(new ImageIcon(getClass().getResource(GameProperties.HERO_IMG_UP_FILENAME)));
                    break;
                case GameProperties.DOWN:
                    heroLabel.setIcon(new ImageIcon(getClass().getResource(GameProperties.HERO_IMG_DOWN_FILENAME)));
                    break;
                case GameProperties.LEFT:
                    heroLabel.setIcon(new ImageIcon(getClass().getResource(GameProperties.HERO_IMG_LEFT_FILENAME)));
                    break;
                case GameProperties.RIGHT:
                    heroLabel.setIcon(new ImageIcon(getClass().getResource(GameProperties.HERO_IMG_RIGHT_FILENAME)));
                    break;
            }
            heroLabel.setLocation(heroAlpha.getX(), heroAlpha.getY());

            //check if goblins exist
            if (arr_Goblins.size() > 0) {
                for (Goblin goblin : new ArrayList<>(arr_Goblins)) {
                    if (goblin.getVisible() == false) {
                        gameScore += GameProperties.Goblin_KILL_POINT;
                        remove(goblin.getGoblinLabel());
                        arr_Goblins.remove(goblin);
                    } else {
                        switch (goblin.getDirection()) {
                            case GameProperties.UP:
                                goblin.getGoblinLabel().setIcon(new ImageIcon(getClass().getResource(GameProperties.Goblin_UP_IMG)));
                                break;
                            case GameProperties.DOWN:
                                goblin.getGoblinLabel().setIcon(new ImageIcon(getClass().getResource(GameProperties.Goblin_DOWN_IMG)));
                                break;
                            case GameProperties.LEFT:
                                goblin.getGoblinLabel().setIcon(new ImageIcon(getClass().getResource(GameProperties.Goblin_LEFT_IMG)));
                                break;
                            case GameProperties.RIGHT:
                                goblin.getGoblinLabel().setIcon(new ImageIcon(getClass().getResource(GameProperties.Goblin_RIGHT_IMG)));
                                break;
                        }
                        goblin.getGoblinLabel().setLocation(goblin.getX(), goblin.getY());
                    }
                }
            }
//

            if (heroAlpha.arr_HeroArrowsFlying.size() > 0) {
                for (Arrow arrow : new ArrayList<>(heroAlpha.arr_HeroArrowsFlying)) {
                    if (arrow.isVisible()) {
                        arrow.arrowLabel.setLocation(arrow.getX(), arrow.getY());
                        add(arrow.arrowLabel);
                    } else {
//                        heroAlpha.arr_HeroArrowsFlying.remove(arrow.arrowLabel);
                        remove(arrow.arrowLabel);
                        heroAlpha.arr_HeroArrowsFlying.remove(arrow);
                    }
                }// end of loop
            }

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

    private void checkCollision() {
        Rectangle heroBox = heroAlpha.collisionBox();
        ArrayList<Arrow> arrows = heroAlpha.arr_HeroArrowsFlying;

        //if Hero's visible, Check for Collision
        if (heroAlpha.isVisible()) {
            //Check if Hero's off-screen
            offscreenCheck(heroAlpha);
            //if walls exist,check for wall collisions
            if (arr_WallList.size() > 0) {
                for (Wall wall : arr_WallList) {
                    int direction = heroAlpha.getDirection();
                    Rectangle wallBoundary = wall.collisionBox();

                    if (heroAlpha.collisionBox().intersects(wallBoundary)) {
//                        int direction = heroAlpha.getDirection();
                        //TODO this could be extracted to a method: barrierCheck(Sprite);
                        switch (direction) {
                            case GameProperties.UP:
                                System.out.println("up collision");
                                heroAlpha.setY(wallBoundary.y + wallBoundary.height);
                                break;
                            case GameProperties.DOWN:
                                System.out.println("Down collision");
                                heroAlpha.setY(wallBoundary.y - heroAlpha.getHeight());
                                break;
                            case GameProperties.LEFT:
                                System.out.println("LEFT collision");
                                heroAlpha.setX(wallBoundary.x + wallBoundary.width);
                                break;
                            case GameProperties.RIGHT:
                                System.out.println("Right collision");
                                heroAlpha.setX(wallBoundary.x - heroAlpha.width);
                                break;
                        }
                    }
                }
            }// end of wall check

            //TODO if goblins exists check collision

        }

        //if Goblins exist, check for collision
        if (arr_Goblins.size() > 0) {

            for (Goblin goblin : arr_Goblins) {

                //check for offscreen collision
                offscreenCheck(goblin);

                //check for wall collision
                if (arr_WallList.size() > 0) {
                    for (Wall wall : arr_WallList) {
                        checkWallCollision(goblin, wall);
                    }
                }// end of wall check
            }
        }// end of Goblin collision check

        //Check for Arrow Collisions
        //if arrows exist
        if (heroAlpha.arr_HeroArrowsFlying.size() > 0) {
            //check for collision with goblins
            for (Arrow arrow : heroAlpha.arr_HeroArrowsFlying) {
                for (Goblin goblin : arr_Goblins) {
                    if (goblin.collisionBox().intersects(arrow.collisionBox())) {
                        goblin.lightDamage();
                        arrow.setVisible(false);
                    }
                }

                //check for wall collision
                if (arr_WallList.size() > 0) {
                    for (Wall wall : arr_WallList) {
                        checkWallCollision(arrow, wall);
                    }
                }
            }//end of arrow loop
        }// end of arrow check


    }//end of Collision Method

    //check goblin wall collisions
    private void checkWallCollision(Goblin goblin, Wall wall) {
//        int direction = goblin.getDirection();
        Rectangle wallBoundary = wall.collisionBox();

        if (goblin.collisionBox().intersects(wallBoundary)) {
//                        int direction = heroAlpha.getDirection();
            //TODO this could be extracted to a method: barrierCheck(Sprite);
            switch (goblin.getDirection()) {
                case GameProperties.UP:
//                    System.out.println("up collision");
                    goblin.setY(wallBoundary.y + wallBoundary.height);
                    goblin.setDirection(GameProperties.DOWN);
                    break;
                case GameProperties.DOWN:
//                    System.out.println("Down collision");
                    goblin.setY(wallBoundary.y - heroAlpha.getHeight());
                    goblin.setDirection(GameProperties.UP);
                    break;
                case GameProperties.LEFT:
//                    System.out.println("LEFT collision");
                    goblin.setX(wallBoundary.x + wallBoundary.width);
                    goblin.setDirection(GameProperties.RIGHT);
                    break;
                case GameProperties.RIGHT:
//                    System.out.println("Right collision");
                    goblin.setX(wallBoundary.x - goblin.width);
                    goblin.setDirection(GameProperties.LEFT);
                    break;
            }
        }
    }

    //check arrow wall collision
    private void checkWallCollision(Arrow arrow, Wall wall) {
        if (arrow.collisionBox().intersects(wall.collisionBox())) {
            arrow.setVisible(false);
        }
    }

    //checks if hero's off-screen
    private void offscreenCheck(Hero hero) {
//        int direction = hero.getDirection();

        switch (hero.getDirection()) {
            case GameProperties.UP:
                if (hero.getY() < 0) {
                    hero.setY(0);
                }
                break;
            case GameProperties.DOWN:
                int actualHeight = this.getContentPane().getSize().height;
                if (hero.getY() + hero.getHeight() > actualHeight) {
                    hero.setY(this.getContentPane().getSize().height - heroAlpha.height);
                    System.out.println("Down collision");
                }
                break;
            case GameProperties.LEFT:
                if (hero.getX() < GameProperties.SCREEN_X) {
                    hero.setX(GameProperties.SCREEN_X);
                }
                break;
            case GameProperties.RIGHT:
                if (hero.getX() + hero.getWidth() >= GameProperties.SCREEN_WIDTH) {
                    hero.setX(GameProperties.SCREEN_WIDTH - hero.getWidth());
                }
                break;
        }

    }

    //checks if Goblin is off-screen
    private void offscreenCheck(Goblin goblin) {
        int direction = goblin.getDirection();


        switch (direction) {
            case GameProperties.UP:
                if (goblin.getY() < 0) {
                    goblin.setY(0);
                    goblin.setDirection(GameProperties.DOWN);
                }
                break;
            case GameProperties.DOWN:
                int actualHeight = this.getContentPane().getSize().height;
                if (goblin.getY() + goblin.getHeight() > actualHeight) {
                    goblin.setY(this.getContentPane().getSize().height - heroAlpha.height);
                    goblin.setDirection(GameProperties.UP);
//                    System.out.println("Down collision");
                }
                break;
            case GameProperties.LEFT:
                if (goblin.getX() < GameProperties.SCREEN_X) {
                    goblin.setX(GameProperties.SCREEN_X);
                    goblin.setDirection(GameProperties.RIGHT);
                }
                break;
            case GameProperties.RIGHT:
                if (goblin.getX() + goblin.getWidth() > GameProperties.SCREEN_WIDTH) {
                    goblin.setX(GameProperties.SCREEN_WIDTH - goblin.getWidth());
                    goblin.setDirection(GameProperties.LEFT);
                }
                break;
        }

    }


    //Database Stuff
    public void getWinner(String name, int score) {
        System.out.println("Player Name: " + name);
        System.out.println("Score: " + score);
        writeWinnerToDatabase(name, score);
    }

    public void writeWinnerToDatabase(String name, int score) {
        String playerName = name;
        int playerScore = score;

        //Declare connection and sql statement
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Database Driver Loaded");

            String dbURL = "jdbc:sqlite:score.db";
            conn = DriverManager.getConnection(dbURL);

            if (conn != null) {
                System.out.println("Connected to database");
                conn.setAutoCommit(false);

                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());

                stmt = conn.createStatement();

                //var for sql code
                String sql;

                //if table doesnt exist, create scoreborad table
                sql = "CREATE TABLE IF NOT EXISTS SCOREBOARD " +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " NAME TEXT NOT NULL, " +
                        " SCORE INT NOT NULL) ";
                stmt.executeUpdate(sql);
                conn.commit();
                System.out.println("Table Created Successfully");

                sql = "INSERT INTO SCOREBOARD (NAME, SCORE) VALUES " +
                        "('" + playerName + "', '" + playerScore + "')";
                stmt.executeUpdate(sql);
                conn.commit();

                System.out.println("Display after Inserts: ");
                ResultSet rs = stmt.executeQuery("SELECT * FROM SCOREBOARD");
                DisplayRecords(rs);
                rs.close();

                conn.close();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        makeAJPanel();
//        DisplayRecords();

    }

    public void DisplayRecords(ResultSet rs) throws SQLException {

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int score = rs.getInt("score");


            JLabel winner = new JLabel("Name: " + name + " Score: " + score);

            arr_WinnerNameScore.add(winner);

            System.out.println("ID = " + id);
            System.out.println("name = " + name);
            System.out.println("score = " + score);
            System.out.println();
        }
        scoreBoardJPanel();
    }

    //todo Figure out how to add to top of components
    public void makeAJPanel() {
        this.getContentPane().removeAll();

        JPanel newPanel = new JPanel();
        newPanel.setLocation(200, 200);
        newPanel.setBackground(Color.darkGray);
        newPanel.setSize(300, 300);

        JLabel label = new JLabel("Type Name and Press Enter:");
        JTextField userName = new JTextField(20);

        newPanel.add(label);
        newPanel.add(userName);
        add(newPanel);

        userName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("The entered text is: " + userName.getText());
                newPanel.removeAll();
                revalidate();
                repaint();
            }
        });

        revalidate();
        repaint();
    }

    public void scoreBoardJPanel() {
        this.getContentPane().removeAll();

        JPanel newPanel = new JPanel();
        newPanel.setLocation(200, 200);
        newPanel.setBackground(Color.white);
        newPanel.setSize(300, 300);
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));

        for (JLabel label : arr_WinnerNameScore) {
            newPanel.add(label);
        }
//
//        newPanel.add(label);
//        newPanel.add(userName);
        add(newPanel);

//        userName.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent event) {
//                System.out.println("The entered text is: " + userName.getText());
//                newPanel.removeAll();
//                revalidate();
//                repaint();
//            }
//        });

        revalidate();
        repaint();
    }

}// end of class, don't delete------

