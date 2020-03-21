package lifesim.main.game.entities;

import lifesim.main.game.entities.components.items.inventory.ItemStack;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.items.Item;

public class DroppedItemStack extends Entity {

    private final ItemStack stack;

    public DroppedItemStack(String name, Sprite sprite, ItemStack stack) {
        super(name, sprite);
        this.stack = stack;
    }


    @Override
    public void whileTouching(Player player, PlayerStats stats) {
        for (int i = 0; i < stack.getAmount(); i++)
            player.acquireItem(stack.getItem(), 1);
        removeFromWorld();
    }

}
