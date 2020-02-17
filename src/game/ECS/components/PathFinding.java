package game.ECS.components;

/** Defines an entity's behavior of automatic pathfinding relative to the player.
 */
public class PathFinding implements IComponent {


    /** Define whether to pursue or evade the location of player instance */
    public enum Behaviors {
        PURSUE,
        EVADE
    }

    private final Behaviors behavior;

    public Behaviors getType() {
        return behavior;
    }

    public PathFinding(Behaviors behavior) {
        this.behavior = behavior;
    }


    @Override
    public PathFinding copy() {
        return new PathFinding(behavior);
    }

}
