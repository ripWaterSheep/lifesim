package lifesim.main.game.entities.components.items.inventory;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;

import java.awt.*;


public class ItemStack {

    private static final Font detailFont = FontLoader.getMainFont(5);

    private final Item item;
    private int amount;
    public final Vector2D currentPos;
    public final Vector2D lastPos;


    public ItemStack(Item item, int amount, Vector2D pos) {
        this.item = item;
        this.amount = amount;
        currentPos = pos;
        lastPos = pos;
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
        currentPos.set(dragPos);
        currentPos.clampAbs(inventoryBounds.translate(item.sprite.size.scale(-0.5)));
    }


    public void release() {
        lastPos.set(currentPos);
    }


    public void render(Graphics2D g2d) {
        item.sprite.render(g2d, currentPos, new Vector2D(0, 0));
    }

    public void renderDetailsAt(Graphics2D g2d, Vector2D pos) {
        DrawMethods.drawCenteredString(g2d, item.name+" * "+amount, new Rectangle((int) pos.x, (int) pos.y, (int) item.sprite.size.x, (int) item.sprite.size.y),
                detailFont, Color.WHITE);
    }

}
