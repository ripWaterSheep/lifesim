package game.organization.components.activity.management.collision;

import game.organization.components.Component;
import game.organization.World;
import game.organization.components.activity.management.WorldManager;
import game.organization.components.structures.Structure;
import game.organization.components.entities.Entity;

import java.util.ArrayList;

import static util.Geometry.testIntersection;

public class CollisionChecker extends WorldManager{


    CollisionChecker(World world) {
        super(world);
    }


    protected ArrayList<Component> getTouchingComponents(Component componentA) {
        ArrayList<Component> touching = new ArrayList<>();
        for (Component componentB: world.getComponents()) {
            if (testIntersection(componentA, componentB)) {
                touching.add(componentB);
            }
        }
        return touching;
    }


    protected boolean isTouchingAnything(Component component) {
        return !getTouchingComponents(component).isEmpty();
    }


    protected Structure getTopStructureTouching(Component componentA) {
        Structure touching = null;
        for (Component componentB: getTouchingComponents(componentA)) {
            if (componentB instanceof Structure)
                touching = (Structure) componentB;
        }
        return touching;
    }



    protected ArrayList<Entity> getTouchingEntities(Component componentA) {
        ArrayList<Entity> touching = new ArrayList<>();
        for (Component componentB: getTouchingComponents(componentA)) {
            if (componentB instanceof Entity)
            touching.add((Entity) componentB);
        }
        return touching;
    }



}
