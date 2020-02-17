package game.ECS.components;


import static util.Geometry.angleWrap;


/** Define characteristics of an entity relating to its motion
 */
public class Movement implements IComponent {

    private final double initialSpeed;
    private double currentSpeed;

    public void setSpeedRatio(double ratio) {
        currentSpeed *= ratio;
    }

    public void resetSpeed() {
        currentSpeed = initialSpeed;
    }


    private double angle;

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


    public Movement(double speed, double angle) {
        initialSpeed = speed;
        currentSpeed = initialSpeed;
        this.angle = angle;
    }

    public Movement(double speed) {
        this(speed, 0);
    }


    public void setMovementTowardsAngle() {
        movementX = -(currentSpeed * Math.cos(Math.toRadians(angle)));
        movementY = -(currentSpeed * Math.sin(Math.toRadians(angle)));
    }

    @Override
    public Movement copy() {
        return new Movement(initialSpeed);
    }
}
