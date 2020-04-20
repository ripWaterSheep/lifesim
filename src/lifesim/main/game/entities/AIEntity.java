package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.MyMath.getRand;


public class AIEntity extends Entity {

    private final Alliance AITargetAlliance;
    protected final double detectionRange;

    protected Entity targetEntity;

    protected boolean pursuing = false;
    protected boolean attacking = false;


    public AIEntity(String name, Sprite sprite, Stats stats, Alliance AITargetAlliance, double detectionRange) {
        super(name, sprite, stats);
        this.AITargetAlliance = AITargetAlliance;
        this.detectionRange = detectionRange;
    }


    public AIEntity(String name, Sprite sprite, Stats stats, double detectionRange) {
        this(name, sprite, stats, stats.getAlliance(), detectionRange);
    }


    private boolean willTarget(Stats stats) {
        return AITargetAlliance.opposes(stats.getAlliance()) && stats.hasHealth();
    }


    private void doRandomMovement() {
        if (!attacking && !pursuing) {
            if (getRand(0, 1) < 0.03)
                velocity.setMagDir(stats.getCurrentSpeed()/3, getRand(0, 360));
        }
    }

    protected void evaluateAIState() {
        attacking = isTouching(targetEntity);
        pursuing = !attacking;
    }

    protected void doAI(World world) {
        if (targetEntity != this) {
            if (pursuing) {
                // Approach entity's position with a small amount of randomness, since it would otherwise overlap other entities chasing it.
                velocity.setMagDir(stats.getCurrentSpeed(),
                        targetEntity.pos.getAngleFrom(pos) + getRand(-25, 25));
            } else if (attacking) stop();
        } else doRandomMovement();
    }


    public void update(World world) {
        super.update(world);
        targetEntity = world.getClosestEntityInRange(this, detectionRange,
                entity -> willTarget(entity.getStats()));

        if (targetEntity != this) {
            evaluateAIState();
            doAI(world);
        } else doRandomMovement();
    }

}
