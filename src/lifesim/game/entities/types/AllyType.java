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


public enum AllyType implements Spawnable{

    ALLY_TEST {
        @Override
        public Entity spawnEntity() {
            return new RangedAIEntity("Ally", new ShapeSprite(8, 8, new Color(50, 100, 255)),
                    new HealthStats( 3, Alliance.PLAYER, 60), 4.5, 250, 1500, ProjectileType.SMALL_BULLET);
    }},

    BIG_BOI {
        @Override
        public Entity spawnEntity() {
            return new AIEntity("Dabifer", new ShapeSprite(16, 16, new Color(113, 135, 103)),
                    new HealthStats(4, Alliance.PLAYER, 100), 4, 200);
    }},


    UNIBOT {
        @Override
        public Entity spawnEntity() {
            return new AIEntity("Unibot", new DirectionalAnimatedSprite(
                    new Animation("unibot", 200, new Vector2D(0, 0),  new Vector2D(6,14)),
                    new Animation("unibot", 200, new Vector2D(0, 14), new Vector2D(6, 14)),
                    new Animation("unibot", 100, new Vector2D(0, 28), new Vector2D(6, 14)),
                    new Animation("unibot", 100, new Vector2D(0, 42), new Vector2D(6, 14)),
                    new Animation("unibot", 100, new Vector2D(0, 56), new Vector2D(6, 14))),
                    new HealthStats(2, Alliance.PLAYER, 50), 2, 150);
        }
    },


    WALLBOT {
        @Override
        public Entity spawnEntity() {
            return new SolidEntity("Wallbot", new AnimatedSprite(new Animation("wallbot", 75,
                    new Vector2D(0, 0), new Vector2D(64, 36))), new HealthStats(0, Alliance.PLAYER, 1000),
                    13);
        }
    }

}
