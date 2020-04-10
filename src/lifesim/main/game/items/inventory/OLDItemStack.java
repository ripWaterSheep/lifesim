package lifesim.main.game.items.inventory;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.items.Item;
import lifesim.main.util.DrawMethods;

import java.awt.*;

import static lifesim.main.util.math.MyMath.roundToMultiple;


public class OLDItemStack {

    private final Item item;
    private int amount;
    private final Vector2D inventoryPos;


    public OLDItemStack(Item item, int amount, Vector2D inventoryPos) {
        this.item = item;
        this.amount = amount;
        this.inventoryPos = inventoryPos;
    }

    public Vector2D getPos() {
        return inventoryPos.copy();
    }

    void setPos(Vector2D newPos) {
        inventoryPos.set(newPos);
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
        inventoryPos.clampInRect(new Vector2D(0, 0), inventoryBounds.copy().translate(item.sprite.getSize().scale(-0.5)));
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
