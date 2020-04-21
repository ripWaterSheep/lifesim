package lifesim.game.entities.itemEntites;

import lifesim.game.state.Game;
import lifesim.game.entities.Player;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.items.Item;
import lifesim.util.math.MyMath;

public class ShopItem extends DroppedItem {

    private final double price;

    public ShopItem(Item item, int amount, double price) {
        super(item, amount);
        this.price = price;
    }


    @Override
    public void playerCollision(Game game, Player player, PlayerStats stats) {
        game.displayCenter("Buy " + name + " for $" + MyMath.roundToMultiple(price, 0.1) + "0?");
    }


    @Override
    public void interact(Game game, Player player, PlayerStats stats) {
        if (stats.attemptToPay(price)) {
            game.displayCenter(name + "++!");
        }
        collect(player);
    }

}
