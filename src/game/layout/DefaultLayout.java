package game.layout;

import game.entities.StructureEntity;
import game.world.BorderTypes;
import game.world.World;

import java.awt.*;

import static game.world.BorderTypes.*;


public class DefaultLayout extends Layout {


    public DefaultLayout() {
        createWorld(new World("Town", 5000, 5000, new Color(56, 150, 86), new Color(201, 193, 126), LAVA_ISLAND)
            .add(new StructureEntity("Hi", 0, 0, 100, 100, false, new Color(100, 100, 100))));
    }
}
