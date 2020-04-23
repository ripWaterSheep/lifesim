package lifesim.game.entities.types;

import lifesim.state.engine.Main;
import lifesim.game.entities.Entity;
import lifesim.game.entities.ExplosiveProjectile;
import lifesim.game.entities.Projectile;
import lifesim.game.entities.components.sprites.AnimatedSprite;
import lifesim.game.entities.components.sprites.Animation;
import lifesim.game.entities.components.sprites.ImageSprite;
import lifesim.game.entities.components.sprites.ShapeSprite;
import lifesim.game.entities.components.stats.HealerStats;
import lifesim.util.math.geom.Vector2D;
import lifesim.game.entities.components.stats.Alliance;
import lifesim.game.entities.components.stats.BasicStats;

import java.awt.*;


public enum ProjectileType implements Launchable {


    FIST {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            double strength = Main.getCurrentGame().getPlayer().getStats().getStrength();

            return new Projectile(owner.name + "'s Fist", new ImageSprite("fist_small"), new BasicStats(
                    1 + (strength/100), Alliance.PLAYER), owner, 8, angle, 30, true, true) {
                @Override
                public void eventOnHit(Entity entity) {
                    super.eventOnHit(entity);
                    push(entity, 0.5);
                }
            };
        }
    },


    SMALL_BULLET {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Bullet", new ImageSprite("bullet"),
                    new BasicStats(15, alliance), owner, 12, angle, 175, true, EffectType.SMALL_BOOM);
    }},

    WATER_STREAM {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Water", new ShapeSprite(3, 3, new Color(50, 80, 220, 150)),
                    new BasicStats(2, alliance), owner, 8, angle, 135, false, true);
    }},

    LASER {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Laser", new ShapeSprite(10, 1, new Color(255, 50, 25, 150)),
                    new BasicStats(5, alliance), owner, 10, angle, 175, true, EffectType.SMALL_BOOM);
    }},


    BOMB {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Bomb", new AnimatedSprite(new Animation("bomb",
                    75, new Vector2D(9, 16), 0)), new BasicStats(0, alliance),
                    owner, 0, angle, 500, false, EffectType.BIG_BOOM);
    }},

    CANNONBALL {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Cannon Ball", new AnimatedSprite(new Animation("weapons",
                    75, new Vector2D(8, 8), 1)), new BasicStats(20, alliance), owner,
                    12, angle, 125, false, false);
    }},


    HEAL_SPELL {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Heal Orb", new ShapeSprite(42, 42, new Color(255, 0, 0, 150)),
                    new HealerStats(0, alliance, 15),  owner, 9, angle, 175, true, false);
        }},


    THROWABLE_WALL {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Throwable Wall", new ShapeSprite(8, 32, new Color(100, 150, 200)),
                    new BasicStats(0, alliance), owner, 15, angle, 300, true, true) {
                @Override
                public void eventOnHit(Entity entity) {
                    entity.push(this, 2.5);
                }
            };
        }
    },

}
