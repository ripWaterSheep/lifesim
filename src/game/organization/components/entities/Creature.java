package game.organization.components.entities;

import game.organization.World;
import game.organization.components.entities.stats.CreatureStats;
import util.Geometry;

import java.awt.*;

import static util.MyMath.getRandInRange;

public class Creature extends Entity {


    public enum Behaviors {
        RANDOM, // Go in a random direction, change direction when at end of range.
        FOLLOW, // Try to get close to player if player is within range.
        AVOID // Try to get far from player if player is within range.
    }


    protected Behaviors behavior;

    private final double initialHealth; // Keep track of health set when first spawned in order to spawn full-health clones, no matter how much actual health creature still has.

    private final double detectionRange;

    private boolean playerInRange() {
        return (Math.abs(Geometry.getDistanceBetween(this, Player.getInstance())) <= detectionRange);
    }

    private boolean touchingPlayer;

    public void setTouchingPlayer(boolean is) {
        touchingPlayer = is;
    }

    protected double getAngleToPlayer() {
        return Geometry.getAngle(x, y, Player.getInstance().getX(), Player.getInstance().getY());
    }


    protected CreatureStats stats;

    public CreatureStats getStats() {
        return stats;
    }


    public Creature(String name, double x, double y, double width, double height, boolean elliptical, Color color,
                    Behaviors behavior, double speed, double detectionRange,
                    double health, double damage, boolean canDamagePlayer, double killLoot) {
        super(name, x, y, width, height, elliptical, color);

        initialHealth = health;
        this.behavior = behavior;
        this.detectionRange = detectionRange;
        this.stats = new CreatureStats(this, speed, health, damage, canDamagePlayer, killLoot);
    }


    public Creature(String name, double x, double y, double width, double height, boolean elliptical, Color color, World world,
                    Behaviors behavior, double speed, double range,
                    double health, double damage, boolean canDamagePlayer, double killLoot) {
        this(name, x, y, width, height, elliptical, color, behavior, speed, range, damage, health, canDamagePlayer, killLoot);
    }


    /** Copy all fields into new c (for spawners) and set its location. This is used in class Spawner to clone base instance. */
    public Creature(Creature c, double x, double y, World world) {
        this("Clone of " + c.getName(), x, y, c.width, c.height, c.isElliptical(), c.color,
            c.behavior, c.stats.getSpeed(), c.detectionRange, c.initialHealth, c.stats.getDamage(), c.stats.canDamagePlayer(), c.stats.getKillLoot());

        image = c.getImage();
        init(world);
    }



    private void randomMovement() {
        // Switch direction when reached the end of range.
        if (getRandInRange(1, 100) <= 4) {
            stats.setAngle(getRandInRange(0, 359));
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
                        stats.setAngle(getAngleToPlayer() + 180 + getRandInRange(-45, 45));
                } else randomMovement();
                break;

            case AVOID:
                if (playerInRange()) {
                    stats.setAngle(getAngleToPlayer());
                } else randomMovement(); // Move around randomly to prevent getting stuck in corner. }
                break;
        }
        if (behavior != Behaviors.FOLLOW || !touchingPlayer) moveTowardsAngle();
    }


    @Override
    public void update() {
        super.update();
    }



}


