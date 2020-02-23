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
                    //TODO: fix overlay structuree so that new deathscreen is not created like a billion times
                } else {
                    world.remove(entity);
                }

            }
        }

    }

}
