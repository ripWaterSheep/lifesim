package game.ecs.components;

public class ProjectileComponent implements CopyableComponent {

    private final double movementRange;

    public double getMovementRange() {
        return movementRange;
    }


    private final boolean destroyOnImpact;

    public boolean shouldDestroyOnImpact() {
        return destroyOnImpact;
    }



    public ProjectileComponent(double movementRange, boolean destroyOnImpact) {
        this.movementRange = movementRange;
        this.destroyOnImpact = destroyOnImpact;
    }


    @Override
    public ProjectileComponent copyInitialState() {
        return new ProjectileComponent(movementRange, destroyOnImpact);
    }

    @Override
    public ProjectileComponent copyCurrentState() {
        return copyInitialState();
    }
}
