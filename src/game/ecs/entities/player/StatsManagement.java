package game.ecs.entities.player;

import game.ecs.components.HealthComponent;
import game.ecs.components.StatsComponent;
import game.ecs.entities.Entity;
import game.ecs.systems.IterableSystem;
import game.setting.World;

public class StatsManagement {

    private HealthComponent health;
    private StatsComponent stats;

    public StatsManagement(Player player) {
        health = player.get(HealthComponent.class);
        stats = player.get(StatsComponent.class);
    }


    public void run() {
        if (stats.getEnergy() <= 0) {
            health.loseHealth(0.5);
        }

        stats.tire(0.1);
    }

}
