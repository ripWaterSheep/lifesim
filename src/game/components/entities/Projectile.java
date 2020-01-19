package game.components.entities;

import game.activity.collision.CollisionLogic;
import game.components.GameComponent;
import game.components.World;
import game.components.entities.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Projectile extends Entity {


    private static ArrayList<Projectile> projectileInstances = new ArrayList<>();

    public static ArrayList<Projectile> getProjectileInstances() { return projectileInstances; }


    protected double currentDistance = 0; // How far the Projectile currently has gone
    protected final int range; // How far to move towards specified angle

    public int getRange() { return range; }


    private ArrayList<GameComponent> lastTouching = new ArrayList<>();

    public ArrayList<GameComponent> getLastTouching() { return lastTouching; }


    protected boolean isTouchingPlayer() { return lastTouching.contains(Player.getInstance()); }



    public Projectile(String name, double x, double y, double radius, World world, Color color, double speed, double angle, int range, double damage, double health, boolean canDamagePlayer) {
        super(name, x, y, radius, world, color, speed, health, damage, canDamagePlayer);
        projectileInstances.add(this);

        this.range = range;
        this.angle = angle;

    }


    protected Projectile(String name, double x, double y,  double scale, World world, String imageName, double speed, double angle, int range, double damage, double health, boolean canDamagePlayer) {
        super(name, x, y, scale, world, imageName, speed, health, damage, canDamagePlayer);
        projectileInstances.add(this);

        this.range = range;
        this.angle = angle;
    }



    @Override
    protected void movementLogic() {
        // Entity moves along specific angle
        moveTowardsAngle();
        // Die around when at edge of range
        if (Math.abs(currentDistance) >= range) {
            alive = false;
            visible = false;
        } else currentDistance += speed;
    }


    @Override
    protected void statLogic() {
        super.statLogic();
        if (!alive) visible = false;
    }


    @Override
    protected void collisionLogic() {
        CollisionLogic.projectileCollisionLogic(this);
    }


}
