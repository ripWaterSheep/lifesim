package game.layout;

import game.world.World;

import java.util.ArrayList;

public abstract class Layout {

    private ArrayList<World> worlds = new ArrayList<>();

    public ArrayList<World> getWorlds() {
        return worlds;
    }


    protected void createWorld(World world) {
        worlds.add(world);
    }


}
