package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.entities.itemEntites.DroppedItem;
import lifesim.game.handlers.World;
import lifesim.util.math.Vector2D;

import java.awt.*;


public abstract class Item {

    public final String name;
    public final Sprite icon;

    public Item(String name, Sprite icon) {
        this.name = name;
        this.icon = icon;
    }


    public abstract boolean shouldBeUsed();

    public abstract boolean shouldBeDepleted();


    public abstract void use(World world, Player player, PlayerStats stats);

    public abstract void renderWhileHolding(Graphics2D g2d, Player player);

    public void renderIcon(Graphics2D g2d, Vector2D pos) {
        icon.render(g2d, pos, new Vector2D(0, 0));
    }

    public DroppedItem getDroppedEntity(int amount) {
        return new DroppedItem(this, amount);
    }

}
