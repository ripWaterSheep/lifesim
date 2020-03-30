package lifesim.main.game.handlers;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;

import static lifesim.main.util.math.MyMath.getRand;

public class Spawner {

    private static final int MAX_ENTITIES = 25;


    private final Entity spawnTemplate;

    private final long spawnInterval;
    private long lastSpawnTime = System.currentTimeMillis();


    public Spawner(Entity spawnTemplate, long spawnInterval) {
        this.spawnTemplate = spawnTemplate;
        this.spawnInterval = spawnInterval;
    }


    public void attemptSpawn(World world, Player player) {
        if (System.currentTimeMillis() - lastSpawnTime >= spawnInterval) {
            Vector2D spawnPos = world.getSize();
            spawnPos.set(world.getSize().scale(getRand(-0.5, 0.5)));

            world.add(spawnTemplate.copyInitialState(), spawnPos);
            lastSpawnTime = System.currentTimeMillis();
        }
    }



}
