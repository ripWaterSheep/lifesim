package game.organization;

import game.entities.Entity;
import game.entities.StructureEntity;
import game.world.World;

import java.awt.*;

public class DefaultLayout extends Layout {


    public DefaultLayout() {
        createWorld(new World("Town", 5000, 5000, new Color(56, 150, 86), new Color(201, 193, 126))
            .add(new StructureEntity("Hi", 0, 0, 100, 100, false, new Color(100, 100, 100))));
    }
}
