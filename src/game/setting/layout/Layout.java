package game.setting.layout;

import game.ECS.entities.Entity;
import game.setting.world.World;

import java.util.ArrayList;

public abstract class Layout {

    private ArrayList<World> worlds = new ArrayList<>();

    public ArrayList<World> getWorlds() {
        return worlds;
    }


    protected void createWorld(World world) {
        worlds.add(world);
    }


    protected World getWorld(String worldName) {
        World foundWorld = null;
        for (World world: worlds) {
            foundWorld = world;
        }

        return foundWorld;
    }


    protected Entity getEntity(String entityName) {
        Entity desiredEntity = null;
        for (World world: worlds) {
            if (world.getEntityWithName(entityName) != null) {
                desiredEntity = world.getEntityWithName(entityName);
                break;
            }
        }

        return desiredEntity;
    }

}
