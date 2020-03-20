package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.items.Item;

public class DroppedItemEntity extends Entity {

    private final Item item;

    public DroppedItemEntity(String name, Sprite sprite, Item item) {
        super(name, sprite);
        this.item = item;
    }


    @Override
    public DroppedItemEntity copyInitialState() {
        return new DroppedItemEntity(name, sprite, item);
    }

    @Override
    public void whileTouching(Player player, PlayerStats stats) {
        player.acquireItem(item, 1);
        this.removeFromWorld();
    }

}
