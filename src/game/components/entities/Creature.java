package game.components.entities;

import game.activity.collision.CollisionCheckers;
import game.activity.collision.CollisionLogic;
import game.components.World;
import game.components.entities.player.Player;
import util.Geometry;

import java.awt.*;
import java.util.ArrayList;

import static util.MyMath.*;

public class Creature extends Projectile {


    public enum Behaviors {
        RANDOM, // Go in a random direction, change direction when at end of range.
        FOLLOW, // Try to get close to player if player is within range.
        AVOID // Try to get far from player if player is within range.
    }

    protected Behaviors behavior;

    private final double initialHealth; // Keep track of health set when first spawned in order to spawn full-health clones, no matter how much actual health creature still has.
    private final double killLoot; // Declare variable for amount of money the player earns from creature's death.


    protected boolean playerInRange() { return (Math.abs(Geometry.getDistanceBetween(this, Player.getInstance())) <= range); }

    protected double getAngleToPlayer() { return Geometry.getAngle(x, y, Player.getInstance().getX(), Player.getInstance().getY()); }


    public Creature(String name, double x, double y, double radius, Color color,
                    Behaviors behavior, double speed, int range,
                    double damage, double health, boolean canDamagePlayer, double killLoot) {

        super(name, x, y, radius, color, speed, 0, range, damage, health, canDamagePlayer);

        initialHealth = health;
        this.behavior = behavior;
        this.killLoot = killLoot;
    }


    public Creature(String name, double x, double y, double radius, Color color, World world,
                    Behaviors behavior, double speed, int range,
                    double damage, double health, boolean canDamagePlayer, double killLoot) {

        this(name, x, y, radius, color, behavior, speed, range, damage, health, canDamagePlayer, killLoot);
        this.world = world;
    }



    /** Copy all fields into new Creature (for spawners) and set its location. This is used in class Spawner to clone base instance. */
    public Creature(Creature baseCreature, double x, double y) {
        this(baseCreature.name, x, y, baseCreature.getMidWidth(), baseCreature.color,
        baseCreature.behavior, baseCreature.speed, baseCreature.range,
        baseCreature.damage, baseCreature.initialHealth, baseCreature.canDamagePlayer, baseCreature.killLoot);

        image = baseCreature.getImage();
    }



    private void randomMovement() {
        // Switch direction when reached the end of range.
        if (currentDistance >= range) {
            angle = getRandInRange(0, 359);
            currentDistance = 0;
        }
        moveTowardsAngle();
        currentDistance += speed;
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
                   if (!getLastTouching().contains(Player.getInstance())) {
                        angle = getAngleToPlayer() + 180 + getRandInRange(-45, 45);
                        moveTowardsAngle();
                   }
                } else randomMovement();
                break;

            case AVOID:
                if (playerInRange()) {
                    angle = getAngleToPlayer();
                    moveTowardsAngle();
                } else randomMovement(); // Move around randomly to prevent getting stuck in corner. }
                break;
        }
    }


    protected void collisionLogic() {
        CollisionLogic.creatureCollisionLogic(this);
    }


    @Override
    protected void statLogic() {
        // If health is less than zero but alive has not been set to zero yet (just a single frame where this occurs), player gains loot.
        if (health <= 0 && alive) Player.getInstance().getStats().gainMoney(killLoot);
        super.statLogic();
    }


    public void draw(Graphics g) {
        if (alive) {
            super.draw(g);
        }
    }



}


