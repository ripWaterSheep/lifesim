package lifesim.main.game.entities.types;

import lifesim.main.game.entities.EffectEntity;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.BasicStats;
import lifesim.main.util.math.Vector2D;
import lifesim.main.game.entities.components.sprites.Animation;


public enum  EffectType implements Spawnable {

    SMALL_BOOM() {
        @Override
        public Entity spawnEntity() {
            return new EffectEntity("Small Boom", new AnimatedSprite(new Animation("boom",
                    40, new Vector2D(16, 16), 0)), new BasicStats(0, 0.2, Alliance.NEUTRAL));
    }},

    BIG_BOOM() {
        @Override
        public Entity spawnEntity() {
            return new EffectEntity("Big Boom", new AnimatedSprite(new Animation("big_boom",
                    40, new Vector2D(32, 32), 0)), new BasicStats(0, 7, Alliance.NEUTRAL));
        }},

}
