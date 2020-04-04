package lifesim.main.game.entities.types;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.RangedAIEntity;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.stats.HealthStats;

import java.awt.*;

import static lifesim.main.game.entities.types.ProjectileType.SMALL_BULLET;

public enum AllyType implements Spawnable{

    ALLY_TEST {
        @Override
        public Entity spawnNew() {
            return new RangedAIEntity("Ally", new Sprite(8, 8, new Color(50, 100, 255)),
                            new HealthStats(3, 5, Alliance.PLAYER, 40), 200, 2500, SMALL_BULLET);
    }}

}
