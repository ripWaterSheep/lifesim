package game.ecs.components;

public class ProjectileComponent implements CopyableComponent {

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

}
