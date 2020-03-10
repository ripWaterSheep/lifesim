package game.ecs.systems;

import game.GameManager;
import game.ecs.components.HealthComponent;
import game.ecs.components.StatsComponent;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.overlay.DeathScreen;
import game.setting.World;

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
