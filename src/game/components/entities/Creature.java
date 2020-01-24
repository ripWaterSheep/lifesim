package game.components.entities;

import game.activity.collision.CollisionCheckers;
import game.activity.collision.CollisionLogic;
import game.components.World;
import game.components.entities.player.Player;
import util.Geometry;

import java.awt.*;

import static game.activity.collision.CollisionLogic.creatureCollisionLogic;
import static util.MyMath.*;

public class Creature extends Entity {


    public enum Behaviors {
        RANDOM, // Go in a random direction, change direction when at end of range.
        FOLLOW, // Try to get close to player if player is within range.
        AVOID // Try to get far from player if player is within range.
    }

    protected Behaviors behavior;

    private final double initialHealth; // Keep track of health set when first spawned in order to spawn full-health clones, no matter how much actual health creature still has.

    private final double detectionRange;

    private boolean playerInRange() { return (Math.abs(Geometry.getDistanceBetween(this, Player.getInstance())) <= detectionRange); }

    private boolean touchingPlayer;

    public void setTouchingPlayer(boolean is) { touchingPlayer = is; }

    protected double getAngleToPlayer() { return Geometry.getAngle(x, y, Player.getInstance().getX(), Player.getInstance().getY()); }


    public Creature(String name, double x, double y, double radius, Color color,
                    Behaviors behavior, double speed, double detectionRange,
                    double damage, double health, boolean canDamagePlayer, double killLoot) {
        super(name, x, y, radius, color, speed, health, damage, canDamagePlayer);

        initialHealth = health;
        this.behavior = behavior;
        this.detectionRange = detectionRange;
        this.stats = new Stats(this, health, damage, canDamagePlayer, killLoot);
    }


    public Creature(String name, double x, double y, double radius, Color color, World world,
                    Behaviors behavior, double speed, double range,
                    double damage, double health, boolean canDamagePlayer, double killLoot) {

        this(name, x, y, radius, color, behavior, speed, range, damage, health, canDamagePlayer, killLoot);
        this.world = world;
        world.add(this);
    }


    /** Copy all fields into new Creature (for spawners) and set its location. This is used in class Spawner to clone base instance. */
    public Creature(Creature baseCreature, double x, double y, World world) {
        this(baseCreature.name, x, y, baseCreature.getMidWidth(), baseCreature.color, world,
        baseCreature.behavior, baseCreature.speed, baseCreature.detectionRange,
        baseCreature.stats.getDamage(), baseCreature.initialHealth, baseCreature.stats.canDamagePlayer(), baseCreature.stats.getKillLoot());

        image = baseCreature.getImage();
    }



    private void randomMovement() {
        // Switch direction when reached the end of range.
        if (getRandInRange(1, 100) <= 4) {
            angle = getRandInRange(0, 359);
        }
    }


    protected void movementLogic() {
        switch (behavior) {
            // Make movement in a direction is completely random.
            case RANDOM:
                randomMovement();
                break;

            case FOLLOW:
                // Stay touching outside of player so it doesn't overlap because that's just unnecessary.
                if (playerInRange()) {
                        angle = getAngleToPlayer() + 180 + getRandInRange(-45, 45);
                } else randomMovement();
                break;

            case AVOID:
                if (playerInRange()) {
                    angle = getAngleToPlayer();
                } else randomMovement(); // Move around randomly to prevent getting stuck in corner. }
                break;
        }
        if (behavior != Behaviors.FOLLOW || !touchingPlayer) moveTowardsAngle();
    }


    @Override
    public void update() {
        super.update();
        creatureCollisionLogic(this);
    }



}


