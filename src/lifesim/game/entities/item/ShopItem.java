package lifesim.game.entities.item;

import lifesim.game.handlers.World;
import lifesim.game.item.ItemType;
import lifesim.game.overlay.MessageDisplay;
import lifesim.state.Game;
import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.util.MyMath;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public class ShopItem extends DroppedItem {

    private final MessageDisplay messageDisplay = new MessageDisplay(7, 5000, new Vector2D(0, 0));
    private final double price;

    public ShopItem(ItemType item, int amount, double price) {
        super(item, amount);
        this.price = price;
    }


    @Override
    public void playerCollision(Game game, Player player, PlayerStats stats) {
        messageDisplay.displayMessage("Buy " + name + " for $" + MyMath.roundToMultiple(price, 0.1) + "0?");
    }


    @Override
    public void interact(Game game, Player player, PlayerStats stats) {
        if (stats.canAfford(price)) {
            game.displayMessage(name + "++!");
            stats.loseMoney(price);
            collect(player);
        }
    }

    @Override
    public void update(World world) {
        super.update(world);
        messageDisplay.update();
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.translate(getDisplayPos().x, getDisplayPos().y - getHitbox().height/2);
        messageDisplay.render(g2d);
    }
}
