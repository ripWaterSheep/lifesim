package game.ecs.components;

import game.ecs.entities.Entity;
import game.setting.World;

import java.util.ArrayList;



public class SpawnerComponent implements CopyableComponent {

    private static final int MAX_SPAWN = 10;


    private final Entity spawnTemplate;

    public Entity getSpawnTemplate() {
        return spawnTemplate;
    }

    private ArrayList<Entity> allSpawn = new ArrayList<>();


    private final long spawnInterval;
    private long lastSpawnTime = System.currentTimeMillis();



    public SpawnerComponent(long spawnInterval, Entity spawnTemplate) {
        this.spawnInterval = spawnInterval;
        this.spawnTemplate = spawnTemplate;
    }


    private void spawnAt(double x, double y, World world) {
        Entity spawnedEntity = spawnTemplate.copyInitialState();
        world.add(spawnedEntity);
        allSpawn.add(spawnedEntity);

        for (PositionComponent pos : spawnedEntity.getAll(PositionComponent.class)) {
            pos.set(x, y);
        }

        lastSpawnTime = System.currentTimeMillis();
    }


    public void attemptSpawn(double x, double y, World world) {
        if (System.currentTimeMillis() - lastSpawnTime >= spawnInterval && allSpawn.size() < MAX_SPAWN) {
            spawnAt(x, y, world);
        }
    }


    @Override
    public SpawnerComponent copyInitialState() {
        return new SpawnerComponent(spawnInterval, spawnTemplate);
    }

}
