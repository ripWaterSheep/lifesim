package lifesim.game.item.inventory;

import lifesim.game.entities.Player;
import lifesim.game.handlers.World;
import lifesim.game.overlay.InventoryGUI;
import lifesim.game.item.ItemType;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public class InventorySlot {

    private static final ItemType EMPTY_ITEM = ItemType.HAND;
    private static final Font AMOUNT_FONT = FontLoader.getMainFont(4);

    private final Inventory inventory;

    private ItemType item;
    private int amount;


    public InventorySlot(Inventory inventory) {
        this.inventory = inventory;
        this.item = EMPTY_ITEM;
        this.amount = 0;
    }


    public ItemType getItem() {
        return item;
    }

    public void setItem(ItemType newItem, int newAmount) {
         item = newItem;
         amount = newAmount;
    }

    public void increase(int amount) {
        this.amount += amount;
    }

    public String getInfo() {
        if (amount <= 0) return "";
        else return item.name + " * " + amount;
    }


    public boolean isEmpty() {
        return item.equals(EMPTY_ITEM);
    }

    void becomeEmpty() {
        item = EMPTY_ITEM;
        amount = 0;
    }

    public void swapItem(InventorySlot slot) {
        ItemType tempItem = item;
        int tempAmount = amount;

        setItem(slot.item, slot.amount);
        slot.setItem(tempItem, tempAmount);
    }

    public void dropItem(World world, Vector2D pos) {
        if (!isEmpty()) {
            world.add(item.getDroppedEntity(amount), pos);
            becomeEmpty();
        }
    }


    public void useItem(Player player) {
        if (amount > 0 || isEmpty()) {
            if (item.canBeUsed(player.getWorld(), player)) {
                item.use(player.getWorld(), player, player.getStats());
                amount -= 1;
            }
        }

        if (amount <= 0) {
            becomeEmpty();
        }
    }

    public void render(Graphics2D g2d, Vector2D pos, boolean doRenderText) {
        if (!isEmpty()) {
            item.renderIcon(g2d, pos);

            if (doRenderText) {
                GraphicsMethods.centeredString(g2d, amount+"", pos.translate(InventoryGUI.GRID_SIZE/2.0,
                        InventoryGUI.GRID_SIZE/2.0), AMOUNT_FONT, Color.WHITE);
            }
        }
    }

}
