package game.ECS.entities;

import game.ECS.components.Appearance;
import game.ECS.components.Movement;
import game.ECS.components.Position;
import game.ECS.components.Spatial;
import game.setting.world.World;

import java.awt.*;

public final class Player extends Entity {


    private static Player instance;

    public static Player getInstance() {
        return instance;
    }

    @Override
    public void setWorld(World newWorld) {
        world = newWorld;
    }


    public Player() {
        super("Player");
        instance = this;

        add(new Position(0, 0));
        add(new Spatial(50, 50, true));
        add(new Appearance(Color.YELLOW));
        add(new Movement(12));
    }

}
