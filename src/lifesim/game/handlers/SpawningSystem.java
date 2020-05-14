package lifesim.game.handlers;

import lifesim.util.geom.Vector2D;
import lifesim.game.entities.types.Spawnable;


public class SpawningSystem {
    private final Spawnable spawnable;
    private final String name;

    private final long spawnInterval;
    private long lastSpawnTime = System.currentTimeMillis();


    /** Spawns entities of a certain type around the player after a certain interval passes. */
    public SpawningSystem(Spawnable spawnable, long spawnInterval) {
        this.spawnable = spawnable;
        name = spawnable.spawnEntity().name;
        this.spawnInterval = spawnInterval;
    }

    /** After the length of the spawn interval passes, spawn the entity if its type is not at it's world limit. */
    public void update(World world, Vector2D pos) {
        if (System.currentTimeMillis() - lastSpawnTime > spawnInterval && !world.isMaxedOut(spawnable, name)) {
            world.add(spawnable.spawnEntity(), pos);
            lastSpawnTime = System.currentTimeMillis();
        }
    }

}
