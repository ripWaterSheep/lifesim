package lifesim.main.game.entities.components.items;

import lifesim.main.game.Game;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.MovementEntity;
import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.TempEntity;
import lifesim.main.game.entities.components.Alliance;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.DamageStats;
import lifesim.main.game.entities.enemies.Enemy;
import lifesim.main.game.handlers.World;

import static lifesim.main.util.math.Geometry.getAngleBetween;


public class Weapon extends Item {

    private final Sprite projectileSprite;
    private final double damage;
    private final double speed;
    private final double movementRange;
    private final boolean matchSpriteAngle;


    public Weapon(String name, Sprite sprite, Sprite projectileSprite, double damage, double speed, double movementRange, boolean matchSpriteAngle) {
        super(name, sprite);
        this.projectileSprite = projectileSprite;
        this.damage = damage;
        this.speed = speed;
        this.movementRange = movementRange;
        this.matchSpriteAngle = matchSpriteAngle;
    }


    public double getMovementRange() {
        return movementRange;
    }


    @Override
    public void onClick(World world, Entity entity) {
        Alliance alliance = Alliance.NEUTRAL;
        if (entity.stats instanceof DamageStats) {
            alliance = ((DamageStats) entity.stats).alliance;
        }

        double angle;
        if (alliance.equals(Alliance.PLAYER)) {
            angle = getAngleBetween(MouseInputManager.right.getPos(),
                    new Vector2D(0, 0));
        } else {
            angle = getAngleBetween(Game.getSession().getPlayer().pos, entity.pos);
        }

        Projectile newProjectile = new Projectile(name+" projectile", projectileSprite,
                new DamageStats(damage, alliance, true), speed, angle, movementRange, matchSpriteAngle);

        world.add(newProjectile, entity.pos);
    }
    
}
