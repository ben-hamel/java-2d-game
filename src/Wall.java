import java.awt.*;
import java.util.ArrayList;

public class Wall {
    //--- Vars
    protected int x, y;
    protected int width, height;
    protected String filename;
    protected Rectangle r;
    private int test;
    ArrayList<Wall> arr_ListFromGamePrep;

    //to work with Hero Alpha, bring him in
    private Hero heroAlpha; //create class

    //--- Setters and Getters
    public Wall setX(int x) {
        this.x = x;
        this.r.x = x;
        return this;
    }

    public Wall setY(int y) {
        this.y = y;
        this.r.y = y;
        return this;
    }

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

    public Wall setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public Rectangle getR() {
        return r;
    }

    public Wall setR(Rectangle r) {
        this.r = r;
        return this;
    }

    public void setHeroAlpha(Hero heroAlpha) {
        this.heroAlpha = heroAlpha;
    }

    public Hero getHeroAlpha() {
        return heroAlpha;
    }

    // --- WALL CONSTRUCTOR ---
    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 40;
        this.filename = "wallBlock1_40x40.png";
        this.r = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public Rectangle collisionBox(){
        return new Rectangle(x,y,width,height);
    }
}
