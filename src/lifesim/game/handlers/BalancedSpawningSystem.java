package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.entities.types.Spawnable;
import lifesim.state.Game;
import lifesim.util.geom.Vector2D;

import java.util.ArrayList;

public class BalancedSpawningSystem extends SpawningSystem {

    private final ArrayList<Entity> spawn = new ArrayList<>();
    private final int maxSpawn;

    /** Spawns an entity only when a previously spawned entity is removed. */
    public BalancedSpawningSystem(Spawnable spawnable, long spawnInterval, int maxSpawn) {
        super(spawnable, spawnInterval);
        this.maxSpawn = maxSpawn;
    }


    @Override
    public Entity attemptSpawn(World world, Vector2D pos) {
        Entity entity = super.attemptSpawn(world, pos);
        spawn.add(entity);
        return entity;
    }

    @Override
    public void update(World world, Vector2D pos) {
        if (spawn.size() < maxSpawn) {
            super.update(world, pos);
        }
        spawn.removeIf(entity -> !entity.getStats().isAlive());
    }
}
