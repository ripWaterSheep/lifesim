package lifesim.main.game.entities.components.stats;

import lifesim.main.game.entities.Entity;
import lifesim.main.game.handlers.World;

public class BasicStats implements Stats {

    @Override
    public void handleCollisions(Entity owner, Entity entity) { }

    @Override
    public Stats copyInitialState() {
        return new BasicStats();
    }

    @Override
    public void run(Entity owner) { }

}
