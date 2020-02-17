package game.ECS.entities;

import game.ECS.components.Appearance;
import game.ECS.components.Position;
import game.ECS.components.Spatial;

import java.awt.*;

public class Structure extends Entity{

    public Structure(String name, double x, double y, double width, double height, boolean elliptical, Color color) {
        super(name);
        add(new Position(x, y));
        add(new Spatial(width, height, elliptical));
        add(new Appearance(color));
    }

}
