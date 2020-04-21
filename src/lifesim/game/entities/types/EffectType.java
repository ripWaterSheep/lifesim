package lifesim.game.entities.types;

import lifesim.game.entities.components.sprites.AnimatedSprite;
import lifesim.game.entities.components.sprites.Animation;
import lifesim.game.entities.EffectEntity;
import lifesim.game.entities.Entity;
import lifesim.game.entities.components.stats.Alliance;
import lifesim.game.entities.components.stats.BasicStats;
import lifesim.util.math.Vector2D;


public enum  EffectType implements Spawnable {

    SMALL_BOOM() {
        @Override
        public Entity spawnEntity() {
            return new EffectEntity("Small Boom", new AnimatedSprite(new Animation("boom",
                    40, new Vector2D(16, 16), 0)), new BasicStats( 1, Alliance.NEUTRAL));
    }},

    BIG_BOOM() {
        @Override
        public Entity spawnEntity() {
            return new EffectEntity("Big Boom", new AnimatedSprite(new Animation("big_boom",
                    40, new Vector2D(32, 32), 0)), new BasicStats(6, Alliance.NEUTRAL));
        }},

}
