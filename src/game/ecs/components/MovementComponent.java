package game.ecs.components;


import static util.Geometry.angleWrap;


/** Define characteristics of an entity relating to its motion
 */
public class MovementComponent implements CopyableComponent {

    private final double initialSpeed;
    private double currentSpeed;

    public void setSpeedRatio(double ratio) {
        currentSpeed *= ratio;
    }

    public void resetSpeed() {
        currentSpeed = initialSpeed;
    }


    private double angle;
    private final double initialAngle;

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angleWrap(angle);
    }


    private double movementX;

    public double getMovementX() {
        return movementX;
    }

    private double movementY;

    public double getMovementY() {
        return movementY;
    }


    private boolean moving = true;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public MovementComponent(double speed, double angle) {
        currentSpeed = speed;
        initialSpeed = speed;

        this.angle = angle;
        initialAngle = angle;
    }

    public MovementComponent(double speed) {
        this(speed, 0);
    }


    public void setMovementTowardsAngle() {
        movementX = -(currentSpeed * Math.cos(Math.toRadians(angle)));
        movementY = -(currentSpeed * Math.sin(Math.toRadians(angle)));
        moving = true;
        resetSpeed();
    }

    @Override
    public MovementComponent copyInitialState() {
        return new MovementComponent(initialSpeed, initialAngle);
    }

    @Override
    public CopyableComponent copyCurrentState() {
        return new MovementComponent(currentSpeed, angle);
    }
}
