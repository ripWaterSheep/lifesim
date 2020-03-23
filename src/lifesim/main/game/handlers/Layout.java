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
            new World("Town", 2500, 2500, new Color(60, 159, 75), new Color(201, 193, 126))
                .add(new Entity("vRoad", new Sprite(75, 2500, Color.DARK_GRAY)), 0, 0)
                .add(new Entity("hRoad", new Sprite(2500, 75, Color.DARK_GRAY)), 0, 0)
                .add(new Entity("House", new Sprite(200, 175, new Color(100, 80, 50))), 250, -200)
                .add(new Entity("School", new Sprite(150, 200, new Color(180, 115, 85))),250, 300)
                .add(new Entity("Office", new Sprite(200, 200, new Color(150, 150, 160))), -250, 250)
                .add(new Entity("Gym", new Sprite("gym")), -600, -200)
                .add(new Entity("Restaurant", new Sprite(200, 200, new Color(255, 215, 125))), -250, -225)
                .add(new Entity("Hospital", new Sprite(200, 200, new Color(210, 210, 210))), -250, -550)
                .add(new Entity("Shop", new Sprite(250, 200, new Color(200, 110, 75))), -675, 250)
                .add(new Entity("Cave", new Sprite(200, 75, Color.GRAY)), -1000, -1000)
                .addSpawner(new Spawner(new Enemy("yee", new AnimatedSprite(new Animation(120, "emo_1", "emo_2", "emo_3", "emo_4")),
                        new HealthStats(6,25, Alliance.ENEMY), 2, 100), 2000))
        );
    }

}
