package game.entities;

import game.components.Appearance;
import game.components.Label;
import game.components.Movement;
import game.components.Position;
import game.components.Spatial;
import game.world.World;

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
