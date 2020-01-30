package game.organization;

import game.Subsystem;
import game.components.Component;
import game.components.entities.Entity;

import java.util.ArrayList;

public class EntityManager implements Subsystem {

    protected World world;

    public EntityManager(World world) {
        this.world = world;
    }


    private void deleteNonexistentEntities() {
        ArrayList<Component> components = world.getComponents();
        for (Component component: components) {
            if (component instanceof Entity) {
                Entity entity = (Entity) component;
                if (!entity.doesExist()) {
                    world.remove(entity);
                }
            }
        }
    }


    @Override
    public void run() {
        deleteNonexistentEntities();
    }



}
