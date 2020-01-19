package game.components.structures;

import game.components.World;
import game.components.entities.Creature;
import java.awt.*;
import java.util.ArrayList;

import static util.MyMath.betterRound;
import static util.TimeUtil.getCurrentTime;


public class Spawner extends Structure {


    private static ArrayList<Spawner> instances = new ArrayList<>();

    public static ArrayList<Spawner> getSpawnerInstances() { return instances; }


    private static final int SPAWN_LIMIT = 12;

    private static ArrayList<Creature> allSpawn = new ArrayList<>();

    private Creature creatureToSpawn;

    private long spawnInterval;
    private long lastSpawnTime = 0;


    public Spawner(String name, double x, double y, int width, int height, World world, Color color, long spawnInterval, Creature creatureToSpawn) {
        super(name, x, y, width, height, world, color);
        Spawner.instances.add(this);

        this.creatureToSpawn = creatureToSpawn;
        this.spawnInterval = spawnInterval;
    }


    public Spawner(String name, double x, double y, double scale, World world,  String imageName, long spawnInterval, Creature creatureToSpawn) {
        super(name, x, y, scale, world, imageName);
        Spawner.instances.add(this);

        this.creatureToSpawn = creatureToSpawn;
        this.spawnInterval = spawnInterval;
    }



    @Override
    public void act() {
        // Spawn a new clone of the Creature passed as a parameter if spawn interval passes and spawn limit has not been reached.
        if (getCurrentTime() - lastSpawnTime > spawnInterval && allSpawn.size() < SPAWN_LIMIT) {
            Creature spawn = new Creature(creatureToSpawn, x, y, world);
            allSpawn.add(spawn); // Add spawned entity to list to keep track of size
            lastSpawnTime = getCurrentTime();
        }

        // Clean dead spawn out from list so that more are allowed to spawn
        allSpawn.removeIf(entity -> !entity.isAlive());
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
    }


}


