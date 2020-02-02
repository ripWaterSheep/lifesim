package game.components.structures;

import game.components.entities.Creature;
import game.components.entities.Entity;
import game.components.entities.RangedCreature;

import java.awt.*;
import java.util.ArrayList;

import static util.TimeUtil.getCurrentTime;

public class Spawner extends Structure {


    private static final int SPAWN_LIMIT = 8;

    private static ArrayList<Entity> allSpawn = new ArrayList<>();


    private Creature spawnTemplate;

    private final long spawnInterval;
    private long lastSpawnTime = 0;


    public Spawner(String name, double x, double y, double width, double height, boolean elliptical, Color color, long spawnInterval, Creature spawnTemplate) {
        super(name, x, y, width, height, elliptical, color);
        this.spawnTemplate = spawnTemplate;
        this.spawnInterval = spawnInterval;
    }


    public Spawner(String name, double x, double y, double width, double height, boolean elliptical, Color color, int fontSize, long spawnInterval, Creature spawnTemplate) {
        super(name, x, y, width, height, elliptical, color, fontSize);
        this.spawnTemplate = spawnTemplate;
        this.spawnInterval = spawnInterval;
    }


    private void spawnLogic() {
        // Spawn a new clone of the Creature passed as a parameter if spawn interval passes and spawn limit has not been reached
        if (getCurrentTime() - lastSpawnTime > spawnInterval && allSpawn.size() < SPAWN_LIMIT) {
            Creature spawn;
            if (spawnTemplate instanceof RangedCreature) {
                spawn = new RangedCreature((RangedCreature)spawnTemplate, x, y, world);
            } else {
                spawn = new Creature(spawnTemplate, x, y, world);
            }

            allSpawn.add(spawn); // Add spawned entity to list to keep track of size
            lastSpawnTime = getCurrentTime();

        }
    }


    @Override
    public void update() {
        super.update();
        spawnLogic();
    }
}