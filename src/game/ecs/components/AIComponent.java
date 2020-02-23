package game.ecs.components;

/** Defines an entity's behavior of automatic pathfinding relative to the player.
 */
public class AIComponent implements CopyableComponent {

    /** Define whether to pursue or evade the location of player instance */
    public enum PathFinding {
        PURSUE,
        EVADE
    }

    private final PathFinding pathFinding;

    public PathFinding getPathFinding() {
        return pathFinding;
    }


    private final double detectionRange;

    public double getDetectionRange() {
        return detectionRange;
    }


    private final boolean doRandomMovement;

    public boolean doesRandomMovement() {
        return doRandomMovement;
    }



    public AIComponent(PathFinding pathFinding, double detectionRange, boolean doRandomMovement) {
        this.pathFinding = pathFinding;
        this.detectionRange = detectionRange;
        this.doRandomMovement = doRandomMovement;
    }


    @Override
    public AIComponent copyInitialState() {
        return new AIComponent(pathFinding, detectionRange, doRandomMovement);
    }

    @Override
    public AIComponent copyCurrentState() {
        return copyInitialState();
    }

}
