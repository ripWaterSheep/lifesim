package com.lifesim.main.game.ecs.components;

/** Defines an entity's behavior of automatic pathfinding relative to the player.
 */
public class AIComponent implements CopyableComponent {

    /** Define whether to pursue or evade the location of player instance */
    public enum PathFinding {
        NONE,
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


    private final double randomness;

    public double getRandomness() {
        return randomness;
    }



    public AIComponent(PathFinding pathFinding, double detectionRange, double randomness) {
        this.pathFinding = pathFinding;
        this.detectionRange = detectionRange;
        this.randomness = randomness;
    }


    @Override
    public AIComponent copyInitialState() {
        return new AIComponent(pathFinding, detectionRange, randomness);
    }

}
