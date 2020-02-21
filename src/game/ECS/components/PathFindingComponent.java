package game.ECS.components;

/** Defines an entity's behavior of automatic pathfinding relative to the player.
 */
public class PathFindingComponent implements Copyable {

    /** Define whether to pursue or evade the location of player instance */
    public enum Behaviors {
        PURSUE,
        EVADE
    }

    private final Behaviors behavior;


    private final double followDistance;

    public double getFollowDistance() {
        return followDistance;
    }


    public Behaviors getType() {
        return behavior;
    }

    public PathFindingComponent(Behaviors behavior, double followDistance) {
        this.behavior = behavior;
        this.followDistance = followDistance;
    }


    @Override
    public PathFindingComponent copyInitialState() {
        return new PathFindingComponent(behavior, followDistance);
    }

    @Override
    public PathFindingComponent copyCurrentState() {
        return copyInitialState();
    }

}
