package game.ECS.entities;

import game.ECS.components.Copyable;
import game.ECS.components.PositionComponent;
import game.setting.world.World;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Entity implements Copyable {

    private ArrayList<Copyable> components = new ArrayList<>();

    public ArrayList<Copyable> getComponents() {
        return components;
    }


    protected World world;

    public World getWorld() {
        return world;
    }


    public void setWorld(World world) {
        if (this.world == null)
            this.world = world;
    }


    private String name;
    public String getName() {
        return name;
    }


    public Entity(String name) {
        this.name = name;
    }


    public void delete() {
        world.remove(this);
    }


    public final Entity add(Copyable component) {
        components.add(component);
        return this;
    }


    public final <T extends Copyable> T get(Class<T> componentType) {
        Copyable desiredComponent = null;
        for (Copyable component: components) {
            if (component.getClass().equals(componentType)) {
                desiredComponent = component;
            }
        }
        return (T) desiredComponent;
    }


    public <T extends Copyable> ArrayList<T> getAll(Class<T> filteringClass) {
        return new ArrayList<>(
                components.stream()
                        .filter(t -> t.getClass().isAssignableFrom(filteringClass))
                        .map(t -> (T) t)
                        .collect(Collectors.toList())
        );
    }


    @Override
    public Entity copyInitialState() {
        Entity newEntity = new Entity("Copy of template Entity " + name);

        for (Copyable component: components) {
            newEntity.add(component.copyInitialState());
        }

        world.add(newEntity);
        return newEntity;
    }


    @Override
    public Entity copyCurrentState() {
        Entity newEntity = new Entity("Copy of " + name);

        for (Copyable component: components) {
            newEntity.add(component.copyCurrentState());
        }

        world.add(newEntity);
        return newEntity;
    }




}
