package lifesim.main.game.entities;

import lifesim.main.game.Game;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;
import lifesim.main.game.items.Item;


import static lifesim.main.util.math.MyMath.roundToMultiple;

public class ShopItem extends DroppedItem {

    private final double price;

    public ShopItem(String name, Sprite sprite, Item item, int amount, double price) {
        super(name, sprite, item, amount);
        this.price = price;
    }


    @Override
    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {
        game.displayCenter("Buy " + name + " for $" + roundToMultiple(price, 0.1) + "0?");
    }


    @Override
    public void eventOnClick(Game game, Player player, PlayerStats stats) {
        if (stats.attemptToPay(price)) {
            game.displayCenter(name + "++!");
        }
        collect(player);
    }

}
