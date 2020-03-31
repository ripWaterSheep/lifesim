package lifesim.main.game.entities;

import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.items.Item;
import lifesim.main.game.entities.components.sprites.Sprite;

public class DroppedItem extends Entity {

    private final Item item;
    private final int amount;

    private boolean collected = false;

    public DroppedItem(String name, Sprite sprite, Item item, int amount) {
        super(name, sprite);
        this.item = item;
        this.amount = amount;
    }


    public void collect(Player player) {
        if (!collected) {
            player.acquireItem(item, amount);
            removeFromWorld();
            collected = true;
        }
    }

    @Override
    public void whileTouching(Player player, PlayerStats stats) {
        collect(player);
    }

}
