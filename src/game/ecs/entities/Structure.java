package game.ecs.entities;

import game.ecs.components.AppearanceComponent;
import game.ecs.components.PositionComponent;
import game.ecs.components.SpatialComponent;

import java.awt.*;

public class Structure extends Entity {

    public Structure(String name, double x, double y, double width, double height, boolean elliptical, Color color) {
        super(name);
        add(new PositionComponent(x, y));
        add(new SpatialComponent(width, height, elliptical));
        add(new AppearanceComponent(color));
    }

}
