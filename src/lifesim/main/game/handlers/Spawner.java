package lifesim.main.game.handlers;

import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.types.Spawnable;

import static lifesim.main.util.math.MyMath.getRand;

public class Spawner {

    private static final int MAX_ENTITIES = 25;
    private static final int MIN_SPAWN_RADIUS = 250;
    private static final int MAX_SPAWN_RADIUS = 400;


    private final Spawnable entityType;

    private final long spawnInterval;
    private long lastSpawnTime = System.currentTimeMillis();


    public Spawner(Spawnable entityType, long spawnInterval) {
        this.entityType = entityType;
        this.spawnInterval = spawnInterval;
    }


    public void attemptSpawn(World world, Player player) {
        if (System.currentTimeMillis() - lastSpawnTime >= spawnInterval && world.getEntities().size() < MAX_ENTITIES) {
            //Vector2D spawnPos = world.getSize();
            //spawnPos.set(world.getSize().scale(getRand(-0.5, 0.5), getRand(-0.5, 0.5)));
            Vector2D spawnPos = player.pos.copy();
            Vector2D distFromPlayer = new Vector2D(0, 0);
            distFromPlayer.setMagDir(getRand(MIN_SPAWN_RADIUS, MAX_SPAWN_RADIUS), getRand(0, 360));


            world.add(entityType.spawnNew(), spawnPos.translate(distFromPlayer));
            lastSpawnTime = System.currentTimeMillis();
        }
    }


}
