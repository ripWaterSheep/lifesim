package lifesim.main.game.ecs.systems;

import lifesim.main.Game;
import lifesim.main.game.ecs.components.HealthComponent;
import lifesim.main.game.ecs.components.StatsComponent;
import lifesim.main.game.ecs.entities.Entity;
import lifesim.main.game.ecs.entities.player.Player;
import lifesim.main.game.overlay.DeathScreen;
import lifesim.main.game.setting.World;

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
                    Game.getSession().getPlayer().get(StatsComponent.class).gainMoney(health.getKillLoot());
                    world.remove(entity);
                }
            }
        }

    }

}
