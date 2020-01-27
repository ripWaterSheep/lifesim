package game.organization.components.activity.management;

import game.organization.World;
import game.organization.components.activity.Subsystem;
import game.organization.components.entities.Entity;

import java.util.ArrayList;

public class EntityManager extends WorldManager implements Subsystem {

    public EntityManager(World world) {
        super(world);
    }


    private void deleteNonexistentEntities() {
        ArrayList<Entity> entities = world.getEntities();
        for (Entity entity: entities) {
            if (!entity.doesExist()) {
                world.getComponents().remove(entity);
            }
        }
    }


    @Override
    public void run() {
        deleteNonexistentEntities();
    }



}
