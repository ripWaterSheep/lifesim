package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.util.geom.Vector2D;
import lifesim.game.entities.types.Spawnable;

public class SpawningSystem {
    private final Spawnable spawnable;

    private final long spawnInterval;
    private long lastSpawnTime = System.currentTimeMillis();


    /** Spawns entities of a certain type around the player after a certain interval passes. */
    public SpawningSystem(Spawnable spawnable, long spawnInterval) {
        this.spawnable = spawnable;
        this.spawnInterval = spawnInterval;
    }


    public Entity attemptSpawn(World world, Vector2D pos) {
        Entity entity = spawnable.spawnEntity();
        world.add(entity, pos);
        lastSpawnTime = System.currentTimeMillis();
        return entity;
    }


    public void update(World world, Vector2D pos) {
        if (System.currentTimeMillis() - lastSpawnTime > spawnInterval) {
            attemptSpawn(world, pos);
        }
    }

}
