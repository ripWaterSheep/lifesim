package lifesim.main.game.entities;

import lifesim.main.game.Game;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.items.Item;

public class ShopItem extends DroppedItem {

    public ShopItem(String name, Sprite sprite, Item item, int amount, double price) {
        super(name, sprite, item, amount);
    }


    @Override
    public void eventWhileTouching(Game game, Player player, PlayerStats stats) {
        super.eventWhileTouching(game, player, stats);
    }


    @Override
    public void eventOnClick(Game game, Player player, PlayerStats stats) {
        super.eventOnClick(game, player, stats);
    }
}
