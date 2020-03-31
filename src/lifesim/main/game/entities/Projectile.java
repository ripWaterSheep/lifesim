package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.BasicStats;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.handlers.World;

import java.awt.*;

import static java.lang.Math.*;


public class Projectile extends Entity {

    private static final Animation BLANK = new Animation(0, "blank");

    private final double range;
    private double distanceTravelled;

    private final boolean matchSpriteAngle;

    private final Animation damageAnimation;


    public Projectile(String name, Sprite sprite, Stats stats, double range, double angle, boolean matchSpriteAngle, Animation damageAnimation) {
        super(name, sprite, stats);
        this.range = range;
        movement.setDirection(angle);
        this.matchSpriteAngle = matchSpriteAngle;
        this.damageAnimation = damageAnimation;
    }

    public Projectile(String name, Sprite sprite, BasicStats stats, double range, double angle, boolean matchSpriteAngle) {
        this(name, sprite, stats, range, angle, matchSpriteAngle, BLANK);
    }


    public double getRange() {
        return range;
    }

    @Override
    public void onRemoval(World world) {
        world.add(new EffectEntity("Damage Animation", new AnimatedSprite(damageAnimation)), pos);
        super.onRemoval(world);
    }


    @Override
    public void update(World world) {
        super.update(world);
        distanceTravelled += abs(movement.x) + abs(movement.y);

        if (distanceTravelled > range) {
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
