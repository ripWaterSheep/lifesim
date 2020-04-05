package lifesim.main.game.items.inventory;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.items.Item;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;

import java.awt.*;

import static lifesim.main.util.math.MyMath.roundToMultiple;


public class ItemStack {

    private final Item item;
    private int amount;
    public final Vector2D inventoryPos;


    public ItemStack(Item item, int amount, Vector2D inventoryPos) {
        this.item = item;
        this.amount = amount;
        this.inventoryPos = inventoryPos;
    }


    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }


    public void changeAmountBy(int amount) {
        this.amount += amount;
    }


    public void drag(Vector2D dragPos, Vector2D inventoryBounds) {
        inventoryPos.set(dragPos);
        inventoryPos.clampInRect(new Vector2D(0, 0), inventoryBounds.translate(item.sprite.getSize().scale(-0.5)));
    }


    public void snapToGrid(int gridSize) {
        inventoryPos.set(roundToMultiple(inventoryPos.x, gridSize), roundToMultiple(inventoryPos.y, gridSize));
    }


    public void render(Graphics2D g2d) {
        item.sprite.render(g2d, inventoryPos, new Vector2D(0, 0));
    }

    public void renderDetails(Graphics2D g2d, Vector2D pos, Font font) {
        DrawMethods.drawCenteredString(g2d, item.name+" * "+amount, pos, font, Color.WHITE);
    }

}
