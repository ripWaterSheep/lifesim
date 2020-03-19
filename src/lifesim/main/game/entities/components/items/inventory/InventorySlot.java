package lifesim.main.game.entities.components.items.inventory;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.handlers.World;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.Fonts;
import lifesim.main.util.fileIO.ImageLoader;

import java.awt.*;

public class InventorySlot {

    private static final Item EMPTY = new Item("", new Sprite(new Vector2D(0, 0), Color.BLACK, false));
    private static final Font font = Fonts.getMainFont(10);


    private Item item = EMPTY;
    private int amount = 0;
    private final Vector2D pos;

    public InventorySlot(Vector2D pos) {
        this.pos = pos;
    }


    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isEmpty() {
        return item == EMPTY;
    }


    void setItem(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }



    void swapWith(InventorySlot slot) {
        Item newItem = slot.item;
        int newAmount = slot.amount;

        slot.item = item;
        slot.amount = amount;
        item = newItem;
        amount = newAmount;
    }


    void depleteItem(int amount) {
        if (this.amount > amount)
            this.amount -= amount;
        else setItem(EMPTY, 1);

    }



    public void dropItemInWorld(Item item, World world, Vector2D centerPos) {
        int dropRadius = 5;

        for (int i = 0; i < amount; i++) {
            Vector2D newPos = new Vector2D(0, 0);
            pos.setMagnDir(dropRadius, 360.0/amount);

            world.add(item.getDroppedEntity(), centerPos.translate(newPos));
        }
    }


    public void renderItem(Graphics2D g2d) {
        item.render(g2d, pos);
        DrawMethods.drawCenteredString(g2d, ""+amount, new Rectangle((int) pos.x, (int) pos.y, 100, 100), font, Color.WHITE);
    }


}
