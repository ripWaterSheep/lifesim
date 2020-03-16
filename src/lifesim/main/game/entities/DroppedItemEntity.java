package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.items.Item;

public class DroppedItemEntity extends Entity {

    private final Item item;

    public DroppedItemEntity(String name, Sprite sprite, Item item) {
        super(name, sprite);
        this.item = item;
    }

    @Override
    public void whileTouching(Player player, PlayerStats stats) {
        player.acquireItem(item);
        this.removeFromWorld();
    }

}
