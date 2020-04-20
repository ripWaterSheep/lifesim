package lifesim.main.game.handlers;

import lifesim.main.game.Game;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.SolidEntity;
import lifesim.main.game.entities.components.sprites.ImageSprite;
import lifesim.main.game.entities.components.sprites.ShapeSprite;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;

import java.awt.*;
import java.util.ArrayList;

import static lifesim.main.game.entities.types.EnemyType.MELEE_1;
import static lifesim.main.game.entities.types.EnemyType.RANGED_1;


public class Layout {

    private final ArrayList<World> worlds = new ArrayList<>();

    public ArrayList<World> getWorlds() {
        return worlds;
    }

    public Layout() {
        worlds.add(
            new World("Town", 2250, 2250, new Color(60, 160, 75), new Color(200, 190, 125))
                .add(new Entity("vRoad", new ShapeSprite(75, 2250, Color.DARK_GRAY)), 0, 0)
                .add(new Entity("hRoad", new ShapeSprite(2250, 75, Color.DARK_GRAY)), 0, 0)
                .add(new Entity("House", new ShapeSprite(200, 175, new Color(100, 80, 50))) {
                    @Override
                    public void eventOnClick(Game game, Player player, PlayerStats stats) {
                        player.goTo("Home Door");
                    }
                }, 250, -200)
                .add(new Entity("School", new ShapeSprite(150, 200, new Color(179, 96, 71))) {
                    @Override
                    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {
                        stats.gainIntellect(0.25);
                        stats.tire(0.1);
                    }
                },250, 250)
                .add(new Entity("Office", new ShapeSprite(200, 200, new Color(150, 150, 160))) {
                    @Override
                    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {
                        stats.gainMoney(stats.getIntellect()/250);
                        stats.tire(0.1);
                    }
                }, -250, 250)
                .add(new Entity("Gym", new ImageSprite("gym")) {
                    @Override
                    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {
                        if (stats.attemptToPay(0.75)) {
                            stats.strengthen(0.75);
                            stats.tire(0.5);
                        }
                    }
                }, -600, -200)
                .add(new Entity("Restaurant", new ShapeSprite(200, 200, new Color(255, 215, 125))) {
                    @Override
                    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {
                        if (stats.attemptToPay(0.5)) {
                            stats.energize(0.25);
                        }
                    }
                }, -250, -225)
                .add(new SolidEntity("Hospital", new ShapeSprite(200, 200, new Color(210, 210, 210))) {
                    @Override
                    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {
                        if (stats.attemptToPay(0.25)) {
                            if (stats.getHealth() < 1000) stats.heal(0.5);
                        }
                    }
                }, -250, -550)
                .add(new Entity("Shop", new ShapeSprite(250, 200, new Color(200, 110, 75))), -675, 250)
                .add(new Entity("Cave", new ShapeSprite(200, 75, Color.LIGHT_GRAY)), -800, -800)

                .addSpawner(new SpawningSystem(MELEE_1, 4000))

                .addSpawner(new SpawningSystem(RANGED_1, 5000))
        );


        worlds.add(new World("Home", 300, 225, new Color(230, 210, 140), new Color(100, 80, 50))
            .add(new Entity("Home Door", new ShapeSprite(2, 30, new Color(167, 155, 81))) {
                @Override
                public void eventOnClick(Game game, Player player, PlayerStats stats) {
                    player.goTo("House");
                }
            }, 150, 0)

        );


        worlds.add(new World("City", 3000, 3000, new Color(180, 180, 180), new Color(100, 205, 131))

        );

    }

}
