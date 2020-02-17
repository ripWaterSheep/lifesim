package game.setting.layout;

import game.ECS.entities.Structure;
import game.setting.world.World;

import java.awt.*;

import static game.setting.world.BorderTypes.*;


public class DefaultLayout extends Layout {


    public DefaultLayout() {
        createWorld(new World("Town", 5000, 5000, new Color(56, 150, 86), new Color(201, 193, 126), LAVA_ISLAND)
            .add(new Structure("Hi", 0, 0, 100, 100, false, new Color(100, 100, 100))));
    }
}
