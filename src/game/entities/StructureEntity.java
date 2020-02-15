package game.entities;

import game.components.Appearance;
import game.components.Position;
import game.components.Spatial;

import java.awt.*;

public class StructureEntity extends Entity{

    public StructureEntity(String name, double x, double y, double width, double height, boolean elliptical, Color color) {
        super(name);
        add(new Position(x, y));
        add(new Spatial(width, height, elliptical));
        add(new Appearance(color));
    }

}
