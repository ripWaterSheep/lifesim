package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.DamageStats;
import lifesim.main.game.handlers.World;

import java.awt.*;

import static java.lang.Math.*;


public class Projectile extends MovementEntity {

    private final double movementRange;
    private double distanceTravelled;

    private final boolean matchSpriteAngle;


    public Projectile(String name, Sprite sprite, DamageStats stats, double speed, double angle, double movementRange, boolean matchSpriteAngle) {
        super(name, sprite, stats, speed);
        movement.setMagnDir(speed, angle);
        this.movementRange = movementRange;
        this.matchSpriteAngle = matchSpriteAngle;
    }


    public double getMovementRange() {
        return movementRange;
    }



    public void launchTowards(double direction) {
        movement.setDirection(direction);
    }



    @Override
    public void update(World world) {
        super.update(world);

        distanceTravelled += abs(movement.x);
        distanceTravelled += abs(movement.y);

        if (distanceTravelled > movementRange) {
            removeFromWorld();
       }
    }


    @Override
    public void render(Graphics2D g2d) {
        if (matchSpriteAngle)
            g2d.rotate(toRadians(movement.getDirection()), getDisplayPos().x, getDisplayPos().y);
        super.render(g2d);
    }
}