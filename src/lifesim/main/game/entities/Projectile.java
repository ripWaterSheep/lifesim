package lifesim.main.game.entities;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.DamageStats;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import java.awt.*;

import static java.lang.Math.*;


public class Projectile extends MovementEntity {

    private static final Animation BLANK = new Animation(0, "blank");

    private final double movementRange;
    private double distanceTravelled;

    private final boolean matchSpriteAngle;

    private final Animation damageAnimation;


    public Projectile(String name, Sprite sprite, DamageStats stats, double speed, double movementRange, boolean matchSpriteAngle, Animation damageAnimation) {
        super(name, sprite, stats, speed);
        movement.setMagnDir(speed, 0);
        this.movementRange = movementRange;
        this.matchSpriteAngle = matchSpriteAngle;
        this.damageAnimation = damageAnimation;
    }

    public Projectile(String name, Sprite sprite, DamageStats stats, double speed, double movementRange, boolean matchSpriteAngle) {
        this(name, sprite, stats, speed, movementRange, matchSpriteAngle, BLANK);
    }


    @Override
    public Projectile copyInitialState() {
        return new Projectile(name, sprite, (DamageStats) stats.copyInitialState(), defaultSpeed, movementRange, matchSpriteAngle, new Animation(damageAnimation));
    }

    public DamageStats stats() {
        return (DamageStats) stats;
    }

    public double getMovementRange() {
        return movementRange;
    }

    public void launchTowards(double direction) {
        movement.setDirection(direction);
    }


    @Override
    public void onRemoval(World world) {
        if (damageAnimation != null)
            world.add(new TempEntity("Damage Animation", new AnimatedSprite(damageAnimation)), pos);

        super.onRemoval(world);
    }


    @Override
    public void update(World world, Player player) {
        super.update(world, player);

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
