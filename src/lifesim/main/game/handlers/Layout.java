package lifesim.main.game.handlers;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Alliance;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.DamageStats;
import lifesim.main.game.entities.components.stats.HealthStats;
import lifesim.main.game.entities.enemies.Enemy;

import java.awt.*;
import java.util.ArrayList;


public class Layout {

    private ArrayList<World> worlds = new ArrayList<>();

    public ArrayList<World> getWorlds() {
        return worlds;
    }

    public Layout() {
        worlds.add(
                new World("Town", new Vector2D(1500, 1500), new Color(60, 159, 75), new Color(201, 193, 126))
                        .add(new Entity("vRoad", new Sprite(new Vector2D(50, 1500), Color.DARK_GRAY, false)), new Vector2D(0, 0))
                        .add(new Entity("hRoad", new Sprite(new Vector2D(1500, 50), Color.DARK_GRAY, false)), new Vector2D(0, 0))
                        .add(new Enemy("yee", new AnimatedSprite(new Animation(120, "emo_1", "emo_2", "emo_3", "emo_4")),
                                new HealthStats(8,1000, Alliance.ENEMY), 1.8, 100),  new Vector2D(150, 150))
        );
    }

}
