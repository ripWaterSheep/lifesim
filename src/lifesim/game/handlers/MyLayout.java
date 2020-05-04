package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.EnemyType;
import lifesim.game.entities.types.ResourceTypes;
import lifesim.state.Game;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.ShapeSprite;

import java.awt.*;


public class MyLayout extends Layout {

    public MyLayout(Game game) {
        super(game);
    }

    @Override
    public void init() {
        worlds.add(
            new World("Town", 2000, 2000, new Color(60, 175, 90), new Color(200, 190, 125))
                .add(new Entity("vRoad", new ShapeSprite(100, 2000, Color.DARK_GRAY)), 0, 0)
                .add(new Entity("hRoad", new ShapeSprite(2000, 100, Color.DARK_GRAY)), 0, 0)

                .add(new Entity("House", new ShapeSprite(200, 175, new Color(100, 80, 50))) {
                    @Override
                    public void interact(Game game, Player player, PlayerStats stats) {
                        player.goTo("Home Door");
                    }
                }, 250, -200)

                .add(new Entity("School", new ShapeSprite(150, 200, new Color(179, 96, 71))) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        stats.gainIntellect(0.25);
                        stats.tire(0.1);
                    }
                },250, 250)

                .add(new Entity("Office", new ShapeSprite(200, 200, new Color(150, 150, 160))) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        stats.gainMoney(stats.getIntellect()/200);
                        stats.tire(0.1);
                    }
                }, -250, 250)

                .add(new Entity("Gym", new ImageSprite("gym")) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.attemptToPay(0.75)) {
                            stats.strengthen(0.5);
                            stats.tire(0.5);
                        }
                    }
                }, -600, -200)

                .add(new Entity("Restaurant", new ShapeSprite(200, 200, new Color(255, 215, 125))) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.attemptToPay(0.5)) {
                            stats.energize(0.75);
                        }
                    }
                }, -250, -225)

                .add(new Entity("Hospital", new ShapeSprite(200, 200, new Color(210, 210, 210))) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.attemptToPay(0.75)) {
                            if (stats.getHealth() < 1000) stats.heal(1);
                        }
                    }
                }, -250, -550)

                .add(new Entity("Shop", new ShapeSprite(250, 200, new Color(200, 110, 75))), -675, 250)
                .add(new Entity("Cave", new ShapeSprite(200, 75, Color.LIGHT_GRAY)), -800, -800)

                .addSpawner(new SpawningSystem(EnemyType.MELEEBORG, 2500))

                .addSpawner(new BalancedSpawningSystem(ResourceTypes.ITEM_PACKAGE, 0, 4))
        );

        worlds.add(new World("Home", 300, 225, new Color(230, 210, 140), new Color(100, 80, 50))
                .add(new Entity("Home Door", new ShapeSprite(2, 30, new Color(167, 155, 81))) {
                    @Override
                    public void interact(Game game, Player player, PlayerStats stats) {
                        player.goTo("House");
                    }
                }, 150, 0)

        );


        worlds.add(new World("City", 200, 200, new Color(180, 180, 180), new Color(100, 205, 131))
                .addSpawner(new BalancedSpawningSystem(ResourceTypes.ITEM_PACKAGE, 5000, 1))
        );

    }

}
