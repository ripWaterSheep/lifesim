package game.activity.collision;

import game.components.GameComponent;
import game.components.entities.Entity;
import game.components.structures.Structure;

import java.util.ArrayList;

import static util.Geometry.testIntersection;

public class CollisionCheckers {


    static ArrayList<Structure> getTouchingStructures(GameComponent component) {
        ArrayList<Structure> touching = new ArrayList<>();
        for (Structure structure: Structure.getStructureInstances()) {
            if (testIntersection(component, structure) && component.getWorld() == structure.getWorld()) {
                touching.add(structure);
            }
        }
        return touching;
    }


    static boolean isTouchingAnyStructures(GameComponent component) { return !getTouchingStructures(component).isEmpty(); }


    static Structure getTopStructureTouching(GameComponent component) {
        Structure topTouching = null;
        if (isTouchingAnyStructures(component))
            topTouching = getTouchingStructures(component).get(0);//(getTouchingStructures().size()-1);

        return topTouching;
    }


    static ArrayList<Entity> getTouchingEntities(GameComponent component) {
        ArrayList<Entity> touching = new ArrayList<>();
        for (Entity entity: Entity.getEntityInstances()) {
            if (testIntersection(component, entity) && !(entity == component) && component.getWorld() == entity.getWorld()) {
                touching.add(entity);
            }
        }
        return touching;
    }



}
