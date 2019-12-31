package Drawing;

import java.awt.*;


public class CenteredRect {


    private int x;

    public int getX() { return x; }


    private int y;

    public int getY() { return y; }


    private int width;

    public int getWidth() { return width; }


    private int height;

    public int getHeight() { return height; }


    public int getMidWidth() { return width/2; }

    public int getMidHeight() { return height/2; }


    public Rectangle getShape() {
        return new Rectangle(x+getMidWidth(), y+getMidHeight(), width, height);
    }



    public CenteredRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }


    public void draw(Graphics g) {

    }


}
