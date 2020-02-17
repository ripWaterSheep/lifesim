package game.ECS.entities;

import game.ECS.components.*;

import java.awt.*;

public final class Creature extends Entity {


    public Creature(String name, double x, double y, double width, double height, boolean elliptical, Color color,
                    double speed, PathFindingComponent.Behaviors pathFindingBehavior, double health, double damage) {
        super(name);

        add(new PositionComponent(x, y));
        add(new SpatialComponent(width, height, elliptical));
        add(new AppearanceComponent(color));
        add(new MovementComponent(speed));
        add(new PathFindingComponent(pathFindingBehavior));
        add(new HealthComponent(health));
        add(new AttackComponent(damage, false));
    }

}
