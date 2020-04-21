package lifesim.game.entities.types;

import lifesim.game.entities.Entity;
import lifesim.game.entities.ExplosiveProjectile;
import lifesim.game.entities.Player;
import lifesim.game.entities.Projectile;
import lifesim.game.entities.components.sprites.AnimatedSprite;
import lifesim.game.entities.components.sprites.Animation;
import lifesim.game.entities.components.sprites.ImageSprite;
import lifesim.game.entities.components.sprites.ShapeSprite;
import lifesim.game.entities.components.stats.HealerStats;
import lifesim.util.math.Vector2D;
import lifesim.game.entities.components.stats.Alliance;
import lifesim.game.entities.components.stats.BasicStats;

import java.awt.*;


public enum ProjectileType implements Launchable {


    FIST {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            double strength = (owner instanceof Player)? ((Player) owner).getStats().getStrength() : 1;

            return new Projectile(owner.name + "'s Fist", new ImageSprite("fist"), new BasicStats(
                    1 + (strength/100), Alliance.PLAYER), owner,  6, angle, 35,false, true) {
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
            return new ExplosiveProjectile("Bullet", new ShapeSprite(2, 2, new Color(0, 0, 0, 150)),
                    new BasicStats(6, alliance), owner, 15, angle, 175, false, EffectType.SMALL_BOOM);
    }},

    WATER_DROP {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Water", new ShapeSprite(3, 3, new Color(50, 80, 220, 150)),
                    new BasicStats(1.5, alliance), owner, 8, angle, 125, true, false);
    }},

    LASER {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Laser", new ShapeSprite(10, 1, new Color(255, 50, 25, 150)),
                    new BasicStats(6, alliance), owner, 13, angle, 150, true, EffectType.SMALL_BOOM);
    }},


    BOMB {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Bomb", new AnimatedSprite(new Animation("bomb",
                    75, new Vector2D(9, 16), 0)), new BasicStats(0, alliance),
                    owner, 0, angle, 1, false, EffectType.BIG_BOOM);
    }},

    BALL {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Ball", new ShapeSprite(4, 4, Color.BLACK), new BasicStats(
                    30, alliance), owner, 18, angle, 125, true, false);
    }},


    HEAL_ORB {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Heal Orb", new ShapeSprite(42, 42, new Color(255, 0, 0, 100)),
                    new HealerStats(0, alliance, 12),  owner, 8, angle, 175, false, false);
        }},


    THROWABLE_WALL {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Push Test", new ShapeSprite(8, 32, new Color(100, 150, 200)),
                    new BasicStats(0, alliance), owner, 15, angle, 300, false, true) {
                @Override
                public void eventOnHit(Entity entity) {
                    entity.push(this, 2.5);
                }
            };
        }
    },

}
