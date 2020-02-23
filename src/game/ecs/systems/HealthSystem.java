package game.ecs.systems;

import game.ecs.components.HealthComponent;
import game.ecs.entities.Entity;
import game.ecs.entities.player.Player;
import game.overlay.DeathScreen;
import game.setting.world.World;
import main.MainPanel;

public class HealthSystem extends IterableSystem {

    public HealthSystem(World world) {
        super(world);
    }


    @Override
    public void run(Entity entity) {
        for (HealthComponent health: entity.getAll(HealthComponent.class)) {

            if (health.getHealth() <= 0) {
                if (entity instanceof Player) {
                    new MainPanel().addOverlay(new DeathScreen());
                } else {
                    world.remove(entity);
                }

            }
        }

    }

}
