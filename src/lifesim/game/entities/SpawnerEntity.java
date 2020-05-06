package lifesim.game.entities;

import lifesim.game.entities.stats.Stats;
import lifesim.game.handlers.SpawningSystem;
import lifesim.game.handlers.World;
import lifesim.util.sprites.Sprite;

public class SpawnerEntity extends SolidEntity {

    private final SpawningSystem spawningSystem;


    public SpawnerEntity(String name, Sprite sprite, Stats stats, SpawningSystem spawningSystem, int baseDepth) {
        super(name, sprite, stats, baseDepth);
        this.spawningSystem = spawningSystem;
    }


    @Override
    public void update(World world) {
        super.update(world);
        spawningSystem.update(world, getPos());
    }
}
