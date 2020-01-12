package game.components.structures;

import game.components.World;
import game.components.entities.MobileEntity;
import java.awt.*;
import java.util.ArrayList;

import static util.TimeUtil.getCurrentTime;


public class Spawner extends Structure {


    private static ArrayList<Spawner> instances = new ArrayList<>();

    public static ArrayList<Spawner> getSpawnerInstances() { return instances; }


    private static final int SPAWN_LIMIT = 8;

    private static ArrayList<MobileEntity> allSpawned = new ArrayList<>();

    private MobileEntity spawnable;

    private long spawnInterval;
    private long lastSpawnTime = 0;


    public Spawner(String name, double x, double y, double width, double height, World world, Color color, MobileEntity spawnable, long spawnInterval) {
        super(name, x, y, width, height, world, color);
        Spawner.instances.add(this);

        this.spawnable = spawnable;
        this.spawnInterval = spawnInterval;
    }



    @Override
    public void act() {
        // Spawn a new clone of the MobileEntity passed as a parameter if spawn interval passes and spawn limit has not been reached
        if (getCurrentTime() - lastSpawnTime > spawnInterval && allSpawned.size() < SPAWN_LIMIT) {
            MobileEntity spawn = spawnable.getNewCloneAt(x, y, world);
            allSpawned.add(spawn); // Add spawned entity to list to keep track of size
            lastSpawnTime = getCurrentTime();
        }

        for (MobileEntity entity: allSpawned) {
             System.out.println(entity.isAlive());
        }

        // Clean dead spawn out from list so that more are allowed to spawn
        allSpawned.removeIf(entity -> !entity.isAlive());
        System.out.println(allSpawned.size());
    }


}


