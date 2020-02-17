package game.ECS.entities;

import game.ECS.components.*;

import java.awt.*;


public class Projectile extends Entity{

    public Projectile(String name, double x, double y, double width, double height, boolean elliptical, Color color,
                      double speed, double angle, double damage) {
        super(name);

        add(new Position(x, y));
        add(new Spatial(width, height, elliptical));
        add(new Appearance(color));
        add(new Movement(speed, angle));
        add(new Attack(damage, true));
    }


}
