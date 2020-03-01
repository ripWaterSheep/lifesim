package game.ecs.components;


/** Defines an entity's location in the player-centric coordinate system (not the actual screen coordinate system,;
 * that is done in SpatialComponent).
 */
public class PositionComponent implements CopyableComponent {

    /** These are the x and y of the center of the entity's shape. */
    private double x, y;
    final double initialX, initialY;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public PositionComponent(double x, double y) {
        this.x = x;
        this.y = y;
        initialX = x;
        initialY = y;
    }


    public void translate(MovementComponent movement) {
        x += movement.getMovementX();
        y += movement.getMovementY();
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(PositionComponent pos) {
        x = pos.x;
        y = pos.y;
    }


    @Override
    public PositionComponent copyInitialState() {
        return new PositionComponent(initialX, initialY);
    }

    public PositionComponent copyCurrentState() {
        return new PositionComponent(x, y);
    }

}
