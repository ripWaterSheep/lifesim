package game.organization.components.activity.management;

import game.organization.World;
import game.organization.components.activity.Subsystem;


public abstract class WorldManager {

    protected World world;

    public WorldManager(World world) {
        this.world = world;
    }

}
