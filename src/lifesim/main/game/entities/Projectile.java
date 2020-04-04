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

    private final Entity owner;

    private final double range;
    private double distanceTravelled;

    private final boolean matchSpriteAngle;
    private final Animation damageAnimation;

    private final boolean destroyOnDamage;


    public Projectile(String name, Sprite sprite, Stats stats, Entity owner, double range, double angle, boolean destroyOnDamage, boolean matchSpriteAngle, Animation damageAnimation) {
        super(name, sprite, stats);
        this.owner = owner;
        this.range = range;
        velocity.setDirection(angle);
        this.matchSpriteAngle = matchSpriteAngle;
        this.damageAnimation = damageAnimation;
        this.destroyOnDamage = destroyOnDamage;
    }

    public Projectile(String name, Sprite sprite, Stats stats, Entity owner, double range, double angle, boolean destroyOnDamage, boolean matchSpriteAngle) {
        this(name, sprite, stats, owner, range, angle, matchSpriteAngle, destroyOnDamage, BLANK);
    }


    @Override
    public Entity getOwner() {
        return owner;
    }

    public double getRange() {
        return range;
    }


    @Override
    public void handleCollision(Entity entity, World world) {
        super.handleCollision(entity, world);

        if (canAttack(entity)) {
            eventOnHit(entity);
            world.add(new EffectEntity("Damage Animation", new AnimatedSprite(damageAnimation)), pos);
            if (destroyOnDamage) removeFromWorld();
        }
    }

    /** When this projectile hits another entity that it can attack, this customizable function is called. */
    public void eventOnHit(Entity entity) {
    }


    @Override
    public void update(World world) {
        super.update(world);
        distanceTravelled += abs(velocity.x) + abs(velocity.y);

        if (distanceTravelled > range) {
            removeFromWorld();
       }
    }

    @Override
    public void render(Graphics2D g2d) {
        if (matchSpriteAngle)
            g2d.rotate(toRadians(velocity.getDirection()), getDisplayPos().x, getDisplayPos().y);
        super.render(g2d);
    }
}
