package game.components;


public class Position implements IComponent {

    private double x, y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public void translate(double horizontal, double vertical) {
        x += horizontal;
        y += vertical;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public Position copy() {
        return new Position(x, y);
    }
}
