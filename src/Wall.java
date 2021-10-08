import java.awt.*;

public class Wall {
    protected int x, y;
    protected int width, height;
    protected String filename;
    protected Rectangle r;
    private int test;


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

    //to work with Hero Alhpa, bring him in
    private Hero heroAlpha; //create class

    public void setHeroAlpha(Hero heroAlpha) {
        this.heroAlpha = heroAlpha;
    }

    public Hero getHeroAlpha() {
        return heroAlpha;
    }


    public Wall() {
        this.x = 0;
        this.y = 0;
        this.width = 40;
        this.height = 40;
        this.filename = "wallBlock1_40x40.png";
        this.r = new Rectangle(this.x, this.y, this.width, this.height);

    }

    public int detectHeroCollision() {
        boolean x;
        int test = 0;

        if (this.r.intersects(heroAlpha.getRectangle()) && heroAlpha.direction == 1) {
            test = 1;
        }
        if (this.r.intersects(heroAlpha.getRectangle()) && heroAlpha.direction == 2) {
            test = 2;
        }

        if (this.r.intersects(heroAlpha.getRectangle()) && heroAlpha.direction == 3) {
            test = 3;
        }

        if (this.r.intersects(heroAlpha.getRectangle()) && heroAlpha.direction == 4) {
            test = 4;
        }

        return test;
    }


}
