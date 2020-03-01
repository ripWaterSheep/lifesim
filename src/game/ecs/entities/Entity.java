package game.ecs.entities;

import game.ecs.components.CopyableComponent;
import game.ecs.components.PositionComponent;
import game.setting.World;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Entity {

    private ArrayList<CopyableComponent> components = new ArrayList<>();

    public ArrayList<CopyableComponent> getComponents() {
        return components;
    }


    private String name;
    public String getName() {
        return name;
    }


    public Entity(String name) {
        this.name = name;
    }


    public Entity add(CopyableComponent component) {
        components.add(component);
        return this;
    }


    public final <T extends CopyableComponent> T get(Class<T> componentType) {
        CopyableComponent desiredComponent = null;
        for (CopyableComponent component: components) {
            if (component.getClass().equals(componentType)) {
                desiredComponent = component;
            }
        }
        return (T) desiredComponent;
    }


    public <T extends CopyableComponent> ArrayList<T> getAll(Class<T> filteringClass) {
        ArrayList<T> desiredComponents = new ArrayList<>();
        for (CopyableComponent component: components) {
            if (filteringClass.isInstance(component)) {
                desiredComponents.add((T) component);
            }
        }
        return desiredComponents;
    }


    public PositionComponent getPos() {
        PositionComponent pos = get(PositionComponent.class);
        if (pos == null)
            return new PositionComponent(0, 0);
        return pos;
    }


    public Entity copyInitialState() {
        Entity newEntity = new Entity("Copy of template Entity " + name);

        for (CopyableComponent component: components) {
            newEntity.add(component.copyInitialState());
        }

        return newEntity;
    }



}
