package lifesim.main.game.setting;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class Layout {

    private ArrayList<World> worlds = new ArrayList<>();

    public ArrayList<World> getWorlds() {
        return worlds;
    }

    public Layout() {
        worlds.add(
                new World("Town", new Vector2D(750, 750), new Color(60, 159, 75), new Color(201, 193, 126))
                        .add(new Entity("vRoad", new Sprite(new Vector2D(50, 750), new Color(41, 41, 41), false), new Vector2D(0, 0)))
                        .add(new Entity("hRoad", new Sprite(new Vector2D(750, 50), new Color(41, 41, 41), false), new Vector2D(0, 0)))
        );
    }

}
