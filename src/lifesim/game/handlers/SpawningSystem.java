package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.util.math.Vector2D;
import lifesim.game.entities.types.Spawnable;
import lifesim.util.math.MyMath;

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


    public Entity attemptSpawn(World world, Player player) {
        //Vector2D spawnPos = world.getSize();
        //spawnPos.set(world.getSize().scale(getRand(-0.5, 0.5), getRand(-0.5, 0.5)));
        Vector2D spawnPos = player.getPos();
        Vector2D distFromPlayer = new Vector2D(0, 0);
        distFromPlayer.setMagDir(MyMath.getRand(MIN_SPAWN_RADIUS, MAX_SPAWN_RADIUS), MyMath.getRand(0, 360));
        spawnPos.translate(distFromPlayer);

        Entity entity = spawnable.spawnEntity();
        world.add(entity, spawnPos);
        lastSpawnTime = System.currentTimeMillis();
        return entity;
    }


    public void update(World world, Player player) {
        if (System.currentTimeMillis() - lastSpawnTime > spawnInterval) {
            attemptSpawn(world, player);
        }
    }


}
