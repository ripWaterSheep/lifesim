package lifesim.main.game.entities.components.items.inventory;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.Item;

import java.awt.*;
import java.nio.file.attribute.UserPrincipal;

import static javax.swing.JComponent.isLightweightComponent;
import static lifesim.main.game.GamePanel.GRAPHICS_SCALE;


public class InventoryStack {

    private final Item item;
    private int amount;
    public final Vector2D pos;

    private boolean dragging = false;
    public Vector2D dragPos;


    public InventoryStack(Item item, int amount, Vector2D pos) {
        this.item = item;
        this.amount = amount;
        this.pos = pos;
        dragPos = pos;
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


    public void drag(Vector2D dragPos, Vector2D inventoryBounds) {
        this.dragPos = dragPos;
        this.dragging = true;

        dragPos.clampAbs(inventoryBounds.translate(item.sprite.size.scale(-0.5)));
    }


    public void release() {
        pos.set(dragPos);
        dragging = false;
    }


    public void renderItem(Graphics2D g2d) {
        item.sprite.render(g2d, dragPos, new Vector2D(0, 0));
    }

}
