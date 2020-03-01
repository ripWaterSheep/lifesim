package game.ecs.systems;

import game.GameManager;
import game.ecs.components.HealthComponent;
import game.ecs.components.StatsComponent;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.overlay.DeathScreen;
import game.setting.World;

public class HealthSystem extends IterableSystem {

    public HealthSystem(World world) {
        super(world);
    }

    @Override
    public void run(Entity entity) {
        for (HealthComponent health: entity.getAll(HealthComponent.class)) {
            if (health.getHealth() <= 0) {
                if (entity instanceof Player) {
                    DeathScreen.show();
                    //TODO: fix overlay structuree so that new deathscreen is not created like a billion times
                } else {
                    System.out.println("removing " + entity.getName());
                    GameManager.getPlayer().get(StatsComponent.class).gainMoney(health.getKillLoot());
                    world.remove(entity);
                }
            }
        }

    }

}
