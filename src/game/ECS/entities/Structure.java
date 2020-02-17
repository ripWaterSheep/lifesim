package game.ECS.entities;

import game.ECS.components.AppearanceComponent;
import game.ECS.components.PositionComponent;
import game.ECS.components.SpatialComponent;

import java.awt.*;

public class Structure extends Entity {

    public Structure(String name, double x, double y, double width, double height, boolean elliptical, Color color) {
        super(name);
        add(new PositionComponent(x, y));
        add(new SpatialComponent(width, height, elliptical));
        add(new AppearanceComponent(color));
    }

}
