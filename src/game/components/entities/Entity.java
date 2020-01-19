package game.components.entities;

import game.components.GameComponent;
import game.components.World;
import game.components.entities.player.Player;
import util.MyMath;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public abstract class Entity extends GameComponent {

    // Contains all subclass instances as well as Entity instances
    private static ArrayList<Entity> entityInstances = new ArrayList<>();

    public static ArrayList<Entity> getEntityInstances() { return entityInstances; }


    protected double speed; // Pixels to move per frame
    protected double angle = 0;

    protected boolean alive = true;

    public boolean isAlive() { return alive; }


    protected double health;

    public double getHealth() { return health; }

    public void heal(double amount) { health += amount; }

    public void dealDamage(double amount) { health -= amount; }

    protected final double damage;

    public double getDamage() { return damage; }


    protected final boolean canDamagePlayer;

    public boolean getCanDamagePlayer() { return canDamagePlayer; }



    @Override
    public Ellipse2D.Double getShape() { return new Ellipse2D.Double(getDisplayX(), getDisplayY(), width, height); }




    protected Entity(String name, double x, double y, double radius, World world, Color color, double speed, double health, double damage, boolean canDamagePlayer) {
        super(name, x, y, radius*2, radius*2, world, color);
        entityInstances.add(this);

        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.canDamagePlayer = canDamagePlayer;
    }


    protected Entity(String name, double x, double y, double scale, World world, String imageName, double speed, double health, double damage, boolean canDamagePlayer) {
        super(name, x, y, scale, world, imageName);
        entityInstances.add(this);

        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.canDamagePlayer = canDamagePlayer;
    }



    protected void moveTowardsAngle() {
        x -= (speed*Math.cos(Math.toRadians(angle)));
        y -= (speed*Math.sin(Math.toRadians(angle)));
    }


    /** Keep entity within the world barrier. */
    protected void borderLogic() {
        x = MyMath.clamp(x, -world.getMidWidth() + getMidWidth(), world.getMidWidth() - getMidWidth());
        y = MyMath.clamp(y, -world.getMidHeight() + getMidHeight(), world.getMidHeight() - getMidHeight());
    }


    protected void statLogic() {
        if (health <= 0) alive = false;

        health = Math.max(health, 0);
    }


    protected abstract void movementLogic();

    protected void collisionLogic() {}


    @Override
    public void init(JPanel panel) {


    }


    @Override
    public void update()  {
        if (alive) {
            if (Player.getInstance().getWorld() == world) movementLogic();
            collisionLogic();
        }
        borderLogic();
        statLogic();
    }



}
