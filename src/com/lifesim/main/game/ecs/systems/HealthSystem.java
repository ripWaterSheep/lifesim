package com.lifesim.main.game.ecs.systems;

import com.lifesim.main.game.GameManager;
import com.lifesim.main.game.ecs.components.HealthComponent;
import com.lifesim.main.game.ecs.components.StatsComponent;
import com.lifesim.main.game.ecs.entities.Entity;
import com.lifesim.main.game.ecs.entities.player.Player;
import com.lifesim.main.game.overlay.DeathScreen;
import com.lifesim.main.game.setting.World;

public class HealthSystem extends IterativeSystem {

    public HealthSystem(World world) {
        super(world);
    }

    @Override
    public void run(Entity entity) {
        for (HealthComponent health: entity.getAll(HealthComponent.class)) {
            if (health.getHealth() <= 0) {
                if (entity instanceof Player) {
                    DeathScreen.show("You lost all of your health!");
                } else {
                    System.out.println("removing " + entity.getName());
                    GameManager.getPlayer().get(StatsComponent.class).gainMoney(health.getKillLoot());
                    world.remove(entity);
                }
            }
        }

    }

}
