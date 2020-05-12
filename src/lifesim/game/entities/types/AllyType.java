package lifesim.game.entities.types;

import lifesim.game.entities.AIEntity;
import lifesim.game.entities.Entity;
import lifesim.game.entities.RangedAIEntity;
import lifesim.game.entities.SolidEntity;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.AnimatedSprite;
import lifesim.util.sprites.Animation;
import lifesim.util.sprites.DirectionalAnimatedSprite;
import lifesim.util.sprites.ShapeSprite;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.HealthStats;

import java.awt.*;


public enum AllyType implements Spawnable {


    UNIBOT {
        @Override
        public Entity spawnEntity() {
            return new AIEntity("Unibot", new DirectionalAnimatedSprite(
                    new Animation("unibot", 200, new Vector2D(0, 0),  new Vector2D(6,14)),
                    new Animation("unibot", 200, new Vector2D(0, 14), new Vector2D(6, 14)),
                    new Animation("unibot", 100, new Vector2D(0, 28), new Vector2D(6, 14)),
                    new Animation("unibot", 100, new Vector2D(0, 42), new Vector2D(6, 14)),
                    new Animation("unibot", 100, new Vector2D(0, 56), new Vector2D(6, 14))),
                    new HealthStats(1, Alliance.PLAYER, 50), 2, 150);
        }
    },

    WALLBOT {
        @Override
        public Entity spawnEntity() {
            return new SolidEntity("Wallbot", new AnimatedSprite(new Animation("wallbot", 75,
                    new Vector2D(0, 0), new Vector2D(64, 36))), new HealthStats(0, Alliance.PLAYER, 750),
                    13);
        }
    },

    MELEEBOT() {
        @Override
        public Entity spawnEntity() {
            return new AIEntity("Meleebot", new DirectionalAnimatedSprite(
                    new Animation("meleebot", 200, new Vector2D(0, 0), new Vector2D(10, 18)),
                    new Animation("meleebot", 100, new Vector2D(0, 18), new Vector2D(10, 18)),
                    new Animation("meleebot", 100, new Vector2D(0, 36), new Vector2D(10, 18)),
                    new Animation("meleebot", 100, new Vector2D(0, 54), new Vector2D(10, 18)),
                    new Animation("meleebot", 100, new Vector2D(0, 72), new Vector2D(10, 18))
            ),
                    new HealthStats(2.5, Alliance.PLAYER, 35), 3, 200);
        }
    };


    @Override
    public int getMaxPerWorld() {
            return 12;
    }
}
