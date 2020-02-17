package game.ECS.entities;

import game.ECS.components.*;

import java.awt.*;

public final class Creature extends Entity {


    public Creature(String name, double x, double y, double width, double height, boolean elliptical, Color color,
                    double speed, PathFinding.Behaviors pathFindingBehavior, double health, double damage) {
        super(name);

        add(new Position(x, y));
        add(new Spatial(width, height, elliptical));
        add(new Appearance(color));
        add(new Movement(speed));
        add(new PathFinding(pathFindingBehavior));
        add(new Health(health));
        add(new Attack(damage, false));
    }

}
