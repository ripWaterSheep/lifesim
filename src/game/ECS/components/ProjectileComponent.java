package game.ECS.components;

public class ProjectileComponent implements Copyable {

    private final double movementRange;

    public double getMovementRange() {
        return movementRange;
    }


    public ProjectileComponent(double movementRange) {
        this.movementRange = movementRange;
    }


    @Override
    public ProjectileComponent copyInitialState() {
        return new ProjectileComponent(movementRange);
    }

    @Override
    public ProjectileComponent copyCurrentState() {
        return copyInitialState();
    }
}
