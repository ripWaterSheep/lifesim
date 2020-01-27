package util;

import game.organization.components.Component;
import game.organization.World;
import game.organization.components.entities.Entity;
import game.organization.components.structures.Structure;

public class FindComponent {


    public static World findStructureWorld(Structure structure) {
        for (World world: World.getWorlds()) {
            if (world.getComponents().contains(structure)) {
                return world;
            }
        }
        throw new IllegalArgumentException("NullPointerException: Structure" + structure.getName()+ "'s world has not been assigned!");
    }


    public static Component findComponent(String name) {
        for (World world: World.getWorlds()) {
            for (Component component : world.getComponents()) {
                if (component.getName().equals(name)) return component;
            }
        }
        throw new IllegalArgumentException
                ("NullPointerException: No component by the name of " + name + " exists!");
    }


    public static Structure findStructure(String name) {
        Component foundComponent = findComponent(name);
        if (foundComponent instanceof Structure) {
            return (Structure) foundComponent;
        } else throw new IllegalArgumentException
                ("NullPointerException: No structure by the name of " + name + " exists!");
    }


    public static Entity findEntity(String name) {
        Component foundComponent = findComponent(name);
        if (foundComponent instanceof Entity) {
            return (Entity) foundComponent;
        } else throw new IllegalArgumentException
                ("NullPointerException: No entity by the name of " + name + " exists!");
    }



}
