package game.components.structures.subtypes;

import game.components.GameComponent;
import game.components.World;
import game.components.player.Player;
import game.components.structures.Structure;
import util.MyMath;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static util.MyMath.*;

public class MobileEntity extends Structure {

    //TODO: finish movement stuff

    // Not actually used for iterating in the GameSession loop.
    // This is actually just used for other things like collisionLogic() in this class to allow use of uninherited fields and methods
    protected static ArrayList<MobileEntity> instances = new ArrayList<>();


    public enum MovementType {
        LINEAR, // Go forward in a straight line and die at end of range.
        RECIPROCATING, // Go in a straight line, turn around when reached end of range.
        RANDOM, // Go in a random direction, change direction when at end of range.
        FOLLOW, // Try to get close to player if player is within range.
        AVOID // Try to get far from player if player is within range.
    }

    private MovementType movementType;


    private double angle;
    private boolean currentlyReversed = false;


    private int randomX = 0; // Random movement direction and speed needs to be preserved with variable until changed when at end of range
    private int randomY = 0;


    private int currentDistance = 0;

    private int speed; // Pixels to move per frame
    private final int range; // How far to go before turning

    private final int damage;
    private final boolean canDamagePlayer;
    private int cumulativeDamage;


    private enum HealthType { VULNERABLE, INVULNERABLE }

    private HealthType healthType;
    private int health = 1;
    private boolean alive = true;


    // This needs to be an actual variable instead of just a function because it needs memory to know if an item was in it previously
    private ArrayList<GameComponent> touching = new ArrayList<>();

    private boolean isTouchingPlayer() { return touching.contains(Player.getInstance()); }

    private boolean playerIsInRange() { return (Math.abs(getDistanceBetween(this, Player.getInstance())) <= range); }


    private double getAngleToPlayer() { return getAngle(x, y, Player.getInstance().getX(), Player.getInstance().getY()); }



    public MobileEntity(String label, int x, int y, int width, int height, World world, Color color,
                        MovementType movementType, int speed, int range, double angle,
                        int damage, boolean canDamagePlayer) {

        super(label, x, y, width, height, world, color);

        instances.add(this);

        this.movementType = movementType;
        this.speed = speed;
        this.angle = angle;
        this.range = range;
        elliptical = true;

        this.damage = damage;
        this.canDamagePlayer = canDamagePlayer;

        healthType = HealthType.INVULNERABLE;
    }


    public MobileEntity(String label, int x, int y, int width, int height, World world, Color color,
                        MovementType movementType, int speed, int range, double angle,
                        int damage, int health, boolean canDamagePlayer) {

        this(label, x, y, width, height, world, color, movementType, speed, range, angle, damage, canDamagePlayer);

        this.health = health;
        healthType = HealthType.VULNERABLE;

    }



    private void moveTowardsAngle(double angle) {
        x -= speed*Math.cos(Math.toRadians(angle));
        y -= speed*Math.sin(Math.toRadians(angle));
        System.out.println(speed*Math.cos(Math.toRadians(angle)) + "  " + (double)speed*Math.cos(Math.toRadians(angle)));
    }



    private void movementLogic() {
        switch (movementType) {
            case LINEAR:
                // Entity reciprocate along single line in specific angle
                moveTowardsAngle(angle);
                // Flip around when at edge of range
                if (Math.abs(currentDistance) >= range) {
                    visible = false;
                    cumulativeDamage = 0;
                } else currentDistance += speed;
                break;

            case RECIPROCATING:
                // Entity reciprocate along single line in specific angle


                if (!currentlyReversed)
                    moveTowardsAngle(angle);
                else
                    moveTowardsAngle(180-angle);

                // Flip around when at edge of range
                if (Math.abs(currentDistance) < 0 || Math.abs(currentDistance) >= range) {
                    currentlyReversed = !currentlyReversed;
                    cumulativeDamage = 0;
                }

                break;

            case RANDOM:
                // Switch direction when reached theend of range.
                if (currentDistance >= range) {
                    // Make movement in a direction is completely random.
                    randomX = betterRound(Math.random());
                    // Make perpendicular axis movement complimentary so that going diagonal isn't any faster than going horizontally/vertically.
                    randomY = 1 - randomX;
                }
                // Give perpendicular axis movement random signs to allow the entity to move in both directions on axis.
                // Also apply entity's given speed to the movement vars.
                x += applyRandomSign(randomX) * speed;
                y += applyRandomSign(randomY) * speed;
                break;


            case FOLLOW:
                // Stay touching outside of player so it doesn't overlap because that's just unnecessary.
                if (playerIsInRange() && !isTouchingPlayer()) {
                    // Entity reciprocate along single line in specific angle
                    moveTowardsAngle(180+getAngleToPlayer());
                     //System.out.println(speed*Math.sin(Math.toRadians(getAngleToPlayer())));

                }
                break;

            case AVOID:
                //System.out.println(Math.abs(getDistanceBetween(this, Player.getInstance())));
                if (playerIsInRange() && !isTouchingPlayer()) {
                    moveTowardsAngle(getAngleToPlayer());
                }
                break;
        }

    }


    private void collisionLogic() {
        for (MobileEntity entity : instances) {
            if (testIntersection(getShape(),entity.getShape())) {
                if (!touching.contains(entity)) {
                    touching.add(entity);
                    entity.health -= damage;
                    cumulativeDamage += damage; // Damage to other entities occurs once when collision starts
                }
            }
        }

        if (testIntersection(getShape(), Player.getInstance().getShape())) {
            Player player = Player.getInstance();
            if (!touching.contains(player))
                touching.add(player);
            // Damage to player is continuous for duration of collision
            if (canDamagePlayer)
                player.damage(damage);
        }

        touching.removeIf(component -> !testIntersection(getShape(), component.getShape()));
    }


    private void borderLogic() {
        x = MyMath.clamp(x, -world.getMidWidth() + getMidWidth(), world.getMidWidth() - getMidWidth());
        y = MyMath.clamp(y, -world.getMidHeight() + getMidHeight(), world.getMidHeight() - getMidHeight());
    }


    private void statLogic() {

        if (health <= 0)
            alive = false;

        health = Math.max(health, 0);

    }



    @Override
    public void setup(JPanel panel) {

    }



    @Override
    public void act() {
        movementLogic();
        collisionLogic();
        borderLogic();
        statLogic();
    }


    @Override
    public void draw(Graphics g) {
        if (alive) {
            super.draw(g);
        }
    }


}


