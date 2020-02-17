package game.ECS.components;


/** Defines an entity's location in the player-centric coordinate system (not the actual screen coordinate system,;
 * that is done in Spatial).
 */
public class Position implements IComponent {

    /** These are the x and y of the center of the entity's shape. */
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
