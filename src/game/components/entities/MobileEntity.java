package game.components.entities;

import game.components.GameComponent;
import game.components.World;
import game.components.entities.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static util.MyMath.*;

public class MobileEntity extends Entity {

    //TODO: Split between mob and projectile, probably.

    // Not actually used for iterating in the GameSession loop.
    // This is actually just used for other things like collisionLogic() in this class to allow use of uninherited fields and methods
    protected static ArrayList<MobileEntity> instances = new ArrayList<>();

    public static ArrayList<MobileEntity> getSubtypeInstances() { return instances; }


    public enum MovementType {
        LINEAR, // Go forward in a straight line and die at end of range.
        RECIPROCATING, // Go in a straight line, turn around when reached end of range.
        RANDOM, // Go in a random direction, change direction when at end of range.
        FOLLOW, // Try to get close to player if player is within range.
        AVOID // Try to get far from player if player is within range.
    }

    protected MovementType movementType;

    protected boolean currentlyReversed = false;

    protected double currentDistance = 0;
    protected final double range; // How far to go before turning

    protected final double damage;
    protected final boolean canDamagePlayer;
    protected final boolean continuousDamage;



    private ArrayList<GameComponent> lastTouching = new ArrayList<>();


    protected boolean isTouchingPlayer() { return lastTouching.contains(Player.getInstance()); }

    protected boolean playerInRange() { return (Math.abs(getDistanceBetween(this, Player.getInstance())) <= range); }

    protected double getAngleToPlayer() { return getAngle(x, y, Player.getInstance().getX(), Player.getInstance().getY()); }



    public MobileEntity(String name, double x, double y, double width, double height, World world, Color color,
                        MovementType movementType, double speed, double range, double angle,
                        double damage, boolean canDamagePlayer, boolean continuousDamage) {

        super(name, x, y, width, height, world, color, speed, angle);

        instances.add(this);

        this.movementType = movementType;
        this.range = range;

        this.damage = damage;
        this.canDamagePlayer = canDamagePlayer;
        this.continuousDamage = continuousDamage;

        vulnerability = Entity.Vulnerability.INVULNERABLE;
    }


    public MobileEntity(String name, double x, double y, double width, double height, World world, Color color,
                        MovementType movementType, double speed, double range, double angle,
                        double damage, double health, boolean canDamagePlayer, boolean continuousDamage) {

        this(name, x, y, width, height, world, color, movementType, speed, range, angle, damage, canDamagePlayer, continuousDamage);

        this.health = health;
        vulnerability = Vulnerability.VULNERABLE;

    }



    /** Copy all fields into new MobileEntity (for spawners) */
    public void cloneAt(double x, double y, World world) {
        new MobileEntity(name, x, y, width, height, world, color, movementType, speed, range, startAngle, damage, health, canDamagePlayer, continuousDamage);
    }


    protected void movementLogic() {
        switch (movementType) {
            case LINEAR:
                // Entity reciprocate along single line in specific angle
                moveTowardsAngle();
                // Die around when at edge of range
                if (Math.abs(currentDistance) >= range) {
                    alive = false;
                } else currentDistance += speed;
                break;

            case RECIPROCATING:
                // Entity reciprocate along single line in specific angle
                moveTowardsAngle();

                // Flip direction around when at edge of range
                if (Math.abs(currentDistance) < 0 || Math.abs(currentDistance) >= range) {
                    currentlyReversed = !currentlyReversed;
                    currentAngle = angleWrap(180-currentAngle);
                }

                // Keep track of distance gone in direction, depending on which way it is temporarily flipped.
                if (currentlyReversed) {
                    currentDistance -= speed;
                } else currentDistance += speed;

                break;

            case RANDOM:
                // Switch direction when reached the end of range.
                if (currentDistance >= range) {
                    currentAngle = getRandInRange(0, 359);
                }
                // Make movement in a direction is completely random.
                moveTowardsAngle();

                break;


            case FOLLOW:
                // Stay touching outside of player so it doesn't overlap because that's just unnecessary.
                if (playerInRange() && !isTouchingPlayer()) {
                    currentAngle = getAngleToPlayer()+180 + getRandInRange(-30, 30);
                    moveTowardsAngle();
                    //System.out.println(speed*Math.sin(Math.toRadians(getAngleToPlayer())));

                }
                break;

            case AVOID:
                //System.out.println(Math.abs(getDistanceBetween(this, Player.getInstance())));
                if (playerInRange() && !isTouchingPlayer()) {
                    currentAngle = getAngleToPlayer();
                    moveTowardsAngle();
                }
                break;
        }
    }



    protected void collisionLogic() {
        super.collisionLogic();
        for (Entity entity: getTouchingEntities()) {
            // If damage is not continuous, only do damage on first collision with entity
            if ((continuousDamage || !lastTouching.contains(entity)) && entity != this) {
                if ((canDamagePlayer && entity == Player.getInstance()) || (!canDamagePlayer && entity instanceof MobileEntity)) {
                    entity.damage(damage);
                }
                // If entities are touching, spread them out to prevent overlap between entities.
                //if (entity != Player.getInstance())
                  //  moveTowardsAngle(-angleWrap(entity.currentAngle-90), speed*2);
            }
        }

        lastTouching.clear();
        lastTouching.addAll(getTouchingEntities());
    }



    protected void statLogic() {

        if (health <= 0 && vulnerability == Vulnerability.VULNERABLE)
            alive = false;

        health = Math.max(health, 0);

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


