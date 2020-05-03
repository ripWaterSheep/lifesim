package lifesim.game.entities.itemEntites;

import lifesim.game.items.ItemType;
import lifesim.state.Game;
import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.items.OLD.OLDItem;

public class DroppedItem extends Entity {

    private final ItemType item;
    private final int amount;

    private boolean collected = false;

    public DroppedItem(ItemType item, int amount) {
        super("Dropped " + item.name, item.icon);
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
    public void playerCollision(Game game, Player player, PlayerStats stats) {
        collect(player);
    }

}
