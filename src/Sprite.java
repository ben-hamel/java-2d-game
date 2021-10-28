import java.awt.Rectangle;

/*
 * Basic character class => width, height, x,y, visibility
 */
public class Sprite {
    //define any data members
    protected int x, y; //upper left coordinate of the object
    protected int width, height; //size of object
    protected String filename;
    protected int direction;
    protected boolean visible;



    //getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFilename() {
        return filename;
    }

    public boolean isVisible() {
        return visible;
    }

    public Sprite setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    //setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getDirection() {
        return direction;
    }

    public Sprite setDirection(int direction) {
        this.direction = direction;
        return this;
    }

    public Sprite() {
        super();
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.filename = "";
    }

    public Sprite(int x, int y, int width, int height, String filename) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filename = filename;
//        this.r = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public Sprite(int width, int height, String filename) {
        super();
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.filename = filename;
    }

    public Sprite(int x, int y, int width, int height, boolean b) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = b;
    }


}
