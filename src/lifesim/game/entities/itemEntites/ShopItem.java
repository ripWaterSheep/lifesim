package lifesim.game.entities.itemEntites;

import lifesim.game.items.ItemType;
import lifesim.state.Game;
import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.util.MyMath;


public class ShopItem extends DroppedItem {

    private final double price;

    public ShopItem(ItemType item, int amount, double price) {
        super(item, amount);
        this.price = price;
    }


    @Override
    public void playerCollision(Game game, Player player, PlayerStats stats) {
        game.displayMessage("Buy " + name + " for $" + MyMath.roundToMultiple(price, 0.1) + "0?");
    }


    @Override
    public void interact(Game game, Player player, PlayerStats stats) {
        if (stats.canAfford(price)) {
            game.displayMessage(name + "++!");
        }
        collect(player);
    }

}
