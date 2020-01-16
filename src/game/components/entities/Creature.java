package game.components.entities;

import game.components.World;
import game.components.entities.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static util.MyMath.*;

public class Creature extends Projectile {

    // Not actually used for iterating in the GameSession loop.
    // This is actually just used for other things like collisionLogic() in this class to allow use of uninherited fields and methods
    private static ArrayList<Creature> creatureInstances = new ArrayList<>();

    public static ArrayList<Creature> getCreatureInstances() { return creatureInstances; }

    public enum BehaviorType {
        RANDOM, // Go in a random direction, change direction when at end of range.
        FOLLOW, // Try to get close to player if player is within range.
        AVOID // Try to get far from player if player is within range.
    }

    protected BehaviorType behavior;


    protected boolean playerInRange() { return (Math.abs(getDistanceBetween(this, Player.getInstance())) <= range); }

    protected double getAngleToPlayer() { return getAngle(x, y, Player.getInstance().getX(), Player.getInstance().getY()); }


    /** Copy all fields into new Creature (for spawners) and set its location. */
    public Creature getNewCloneAt(double x, double y, World world) {
        return new Creature(name, x, y, getMidWidth(), world, color, behavior, speed, range, damage, health, canDamagePlayer);
    }


    public Creature(String name, double x, double y, double radius, World world, Color color,
                    BehaviorType behavior, double speed, double range,
                    double damage, double health, boolean canDamagePlayer) {

        super(name, x, y, radius, world, color, speed, 0, range, damage, health, canDamagePlayer);
        creatureInstances.add(this);

        this.behavior = behavior;
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
                   if (!isTouchingPlayer()){
                        angle = getAngleToPlayer() + 180 + getRandInRange(-45, 45);
                        moveTowardsAngle();
                    }
                } else {
                    randomMovement();
                }
                break;

            case AVOID:
                if (playerInRange()) {
                    angle = getAngleToPlayer();
                    moveTowardsAngle();
                } else {
                    randomMovement(); // Move around randomly to prevent getting stuck in corner.
                }
                break;
        }
    }



    protected void collisionLogic() {
        // Allow doing damage to other entities continuously for the whole duration of intersection.
        for (Entity entity: getTouchingEntities()) {
            // Do damage to colliding entities. If canDamagePlayer == true, can only damage player. Else, it can only damage other Creatures
            if ((canDamagePlayer && entity == Player.getInstance()) || (!canDamagePlayer && entity instanceof Creature)) {
                entity.damage(damage);
            }
        }
    }




    @Override
    public void setup(JPanel panel) {

    }



    @Override
    public void act() {
        super.act();
    }


    public void draw(Graphics g) {
        if (alive) super.draw(g);
    }



}


