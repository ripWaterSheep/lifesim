package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.FlatEntity;
import lifesim.game.entities.Player;
import lifesim.game.entities.SolidEntity;
import lifesim.game.entities.stats.InanimateStats;
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
            new World("Town", 2500, 2500, new Color(60, 175, 90), new Color(200, 190, 125))
                .add(new FlatEntity("vRoad", new ShapeSprite(100, 2500, Color.DARK_GRAY)), 0, 0)
                .add(new FlatEntity("hRoad", new ShapeSprite(2500, 100, Color.DARK_GRAY)), 0, 0)

                .add(new SolidEntity("House", new ShapeSprite(200, 175, new Color(100, 80, 50)), 125) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        game.displayMessage("Enter home?");
                    }
                    @Override
                    public void interact(Game game, Player player, PlayerStats stats) {
                        player.goToEntity("Home Door");
                    }
                }, 250, -200)

                .add(new SolidEntity("School", new ShapeSprite(150, 200, new Color(179, 96, 71)), 150) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        stats.gainIntellect(0.25);
                        stats.tire(0.1);
                    }
                },250, 225)

                .add(new SolidEntity("Office", new ImageSprite("office"), 150) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        stats.gainMoney(stats.getIntellect()/200);
                        stats.tire(0.1);
                    }
                }, -250, 175)

                .add(new SolidEntity("Gym", new ImageSprite("gym"), 80) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.attemptToPay(0.75)) {
                            stats.strengthen(0.5);
                            stats.tire(0.5);
                        }
                    }
                }, -600, -200)

                .add(new SolidEntity("Restaurant", new ShapeSprite(200, 200, new Color(255, 215, 125)), 100) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.attemptToPay(0.5)) {
                            stats.energize(0.75);
                        }
                    }
                }, -250, -225)

                .add(new SolidEntity("Hospital", new ShapeSprite(200, 400, new Color(210, 210, 210)), 200) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.attemptToPay(0.75)) {
                            if (stats.getHealth() < 1000) stats.heal(1);
                        }
                    }
                }, -250, -650)

                .add(new SolidEntity("Shop", new ShapeSprite(250, 200, new Color(200, 110, 75)), 150) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        game.displayMessage("Enter shop?");
                    }
                    @Override
                    public void interact(Game game, Player player, PlayerStats stats) {
                        player.goToWorld("Shop Interior");
                    }
                }, -675, 200)
                .add(new SolidEntity("Cave", new ShapeSprite(200, 75, Color.LIGHT_GRAY), new InanimateStats(), 45), -800, -800)

                .addSpawner(new SpawningSystem(EnemyType.MELEEBORG, 2500))
                .addSpawner(new SpawningSystem(EnemyType.RANGED, 3500))

                .addSpawner(new SpawningSystem(ResourceTypes.ITEM_PACKAGE, 100))
        );

        worlds.add(new World("Home", 300, 225, new Color(230, 210, 140), new Color(100, 80, 50))
                .add(new Entity("Home Door", new ShapeSprite(2, 30, new Color(167, 155, 81))) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        game.displayMessage("Leave home?");
                    }
                    @Override
                    public void interact(Game game, Player player, PlayerStats stats) {
                        player.goToEntity("House");
                    }
                }, 150, 0)

        );

        worlds.add(new World("Shop Interior", 500, 400, new Color(190, 183, 121), new Color(200, 110, 75))
                .add(new Entity("Shop Door", new ShapeSprite(40, 3, new Color(110, 102, 58))) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        game.displayMessage("Leave Shop?");
                    }
                    @Override
                    public void interact(Game game, Player player, PlayerStats stats) {
                        player.goToEntity("Shop");
                    }
                }, 0, 200)
        );


        worlds.add(new World("City", 300, 300, new Color(180, 180, 180), new Color(100, 205, 131))
                .addSpawner(new SpawningSystem(ResourceTypes.ITEM_PACKAGE, 1000))
        );

    }

}
