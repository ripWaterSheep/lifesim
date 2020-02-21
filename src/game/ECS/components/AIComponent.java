package game.ECS.components;

/** Defines an entity's behavior of automatic pathfinding relative to the player.
 */
public class AIComponent implements Copyable {

    /** Define whether to pursue or evade the location of player instance */
    public enum Behaviors {
        PURSUE,
        EVADE
    }

    private final Behaviors pathFinding;


    private final double followDistance;

    public double getFollowDistance() {
        return followDistance;
    }


    public Behaviors getType() {
        return pathFinding;
    }

    public AIComponent(Behaviors pathFinding, double followDistance) {
        this.pathFinding = pathFinding;
        this.followDistance = followDistance;
    }


    @Override
    public AIComponent copyInitialState() {
        return new AIComponent(pathFinding, followDistance);
    }

    @Override
    public AIComponent copyCurrentState() {
        return copyInitialState();
    }

}
