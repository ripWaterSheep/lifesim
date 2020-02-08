package game.components.entities.stats;

import game.components.Component;
import game.components.entities.player.Player;
import game.components.structures.Structure;
import game.components.entities.Entity;

import java.util.ArrayList;

import static util.Geometry.testIntersection;

public class CollisionChecker {

    private static ArrayList<Component> getTouchingComponents(Component componentA) {
        ArrayList<Component> allComponents = new ArrayList<>(componentA.getWorld().getComponents());
        allComponents.add(Player.getInstance());
        ArrayList<Component> touching = new ArrayList<>();

        for (Component componentB: allComponents) {
            // If two different game.components are touching each other, place in the list.
            if (testIntersection(componentA, componentB)) {
                touching.add(componentB);
            }
        }
        touching.remove(componentA);
        return touching;
    }


    public static Structure getTopStructureTouching(Component componentA) {
        Structure touching = null;
        for (Component componentB: getTouchingComponents(componentA)) {
            if (componentB instanceof Structure)
                touching = (Structure) componentB;
        }
        return touching;
    }



    public static ArrayList<Entity> getTouchingEntities(Component componentA) {
        ArrayList<Entity> touching = new ArrayList<>();
        for (Component componentB: getTouchingComponents(componentA)) {
            if (componentB instanceof Entity)
            touching.add((Entity) componentB);
        }
        return touching;
    }



}
