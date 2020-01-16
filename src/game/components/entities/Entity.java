package game.components.entities;

import game.GameSession;
import game.components.GameComponent;
import game.components.World;
import game.components.structures.Structure;
import util.MyMath;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import static util.MyMath.testIntersection;

public abstract class Entity extends GameComponent {

    // Contains all subclass instances as well as Entity instances
    private static ArrayList<Entity> instances = new ArrayList<>();

    public static ArrayList<Entity> getInstances() { return instances; }


    public static void addSpawnedEntities() {
        /* Add entity instance spawned mid-game into the game after the loop is executed.
         * If GameSession.usedComponents held references to the entity list,
         * then adding to the list while iterating through it would cause a ConcurrentModificationException.
         */
        for (Entity entity: instances) {
            if (!GameSession.getUsedComponents().contains(entity))
                GameSession.getUsedComponents().add(entity);
        }
    }


    protected double speed; // Pixels to move per frame
    protected double angle;

    protected boolean alive = true;

    public boolean isAlive() { return alive; }


    protected double health;

    public double getHealth() { return health; }

    public void heal(double amount) { health += amount; }

    public void damage(double amount) { health -= amount; }


    @Override
    public Ellipse2D.Double getShape() { return new Ellipse2D.Double(getDisplayX(), getDisplayY(), width, height); }


    public ArrayList<Structure> getTouchingStructures() {
        ArrayList<Structure> touching = new ArrayList<>();
            for (Structure structure: Structure.getInstances()) {
                if (testIntersection(getShape(), structure.getShape()) && getWorld() == structure.getWorld()) {
                    touching.add(structure);
                }
            }
        return touching;
    }


    public boolean isTouchingAnyStructures() { return !getTouchingStructures().isEmpty(); }


    public Structure getTopStructureTouching() {
        Structure topTouching = null;
        if (isTouchingAnyStructures())
            topTouching = getTouchingStructures().get(getTouchingStructures().size()-1);

        return topTouching;
    }


    public ArrayList<Entity> getTouchingEntities() {
        ArrayList<Entity> touching = new ArrayList<>();
            for (Entity entity: Entity.instances) {
                if (testIntersection(getShape(), entity.getShape()) && world == entity.getWorld() && !(entity == this)) {
                    touching.add(entity);
                }
            }
        return touching;
    }



    protected Entity(String name, double x, double y, double radius, World world, Color color, double speed, double angle, double health) {
        super(name, x, y, radius, radius, world, color);

        instances.add(this);
        this.speed = speed;
        this.angle = angle;
        this.health = health;
    }



    protected void moveTowardsAngle() {
        x -= (speed*Math.cos(Math.toRadians(angle)));
        y -= (speed*Math.sin(Math.toRadians(angle)));
    }


    /** Keep entity within the world barrier.
     * I might make this optional for some worlds in the future.
     */
    protected void borderLogic() {
        //System.out.println(this.name);
        x = MyMath.clamp(x, -world.getMidWidth() + getMidWidth(), world.getMidWidth() - getMidWidth());
        y = MyMath.clamp(y, -world.getMidHeight() + getMidHeight(), world.getMidHeight() - getMidHeight());
    }


    protected void statLogic() {

        if (health <= 0) {
            alive = false;
            visible = false;
        }

        health = Math.max(health, 0);
    }


    protected abstract void movementLogic();

    protected abstract void collisionLogic();


    @Override
    public void setup(JPanel panel) {


    }


    @Override
    public void act()  {
        if (alive) {
            movementLogic();
            collisionLogic();
        }
        borderLogic();
        statLogic();
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }



}
