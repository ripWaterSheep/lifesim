package lifesim.main.game.setting;

import lifesim.main.util.math.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class Layout {

    private ArrayList<World> worlds = new ArrayList<>();

    public ArrayList<World> getWorlds() {
        return worlds;
    }

    public Layout() {
        worlds.add(
                new World("Town", new Vector2D(100, 1000), new Color(0, 255, 100))
        );
    }

}
