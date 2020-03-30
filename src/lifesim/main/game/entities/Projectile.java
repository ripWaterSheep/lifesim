package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import java.awt.*;

import static java.lang.Math.*;


public class Projectile extends Entity {

    private static final Animation BLANK = new Animation(0, "blank");

    private final double movementRange;
    private double distanceTravelled;

    private final boolean matchSpriteAngle;

    private final Animation damageAnimation;


    public Projectile(String name, Sprite sprite, Stats stats, double movementRange, boolean matchSpriteAngle, Animation damageAnimation) {
        super(name, sprite, stats);
        this.movementRange = movementRange;
        this.matchSpriteAngle = matchSpriteAngle;
        this.damageAnimation = damageAnimation;
    }

    public Projectile(String name, Sprite sprite, Stats stats, double movementRange, boolean matchSpriteAngle) {
        this(name, sprite, stats, movementRange, matchSpriteAngle, BLANK);
    }


    @Override
    public Projectile copyInitialState() {
        return new Projectile(name, sprite,  stats.copyInitialState(), movementRange, matchSpriteAngle, new Animation(damageAnimation));
    }

    public double getMovementRange() {
        return movementRange;
    }


    @Override
    public void onRemoval(World world) {
        world.add(new TempEntity("Damage Animation", new AnimatedSprite(damageAnimation)), pos);
        super.onRemoval(world);
    }


    @Override
    public void update(World world) {
        super.update(world);
        distanceTravelled += abs(movement.x) + abs(movement.y);

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
