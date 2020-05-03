package lifesim.game.entities.types;

import lifesim.game.entities.Entity;
import lifesim.game.entities.SpawnerEntity;
import lifesim.game.entities.stats.InanimateStats;
import lifesim.game.handlers.SpawningSystem;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.AnimatedSprite;
import lifesim.util.sprites.Animation;

public enum FactoryTypes implements Spawnable {

    STARTER() {

    @Override
    public Entity spawnEntity() {
        return new SpawnerEntity("Starter Factory", new AnimatedSprite(new Animation("factories", 250,
                new Vector2D(0, 0), new Vector2D(32, 32))), new InanimateStats(), new SpawningSystem(AllyType.UNIBOT, 3000));
    }}
}
