package util;

import game.components.World;
import game.components.entities.Entity;
import game.components.structures.Structure;

public class FindComponent {


    public static World findWorld(String name) {
        World foundWorld = null;
        for (World world: World.getWorldInstances()) {
            if (world.getName().equals(name)) {
                foundWorld = world;
            }
        }
        if (foundWorld == null) throw new IllegalArgumentException();

        return foundWorld;
    }


    public static Structure findStructure(String name) {
        Structure foundStructure = null;
        for (Structure structure: Structure.getStructureInstances()) {
            if (structure.getName().equals(name)) {
                foundStructure = structure;
            }
        }
        if (foundStructure == null) throw new IllegalArgumentException();

        return foundStructure;
    }


    public static Entity findEntity(String name) {
        Entity foundEntity = null;
        for (Entity entity: Entity.getEntityInstances()) {
            if (entity.getName().equals(name)) {
                foundEntity = entity;
            }
        }
        if (foundEntity == null) throw new IllegalArgumentException();

        return foundEntity;
    }



}
