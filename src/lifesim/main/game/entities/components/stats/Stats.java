package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.handlers.World;

public interface Stats {

    void onDeath(Entity owner, World world);
    void handleCollisions(Entity owner, Entity entity);
    Stats copyInitialState();
    void run(Entity owner);

}
