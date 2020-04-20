package lifesim.main.game.entities.types;

import lifesim.main.game.entities.AIEntity;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.RangedAIEntity;
import lifesim.main.game.entities.components.sprites.ShapeSprite;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.HealthStats;

import java.awt.*;

import static lifesim.main.game.entities.types.ProjectileType.SMALL_BULLET;

public enum AllyType implements Spawnable{

    ALLY_TEST {
        @Override
        public Entity spawnEntity() {
            return new RangedAIEntity("Ally", new ShapeSprite(8, 8, new Color(50, 100, 255)),
                            new HealthStats(3, 4, Alliance.PLAYER, 50), Alliance.PLAYER, 200, 2000, SMALL_BULLET);
    }},

    BIG_BOI {
        @Override
        public Entity spawnEntity() {
            return new AIEntity("Dabifer", new ShapeSprite(16, 16, new Color(113, 135, 103)),
                    new HealthStats(4.5, 4, Alliance.PLAYER, 80), Alliance.PLAYER, 300);
        }}

}
