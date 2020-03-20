package lifesim.main.game.entities.components.items.inventory;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.Item;

import java.awt.*;

import static lifesim.main.game.GamePanel.GRAPHICS_SCALE;


public class InventoryStack {

    private final Item item;
    private int amount;
    public final Vector2D pos;


    public InventoryStack(Item item, int amount, Vector2D pos) {
        this.item = item;
        this.amount = amount;
        this.pos = pos;
    }


    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }


    public void changeAmountBy(int amount) {
        this.amount -= amount;
    }

    public void renderItem(Graphics2D g2d, double scale) {
        item.sprite.render(g2d, pos.scale(scale), new Vector2D(0, 0));
    }

}
