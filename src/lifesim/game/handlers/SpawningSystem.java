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
    private final Game game;

    private final long spawnInterval;
    private long lastSpawnTime = System.currentTimeMillis();


    /** Spawns entities of a certain type around the player after a certain interval passes. */
    public SpawningSystem(Spawnable spawnable, long spawnInterval, Game game) {
        this.spawnable = spawnable;
        this.spawnInterval = spawnInterval;
        this.game = game;
    }


    public Entity attemptSpawn(World world, Player player) {
        //Vector2D spawnPos = world.getSize();
        //spawnPos.set(world.getSize().scale(getRand(-0.5, 0.5), getRand(-0.5, 0.5)));
        // Spawn entities around player in a circle.

        Vector2D spawnPos = player.getPos();
        double distance = MyMath.getRand(MIN_SPAWN_RADIUS, MAX_SPAWN_RADIUS);
        Vector2D spawnOffset = Vector2D.newMagDir(distance, MyMath.getRand(0, 360));
        spawnPos.translate(spawnOffset);

        Entity entity = spawnable.spawnEntity();
        world.add(entity, spawnPos);
        lastSpawnTime = System.currentTimeMillis();
        return entity;
    }


    public void update(World world, Player player) {
        double currentInterval = spawnInterval;

        if (spawnable.spawnEntity().isEnemy()) { // Make only enemy spawn rate affected by difficulty.
            currentInterval = (long) (spawnInterval*game.getDifficulty().spawnInterval);
        }

        if (System.currentTimeMillis() - lastSpawnTime > currentInterval) {
            attemptSpawn(world, player);
        }
    }


}
