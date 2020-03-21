package lifesim.main.game.entities;

import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.items.Item;

public class DroppedItemStack extends Entity {

    private final Item item;
    private final int amount;

    public DroppedItemStack(String name, Sprite sprite, Item item, int amount) {
        super(name, sprite);
        this.item = item;
        this.amount = amount;
    }


    @Override
    public void whileTouching(Player player, PlayerStats stats) {
        for (int i = 0; i < amount; i++)
            player.acquireItem(item, 1);
        this.removeFromWorld();
    }

}
