package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.state.Game;
import lifesim.util.geom.Vector2D;
import lifesim.game.entities.types.Spawnable;
import lifesim.util.MyMath;

public class SpawningSystem {

    private static final int MIN_SPAWN_RADIUS = 250;
    private static final int MAX_SPAWN_RADIUS = 400;

    private final Spawnable spawnable;

    private final long spawnInterval;
    private long lastSpawnTime = System.currentTimeMillis();


    /** Spawns entities of a certain type around the player after a certain interval passes. */
    public SpawningSystem(Spawnable spawnable, long spawnInterval) {
        this.spawnable = spawnable;
        this.spawnInterval = spawnInterval;
    }


    public Entity attemptSpawn(World world, Vector2D pos) {
        //Vector2D spawnPos = world.getSize();
        //spawnPos.set(world.getSize().scale(getRand(-0.5, 0.5), getRand(-0.5, 0.5)));
        // Spawn entities around player in a circle.
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
