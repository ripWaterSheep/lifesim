package game.components.entities;

import game.components.Component;
import game.organization.World;
import game.components.entities.stats.DamageStats;
import game.components.entities.stats.ProjectileStats;

import java.awt.*;
import java.util.ArrayList;


public class Projectile extends Entity {

    protected double currentDistance = 0; // How far the Projectile currently has gone

    public ArrayList<Component> lastTouching = new ArrayList<>();

    public ArrayList<Component> getLastTouching() {
        return lastTouching;
    }


    protected DamageStats stats;

    @Override
    public DamageStats getStats() {
        return stats;
    }


    protected final double range;


    public Projectile(String name, double x, double y, double width, double height, boolean elliptical, Color color, World world, double speed, double angle, double range, double damage, boolean canDamagePlayer) {
        super(name, x, y, width, height, elliptical, color);
        this.world = world;
        this.range = range;
        stats = new ProjectileStats(this, speed, angle, damage, canDamagePlayer);

        init(world);
    }

    public Projectile(String name, double x, double y, double scale, String imageName,  World world, double speed, double angle, double range, double damage, boolean canDamagePlayer) {
        this(name, x, y, 0, 0, false, null, world, speed, angle, range, damage, canDamagePlayer);

        setImage(imageName, scale);
    }


    @Override
    protected void movementLogic() {
        moveTowardsAngle();
        // Die when at edge of range
        if (Math.abs(currentDistance) >= range) {
            delete();
        } else currentDistance += stats.getSpeed();
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
