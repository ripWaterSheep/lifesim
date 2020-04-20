package lifesim.main.game.entities.itemEntites;

import lifesim.main.game.Game;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.entities.itemEntites.DroppedItem;
import lifesim.main.game.items.Item;


import static lifesim.main.util.math.MyMath.roundToMultiple;

public class ShopItem extends DroppedItem {

    private final double price;

    public ShopItem(Item item, int amount, double price) {
        super(item, amount);
        this.price = price;
    }


    @Override
    public void playerCollision(Game game, Player player, PlayerStats stats) {
        game.displayCenter("Buy " + name + " for $" + roundToMultiple(price, 0.1) + "0?");
    }


    @Override
    public void interact(Game game, Player player, PlayerStats stats) {
        if (stats.attemptToPay(price)) {
            game.displayCenter(name + "++!");
        }
        collect(player);
    }

}
