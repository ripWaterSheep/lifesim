package lifesim.main.game.handlers;

import lifesim.main.game.entities.*;
import lifesim.main.game.entities.components.stats.Alliance;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.AnimatedSprite;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.HealthStats;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.stats.BasicStats;

import java.awt.*;
import java.util.ArrayList;


public class Layout {

    private final ArrayList<World> worlds = new ArrayList<>();

    public ArrayList<World> getWorlds() {
        return worlds;
    }

    public Layout() {
        worlds.add(
            new World("Town", 2250, 2250, new Color(60, 160, 75), new Color(200, 190, 125))
                .add(new Entity("vRoad", new Sprite(75, 2250, Color.DARK_GRAY)), 0, 0)
                .add(new Entity("hRoad", new Sprite(2250, 75, Color.DARK_GRAY)), 0, 0)
                .add(new Entity("House", new Sprite(200, 175, new Color(100, 80, 50))) {
                    @Override
                    public void onClick(Player player, PlayerStats stats) {
                        player.goTo("Home Door");
                    }
                }, 250, -200)
                .add(new Entity("School", new Sprite(150, 200, new Color(179, 96, 71))) {
                    @Override
                    public void whileTouching(Player player, PlayerStats stats) {
                        stats.gainIntellect(0.25);
                        stats.tire(0.05);
                    }
                },250, 250)
                .add(new Entity("Office", new Sprite(200, 200, new Color(150, 150, 160))) {
                    @Override
                    public void whileTouching(Player player, PlayerStats stats) {
                        stats.gainMoney(stats.getIntellect());
                        stats.tire(0.075);
                    }
                }, -250, 250)
                .add(new Entity("Gym", new Sprite("gym")) {
                    @Override
                    public void whileTouching(Player player, PlayerStats stats) {
                        if (stats.canAfford(0.05)) {
                            stats.strengthen(0.5);
                            stats.tire(0.075);
                            stats.loseMoney(0.05);
                        }
                    }
                }, -600, -200)
                .add(new Entity("Restaurant", new Sprite(200, 200, new Color(255, 215, 125))) {
                    @Override
                    public void whileTouching(Player player, PlayerStats stats) {
                        if (stats.canAfford(0.05)) {
                            stats.energize(0.1);
                            stats.loseMoney(0.05);
                        }
                    }
                }, -250, -225)
                .add(new Entity("Hospital", new Sprite(200, 200, new Color(210, 210, 210))) {
                    @Override
                    public void whileTouching(Player player, PlayerStats stats) {
                        if (stats.canAfford(0.25)) {
                            if (stats.getHealth() < 1000) stats.heal(0.05);
                            stats.loseMoney(0.25);
                        }
                    }
                }, -250, -550)
                .add(new Entity("Shop", new Sprite(250, 200, new Color(200, 110, 75))), -675, 250)
                .add(new Entity("Cave", new Sprite(200, 75, Color.LIGHT_GRAY)), -800, -800)

                .addSpawner(new Spawner(new AttackEntity("Emo", new AnimatedSprite(new Animation("emo", 120, new Vector2D(11, 16), 0)),
                    new HealthStats(3, 8, Alliance.ENEMY, 25), 150), 1500))

                .addSpawner(new Spawner(new RangedAttackEntity("Nerd", new AnimatedSprite(new Animation("nerd", 300, new Vector2D(7, 16), 0)),
                    new HealthStats(2.5, 5, Alliance.ENEMY, 20), 200, 1000,
                        new Projectile("Ball", new Sprite(4, 4, Color.BLACK), new BasicStats(15, 10, Alliance.ENEMY),
                                125,false)), 2500))
        );


        worlds.add(new World("Home", 300, 225, new Color(230, 210, 140), new Color(100, 80, 50))
            .add(new Entity("Home Door", new Sprite(2, 30, new Color(167, 155, 81))) {
                @Override
                public void onClick(Player player, PlayerStats stats) {
                    player.goTo("House");
                }
            }, 150, 0)

        );


        worlds.add(new World("City", 3000, 3000, new Color(180, 180, 180), new Color(100, 205, 131))

        );

    }

}
