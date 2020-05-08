package lifesim.game.entities.types;

import lifesim.game.entities.Entity;

public interface Spawnable {

    Entity spawnEntity();
    int getMaxPerWorld();

}
