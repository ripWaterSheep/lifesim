package lifesim.game.entities.types;

import lifesim.game.entities.Entity;
import lifesim.game.entities.itemEntites.ItemPackage;

public enum ResourceTypes implements Spawnable {

    ITEM_PACKAGE;

    @Override
    public Entity spawnEntity() {
        return new ItemPackage();
    }
}
