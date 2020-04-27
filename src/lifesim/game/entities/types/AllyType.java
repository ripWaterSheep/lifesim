package lifesim.game.entities.types;

import lifesim.game.entities.AIEntity;
import lifesim.game.entities.Entity;
import lifesim.game.entities.RangedAIEntity;
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
        }}

}
