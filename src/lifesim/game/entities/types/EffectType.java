package lifesim.game.entities.types;

import lifesim.util.sprites.AnimatedSprite;
import lifesim.util.sprites.Animation;
import lifesim.game.entities.EffectEntity;
import lifesim.game.entities.Entity;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.BasicStats;
import lifesim.util.geom.Vector2D;


public enum  EffectType implements Spawnable {

    SMALL_BOOM() {
        @Override
        public Entity spawnEntity() {
            return new EffectEntity("Small Boom", new AnimatedSprite(new Animation("boom",
                    40, new Vector2D(0, 0), new Vector2D(16, 16))), new BasicStats( 0.5, Alliance.INVINCIBLE));
    }},

    BIG_BOOM() {
        @Override
        public Entity spawnEntity() {
            return new EffectEntity("Big Boom", new AnimatedSprite(new Animation("big_boom",
                    40, new Vector2D(0, 0), new Vector2D(32, 32))), new BasicStats(5, Alliance.INVINCIBLE));
    }};


    @Override
    public int getMaxPerWorld() {
        return 20;
    }
}
