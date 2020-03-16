package lifesim.main.game.setting;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.TempEntity;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.DamageStats;

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
                        .add(new Entity("vRoad", new Sprite(new Vector2D(50, 750), Color.DARK_GRAY, false)), new Vector2D(0, 0))
                        .add(new Entity("hRoad", new Sprite(new Vector2D(750, 50), Color.DARK_GRAY, false)), new Vector2D(0, 0))
                        .add(new Entity("Step on me!", new Sprite(new Vector2D(20, 20), Color.BLUE, true), new DamageStats(100, false)), new Vector2D(200, 200))
                        .add(new TempEntity("bomb", new AnimatedSprite(new Animation(80, "bomb_1", "bomb_2", "bomb_3", "bomb_4", "bomb_5", "bomb_6"))), new Vector2D(0, 0))
                        );
    }

}
