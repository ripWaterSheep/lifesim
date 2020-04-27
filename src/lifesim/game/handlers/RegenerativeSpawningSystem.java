package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.entities.types.Spawnable;
import lifesim.state.Game;

import java.util.ArrayList;

public class RegenerativeSpawningSystem extends SpawningSystem {

    private final ArrayList<Entity> spawn = new ArrayList<>();
    private final int maxSpawn;

    /** Spawns an entity only when a previously spawned entity is removed. */
    public RegenerativeSpawningSystem(Spawnable spawnable, long spawnInterval, int maxSpawn, Game game) {
        super(spawnable, spawnInterval, game);
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
