package game.entities;

import game.components.IComponent;
import game.components.StatInteraction;
import game.world.World;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class Entity {

    private ArrayList<IComponent> components = new ArrayList<>();

    public ArrayList<IComponent> getComponents() {
        return components;
    }


    private World world;

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


    public final Entity add(IComponent component) {
        components.add(component);
        return this;
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
