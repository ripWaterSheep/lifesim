package lifesim.main.game.entities.types;

import lifesim.main.game.entities.*;
import lifesim.main.util.math.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.BasicStats;
import lifesim.main.game.entities.components.stats.HealerStats;

import java.awt.*;


public enum ProjectileType implements Launchable {


    FIST {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            double strength = 3;
            if (owner instanceof Player) strength = ((Player) owner).getStats().getStrength();

            return new Projectile(owner.name + "'s Fist", new Sprite("fist"), new BasicStats(
                    6, 1 + (strength/100), Alliance.PLAYER), owner, 35, angle, false, true) {
                @Override
                public void eventOnHit(Entity entity) {
                    super.eventOnHit(entity);
                    entity.push(getVelocity().scale(0.5));
                }
            };
        }
    },


    SMALL_BULLET {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Bullet", new Sprite(2, 2, new Color(0, 0, 0, 150)),
                    new BasicStats(15,  6, alliance), owner, 175, angle, false, EffectType.SMALL_BOOM);
    }},

    WATER_DROP {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Water", new Sprite(3, 3, new Color(50, 80, 220, 150)),
                    new BasicStats(8, 1.5, alliance), owner, 125, angle, true, false);
    }},

    LASER {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Laser", new Sprite(10, 1, new Color(255, 50, 25, 150)),
                    new BasicStats(13, 8, alliance), owner, 150, angle, true, EffectType.SMALL_BOOM);
    }},


    BOMB {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Bomb", new AnimatedSprite(new Animation("bomb",
                    75, new Vector2D(9, 16), 0)), new BasicStats(0, 0, alliance),
                    owner, 5, angle, false, EffectType.BIG_BOOM);
    }},

    BALL {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Ball", new Sprite(4, 4, Color.BLACK), new BasicStats(
                    18, 15, alliance), owner, 125, angle, true, false);
    }},


    HEAL_ORB {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Heal Orb", new Sprite(64, 64, new Color(255, 0, 0, 125)),
                    new HealerStats(6, 0, alliance, 6),  owner, 150, angle, false, false);
        }},


    PUSH_TEST {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Push Test", new Sprite(16, 16, new Color(100, 150, 200)),
                    new BasicStats(12, 0, alliance), owner, 300, angle, false, false) {
                @Override
                public void eventOnHit(Entity entity) {
                    entity.push(getVelocity().scale(2.5));
                }
            };
        }
    },

}
