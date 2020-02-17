package game.ECS.entities;

import game.ECS.components.*;

import java.awt.*;


public class Projectile extends Entity{

    public Projectile(String name, double x, double y, double width, double height, boolean elliptical, Color color,
                      double speed, double angle, double damage) {
        super(name);

        add(new PositionComponent(x, y));
        add(new SpatialComponent(width, height, elliptical));
        add(new AppearanceComponent(color));
        add(new MovementComponent(speed, angle));
        add(new AttackComponent(damage, true));
    }


}
