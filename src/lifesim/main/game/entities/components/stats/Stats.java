package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;

public interface Stats {

    void collision(Entity owner, Entity entity);
    void run(Entity owner);

}
