package lifesim.main.game.handlers;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.types.Spawnable;

import java.util.ArrayList;

public class RegenerativeSpawningSystem extends SpawningSystem {

    private final ArrayList<Entity> spawn = new ArrayList<>();
    private final int maxSpawn;

    /** Spawns an entity only when a previously spawned entity is removed. */
    public RegenerativeSpawningSystem(Spawnable spawnable, long spawnInterval, int maxSpawn) {
        super(spawnable, spawnInterval);
        this.maxSpawn = maxSpawn;
    }


    @Override
    public Entity attemptSpawn(World world, Player player) {
        Entity entity = super.attemptSpawn(world, player);
        spawn.add(entity);
        return entity;
    }

    @Override
    public void update(World world, Player player) {
        if (spawn.size() < maxSpawn) {
            super.update(world, player);
        }
        spawn.removeIf(entity -> !entity.getStats().isAlive());
    }
}
