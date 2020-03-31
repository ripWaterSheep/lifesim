package lifesim.main.game.entities.types;

import lifesim.main.game.entities.Projectile;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.BasicStats;

import java.awt.*;

import static lifesim.main.game.entities.types.EffectTypes.BOOM;


public enum ProjectileType implements Launchable {

    SMALL_BULLET {
        @Override
        public Projectile launchNew(Alliance alliance, double angle) {
            return new Projectile("Bullet", new Sprite(2, 2, new Color(0, 0, 0, 150)),
                    new BasicStats(6,  12, alliance), 150, angle, false, new Animation(BOOM));
    }},

    WATER_DROP {
        @Override
        public Projectile launchNew(Alliance alliance, double angle) {
            return new Projectile("Water", new Sprite(2, 2, new Color(50, 80, 220, 150)),
                    new BasicStats(12, 1, alliance), 120, angle, false);
    }},

    LASER {
        @Override
        public Projectile launchNew(Alliance alliance, double angle) {
            return new Projectile("Laser", new Sprite(10, 1, new Color(255, 0, 25, 150)),
                    new BasicStats(20, 3, alliance), 150, angle, true, new Animation(BOOM));
    }},

    BOMB {
        @Override
        public Projectile launchNew(Alliance alliance, double angle) {
            return new Projectile("Bomb", new AnimatedSprite(new Animation("bomb", 75, new Vector2D(9, 16), 0)),
                    new BasicStats(0, 20, alliance), 5, angle, false, new Animation(BOOM));
    }},

    BALL {
        @Override
        public Projectile launchNew(Alliance alliance, double angle) {
            return new Projectile("Ball", new Sprite(4, 4, Color.BLACK), new BasicStats(15, 10, alliance),
                    125, angle, false);
    }}

}
