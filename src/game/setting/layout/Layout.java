package game.setting.layout;

import game.ecs.entities.Entity;
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
        for (World world : worlds) {
            foundWorld = world;
        }

        return foundWorld;
    }


    public Layout copyCurrentState() {
        Layout newLayout = new DefaultLayout();
        for (World world : worlds) {
            newLayout.createWorld(world.copyCurrentState());
        }
        return newLayout;
    }



}
