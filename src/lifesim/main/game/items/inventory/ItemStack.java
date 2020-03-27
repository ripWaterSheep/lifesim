package lifesim.main.game.items.inventory;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.items.Item;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;

import java.awt.*;

import static lifesim.main.util.math.MyMath.roundToMultiple;


public class ItemStack {

    private static final Font detailFont = FontLoader.getMainFont(5);

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
        inventoryPos.clampAbs(inventoryBounds.translate(item.sprite.getSize().scale(-0.5)));
    }


    public void snapToGrid(int gridSize) {
        inventoryPos.set(roundToMultiple(inventoryPos.x, gridSize), roundToMultiple(inventoryPos.y, gridSize));
    }


    public void render(Graphics2D g2d) {
        item.sprite.render(g2d, inventoryPos, new Vector2D(0, 0));
    }

    public void renderDetailsAt(Graphics2D g2d, Vector2D pos) {
        DrawMethods.drawCenteredString(g2d, item.name+" * "+amount, new Rectangle((int) pos.x, (int) pos.y, (int) item.sprite.getSize().x, (int) item.sprite.getSize().y),
                detailFont, Color.WHITE);
    }

}
