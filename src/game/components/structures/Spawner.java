package game.components.structures;

import game.components.GameComponent;
import game.components.World;
import game.components.entities.MobileEntity;
import game.components.entities.player.Player;
import util.DrawString;
import util.MyFont;
import util.TimeUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static util.TimeUtil.getCurrentTime;


public class Spawner extends Structure {


    private static ArrayList<Spawner> instances = new ArrayList<>();

    public static ArrayList<Spawner> getSubtypeInstances() { return instances; }

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
            spawnable.cloneAt(x, y, world);;
            allSpawned.add(spawnable); // Add spawned entity to list to keep track of size
            lastSpawnTime = getCurrentTime();
        }
    }


}


