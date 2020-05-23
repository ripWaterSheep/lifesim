package lifesim.game.handlers;

import lifesim.game.entities.Entity;
import lifesim.game.entities.FlatEntity;
import lifesim.game.entities.Player;
import lifesim.game.entities.SolidEntity;
import lifesim.game.entities.stats.InanimateStats;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.EnemyType;
import lifesim.game.entities.types.PackageTypes;
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
            new World("Town", 2500, 2000, new Color(60, 175, 90), new Color(200, 190, 125))
                .add(new FlatEntity("vRoad", new ShapeSprite(100, 2000, Color.DARK_GRAY)), 0, 0)
                .add(new FlatEntity("hRoad", new ShapeSprite(2500, 100, Color.DARK_GRAY)), 0, 0)

                .add(new SolidEntity("House", new ImageSprite("house"), 125) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        game.displayMessage("Enter home?");
                    }
                    @Override
                    public void interact(Game game, Player player, PlayerStats stats) {
                        player.goToEntity("Home Door");
                    }
                }, 250, -200)

                .add(new SolidEntity("School", new ImageSprite("school"), 150) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        stats.gainIntellect(0.25);
                        stats.tire(0.05);
                    }
                },250, 250)

                .add(new SolidEntity("Office", new ImageSprite("office"), 150) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.getIntellect() > 0) {
                            stats.gainMoney(stats.getIntellect() / 200);
                            stats.tire(0.075);
                        }
                    }
                }, -250, 175)

                .add(new SolidEntity("Gym", new ImageSprite("gym"), 80) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.canAfford(0.75)) {
                            stats.strengthen(0.5);
                            stats.tire(0.25);
                            stats.loseMoney(0.75);
                        }
                    }
                }, -600, -200)

                .add(new SolidEntity("Restaurant", new ShapeSprite(200, 200, new Color(255, 215, 125)), 100) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.canAfford(0.5) && stats.getEnergy() < 100) {
                            stats.energize(0.5);
                            stats.loseMoney(0.5);
                        }
                    }
                }, -250, -225)

                .add(new SolidEntity("Hospital", new ShapeSprite(200, 400, new Color(210, 210, 210)), 200) {
                    @Override
                    public void playerCollision(Game game, Player player, PlayerStats stats) {
                        if (stats.canAfford(0.75) && stats.getHealth() < 100) {
                            stats.heal(0.5);
                            stats.loseMoney(0.75);
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
                        player.goToEntity("Shop Door");
                    }
                }, -675, 200)
                .add(new SolidEntity("Cave", new ShapeSprite(200, 75, new Color(150, 150, 150)), new InanimateStats(),45) {
                    @Override
                    public void interact(Game game, Player player, PlayerStats stats) {
                        player.goToEntity("Cave Entrance");
                    }
                }, -800, -800)

                .addSpawningSystem(new SpawningSystem(EnemyType.MELEE, 3000))
                .addSpawningSystem(new SpawningSystem(EnemyType.RANGED, 4500))

                .addSpawningSystem(new SpawningSystem(PackageTypes.ITEM_PACKAGE, 1000))
                .addSpawningSystem(new SpawningSystem(PackageTypes.REINFORCED_PACKAGE, 10000))
        );

        worlds.add(new World("Home", 300, 225, new Color(230, 210, 140), new Color(118, 175, 204))
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

        worlds.add(new World("Cave Interior", 3000, 150, new Color(75, 75, 75), new Color(25, 25, 25))
            .add(new FlatEntity("Cave Entrance", new ShapeSprite(20, 150, Color.BLACK)) {
                @Override
                public void interact(Game game, Player player, PlayerStats stats) {
                    player.goToEntity("Cave");
                }
            }, -1500, 0)
            .addSpawningSystem(new SpawningSystem(EnemyType.CUBORG, 2500))
        );


        worlds.add(new World("City", 300, 300, new Color(180, 180, 180), new Color(100, 205, 131))
                .addSpawningSystem(new SpawningSystem(PackageTypes.ITEM_PACKAGE, 1000))
        );

    }

}
