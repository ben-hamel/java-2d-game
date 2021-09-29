import java.awt.*;

public class Sprite {
    protected int x, y; //upper left coordinate of the object
    protected int width, height; //size of object
    protected String filename;
    protected Rectangle r;

    public int getX() {
        return x;
    }

    public Sprite setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Sprite setY(int y) {
        this.y = y;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public Sprite setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public Sprite setHeight(int height) {
        this.height = height;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public Sprite setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public Rectangle getR() {
        return r;
    }

    public Sprite setR(Rectangle r) {
        this.r = r;
        return this;
    }

    public Sprite() {
        super();
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.filename = "";
        this.r = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public Sprite(int x, int y, int width, int height, String filename) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.filename = filename;
        this.r = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public Sprite(int width, int height, String filename) {
        super();
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.filename = filename;
        this.r = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public void Display() {
        System.out.println("X,Y: " + this.x + "," + this.y);

    }

}
