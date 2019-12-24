package Geometry;

public class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        x = 0;
        y = 0;
    }

    public void offsetPoint(int dx, int dy) {
         x += dx;
         y += dy;
    }


    public void setPoint(int deltaX, int deltaY) {
        x = deltaX;
        y = deltaY;
    }

}
