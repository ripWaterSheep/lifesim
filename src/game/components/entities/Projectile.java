package game.components.entities;

import game.components.GameComponent;
import game.components.World;
import util.FindComponent;

import java.awt.*;
import java.util.ArrayList;

import static game.activity.collision.CollisionLogic.projectileCollisionLogic;

public class Projectile extends Entity {

    protected double currentDistance = 0; // How far the Projectile currently has gone
    protected final double range; // How far to move towards specified angle

    public ArrayList<GameComponent> lastTouching = new ArrayList<>();

    public ArrayList<GameComponent> getLastTouching() { return lastTouching; }


    public Projectile(String name, double x, double y, double radius, Color color, World world, double speed, double angle, double range, double damage, double health, boolean canDamagePlayer) {
        super(name, x, y, radius, color, speed, health, damage, canDamagePlayer);

        this.world = world;
        world.add(this);

        this.range = range;
        this.angle = angle;
        stats = new BasicStats(this, speed, health, damage, canDamagePlayer);
    }



    @Override
    protected void movementLogic() {
        // Entity moves along specific angle
        moveTowardsAngle();
        // Die when at edge of range
        if (Math.abs(currentDistance) >= range) {
            visible = false;
        } else currentDistance += stats.speed;
    }



    @Override
    public void update() {
        super.update();
        projectileCollisionLogic(this);
    }





}
