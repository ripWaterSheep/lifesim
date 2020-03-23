package lifesim.main.game.handlers;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.enemies.Enemy;

import java.util.ArrayList;

import static lifesim.main.util.math.MyMath.getRand;

public class Spawner {

    private static final int MAX_ENTITIES = 25;


    private final Enemy spawnTemplate;

    private final long spawnInterval;
    private long lastSpawnTime = System.currentTimeMillis();


    public Spawner(Enemy spawnTemplate, long spawnInterval) {
        this.spawnTemplate = spawnTemplate;
        this.spawnInterval = spawnInterval;
    }


    public void attemptSpawn(World world) {
        if (System.currentTimeMillis() - lastSpawnTime > spawnInterval) {
            Vector2D spawnPos = new Vector2D(world.size);
            spawnPos.set(world.size.scale(getRand(-0.5, 0.5)));

            world.add(spawnTemplate.copyInitialState(), spawnPos);
            lastSpawnTime = System.currentTimeMillis();
        }
    }



}
