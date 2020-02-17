package game.ECS.entities;

import game.ECS.components.IComponent;
import game.ECS.components.PositionComponent;
import game.setting.world.World;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Entity {

    private ArrayList<IComponent> components = new ArrayList<>();

    public ArrayList<IComponent> getComponents() {
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



    public final Entity add(IComponent component) {
        System.out.println(component + "  " + getName());
        components.add(component);
        return this;
    }


    /** Copy all of the entities components to create a
     * pretty much identical entity in a different location
     */
    public final Entity copyAt(double x, double y, World world) {
        Entity newEntity = new Entity("Copy of " + name);

        for (IComponent component: components) {
            newEntity.add(component.copy());
        }

        world.add(newEntity);
        for (PositionComponent pos: newEntity.getAll(PositionComponent.class)) {
            pos.set(x, y);
        }
        return newEntity;
    }


    public final boolean has(Class<? extends IComponent> componentType) {
        boolean has = false;
        for (IComponent component: components) {
            if (component.getClass().equals(componentType)) {
                has = true;
                break;
            }
        }
        return has;
    }



    public final <T extends IComponent> T get(Class<T> componentType) {
        IComponent desiredComponent = null;
        for (IComponent component: components) {
            if (component.getClass().equals(componentType)) {
                desiredComponent = component;
            }
        }
        return (T) desiredComponent;
    }


    public <T extends IComponent> ArrayList<T> getAll(Class<T> filteringClass){
        return new ArrayList<>(
                components.stream()
                        .filter(t -> t.getClass().equals(filteringClass))
                        .map(t -> (T) t)
                        .collect(Collectors.toList())
        );
    }


}
