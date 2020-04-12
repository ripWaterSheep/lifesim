package lifesim.main.game.entities;

import lifesim.main.game.Game;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.items.Item;
import lifesim.main.game.entities.components.sprites.Sprite;

public class DroppedItem extends Entity {

    private final Item item;
    private final int amount;

    private boolean collected = false;

    public DroppedItem(Item item, int amount) {
        super("Dropped " + item.name, item.sprite);
        this.item = item;
        this.amount = amount;
    }


    protected void collect(Player player) {
        if (!collected) {
            player.acquireItem(item, amount);
            removeFromWorld();
            collected = true;
        }
    }

    @Override
    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {
        collect(player);
    }

}
