package lifesim.main.game.entities;

import lifesim.main.game.entities.components.items.inventory.ItemStack;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.components.items.Item;

public class DroppedItemStack extends Entity {

    private final ItemStack stack;

    private boolean collected = false;

    public DroppedItemStack(String name, Sprite sprite, ItemStack stack) {
        super(name, sprite);
        this.stack = stack;
    }


    public void collect(Player player) {
        if (!collected) {
            player.acquireItem(stack.getItem(), stack.getAmount());
            removeFromWorld();
            collected = true;
        }
    }


    @Override
    public Entity copyInitialState() {
        return new DroppedItemStack(name, sprite, stack);
    }


    @Override
    public void whileTouching(Player player, PlayerStats stats) {
        collect(player);
    }

}
