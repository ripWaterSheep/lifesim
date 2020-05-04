package lifesim.game.entities.types;

import lifesim.state.engine.Main;
import lifesim.game.entities.Entity;
import lifesim.game.entities.ExplosiveProjectile;
import lifesim.game.entities.Projectile;
import lifesim.util.sprites.AnimatedSprite;
import lifesim.util.sprites.Animation;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.ShapeSprite;
import lifesim.game.entities.stats.HealerStats;
import lifesim.util.geom.Vector2D;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.BasicStats;

import java.awt.*;


public enum ProjectileType implements Launchable {


    FIST {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            double strength = Main.getCurrentPlayer().getStats().getStrength();

            return new Projectile(owner.name + "'s Fist", new ImageSprite("fist"), new BasicStats(1 + (strength/100),
                    Alliance.PLAYER), owner, 6, angle, 30, true, 10, true) {
            };
        }
    },


    SMALL_BULLET {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Bullet", new ImageSprite("bullet"),
                    new BasicStats(15, alliance), owner, 12, angle, 175, 25, true, EffectType.SMALL_BOOM);
    }},

    WATER_STREAM {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Water", new ShapeSprite(3, 3, new Color(50, 80, 220, 150)),
                    new BasicStats(2, alliance), owner, 8, angle, 135, false, 20,  true);
    }},

    LASER {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Laser", new ShapeSprite(10, 1, new Color(255, 50, 25, 150)),
                    new BasicStats(5, alliance), owner, 10, angle, 175, 10, true, EffectType.SMALL_BOOM);
    }},


    BOMB {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new ExplosiveProjectile("Bomb", new AnimatedSprite(new Animation("bomb",
                    75, new Vector2D(0, 0), new Vector2D(9, 16))), new BasicStats(0, alliance),
                    owner, 0, angle, 500, 100, false, EffectType.BIG_BOOM);
    }},

    CANNONBALL {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Cannon Ball", new AnimatedSprite(new Animation("weapons",
                    75, new Vector2D(0, 16), new Vector2D(16, 16))), new BasicStats(20, alliance), owner,
                    12, angle, 125, false, 25, false);
    }},


    HEAL_SPELL {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Heal Orb", new ShapeSprite(42, 42, new Color(255, 0, 0, 150)),
                    new HealerStats(0, alliance, 15), owner, 9, angle, 175, true, 0, false);
    }},


    THROWABLE_WALL {
        @Override
        public Projectile launchEntity(Entity owner, Alliance alliance, double angle) {
            return new Projectile("Throwable Wall", new ShapeSprite(8, 32, new Color(100, 150, 200)),
                    new BasicStats(0, alliance), owner, 15, angle, 300, true, 50, true);
    }}


}
