package lifesim.main.game.entities.itemEntites;

import lifesim.main.game.Game;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.items.Item;

public class DroppedItem extends Entity {

    private final Item item;
    private final int amount;

    private boolean collected = false;

    public DroppedItem(Item item, int amount) {
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
