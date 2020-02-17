package game.ECS.components;

/** Defines an entity's behavior of automatic pathfinding relative to the player.
 */
public class PathFindingComponent implements IComponent {


    /** Define whether to pursue or evade the location of player instance */
    public enum Behaviors {
        PURSUE,
        EVADE
    }

    private final Behaviors behavior;

    public Behaviors getType() {
        return behavior;
    }

    public PathFindingComponent(Behaviors behavior) {
        this.behavior = behavior;
    }


    @Override
    public PathFindingComponent copy() {
        return new PathFindingComponent(behavior);
    }

}
