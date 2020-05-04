package lifesim.game.entities.types;

import lifesim.game.entities.Entity;
import lifesim.game.entities.SpawnerEntity;
import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.HealthStats;
import lifesim.game.handlers.SpawningSystem;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.AnimatedSprite;
import lifesim.util.sprites.Animation;


public enum FactoryType implements Spawnable {

    STARTER() {
    @Override
    public Entity spawnEntity() {
        return new SpawnerEntity("Starter Factory", new AnimatedSprite(new Animation("factories", 200,
                new Vector2D(0, 0), new Vector2D(42, 32))), new HealthStats(0, Alliance.PLAYER, 500),
                new SpawningSystem(AllyType.UNIBOT, 6000));
    }}
}
