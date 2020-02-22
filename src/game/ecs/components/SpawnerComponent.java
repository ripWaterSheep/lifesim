package game.ecs.components;

import game.ecs.entities.Entity;
import game.setting.world.World;

import java.util.ArrayList;

import static util.TimeUtil.getCurrentTime;

public class SpawnerComponent implements CopyableComponent {

    private static final int MAX_SPAWN = 10;


    private final Entity spawnTemplate;

    public Entity getSpawnTemplate() {
        return spawnTemplate;
    }

    private ArrayList<Entity> allSpawn = new ArrayList<>();


    private final long spawnInterval;
    private long lastSpawnTime = 0;


    /** Distance player must be from the entity for the entity to attempt to spawn.
     * If it is negative, then there is no maximum range.
     */
    private final double activeRange;

    public double getActiveRange() {
        return activeRange;
    }


    public SpawnerComponent(Entity spawnTemplate, long spawnInterval, double activeRange) {
        this.spawnTemplate = spawnTemplate;
        this.spawnInterval = spawnInterval;
        this.activeRange = activeRange;
    }


        private void spawnAt(double x, double y, World world) {
        Entity spawnedEntity = spawnTemplate.copyInitialState();
        allSpawn.add(spawnedEntity);
        world.add(spawnedEntity);

        lastSpawnTime = getCurrentTime();
    }


    public void attemptSpawn(double x, double y, World world) {
        if (getCurrentTime() - lastSpawnTime >= spawnInterval && allSpawn.size() < MAX_SPAWN) {
            spawnAt(x, y, world);
        }
    }


    @Override
    public SpawnerComponent copyInitialState() {
        return new SpawnerComponent(spawnTemplate, spawnInterval, activeRange);
    }

    @Override
    public SpawnerComponent copyCurrentState() {
        SpawnerComponent spawner = new SpawnerComponent(spawnTemplate, spawnInterval, activeRange);
        spawner.lastSpawnTime = lastSpawnTime;
        spawner.allSpawn = allSpawn;
        return spawner;
    }

}