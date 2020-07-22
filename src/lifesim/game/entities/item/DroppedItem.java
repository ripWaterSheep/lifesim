package lifesim.game.entities.item;

import lifesim.game.entities.stats.Alliance;
import lifesim.game.entities.stats.HealthStats;
import lifesim.game.handlers.World;
import lifesim.game.item.ItemType;
import lifesim.state.Game;
import lifesim.game.entities.Entity;
import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;

public class DroppedItem extends Entity {

    private final ItemType item;
    private final int amount;

    private boolean collected = false;

    public DroppedItem(ItemType item, int amount) {
        super("Dropped " + item.name, item.icon, new HealthStats(0, Alliance.PLAYER, 100));
        this.item = item;
        this.amount = amount;
        assert amount > 0;
    }


    protected void collect(Player player) {
        player.acquireItem(item, amount);
    }

    @Override
    public void playerCollision(Game game, Player player, PlayerStats stats) {
        if (!collected) {
            collect(player);
            collected = true;
        }
        removeFromWorld();
    }

    @Override
    public void update(World world) {
        super.update(world);
    }
}
