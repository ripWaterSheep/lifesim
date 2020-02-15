package game.components;


import static util.Geometry.angleWrap;

public class Movement implements IComponent {

    private final double initialSpeed;
    private double currentSpeed;

    public void setSpeedRatio(double speedMultiplier) {
        currentSpeed = initialSpeed *speedMultiplier;
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


    public Movement(double initialSpeed, double angle) {
        this.initialSpeed = initialSpeed;
        currentSpeed = initialSpeed;
    }

    public Movement(double initialSpeed) {
        this(initialSpeed, 0);
    }


    public void moveTowardsAngle() {
        movementX = -(currentSpeed * Math.cos(Math.toRadians(angle)));
        movementY = -(currentSpeed * Math.sin(Math.toRadians(angle)));
    }


}
