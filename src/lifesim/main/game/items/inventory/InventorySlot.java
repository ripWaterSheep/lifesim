package lifesim.main.game.items.inventory;

import lifesim.main.game.entities.Player;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;
import lifesim.main.util.math.Vector2D;
import lifesim.main.game.handlers.World;
import lifesim.main.game.items.Item;
import lifesim.main.game.items.ItemTypes;

import java.awt.*;

import static lifesim.main.game.display.overlay.InventoryGUI.GRID_SIZE;


public class InventorySlot {

    private static final Item EMPTY_ITEM =  ItemTypes.hand;

    private static final Font AMOUNT_FONT = FontLoader.getMainFont(4);


    private Item item;
    private int amount;


    public InventorySlot() {
        this.item = EMPTY_ITEM;
        this.amount = 0;
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item newItem, int newAmount) {
         item = newItem;
         amount = newAmount;
    }


    public int getAmount() {
        return amount;
    }

    public void changeAmount(int amount) {
        this.amount += amount;
    }

    public String getInfo() {
        if (amount <= 0) return "";
        else return item.name;
    }


    public boolean isEmpty() {
        return item.equals(EMPTY_ITEM);
    }

    private void becomeEmpty() {
        item = EMPTY_ITEM;
        amount = 0;
    }

    public void swapItem(InventorySlot slot) {
        Item tempItem = item;
        int tempAmount = amount;

        setItem(slot.item, slot.amount);
        slot.setItem(tempItem, tempAmount);
    }

    public void dropItem(World world, Vector2D pos) {
        world.add(item.getDroppedEntity(amount), pos);
        becomeEmpty();
    }


    public void useItem(Player player) {
        if (item.shouldBeUsed() && (amount > 0 || isEmpty())) {
            item.use(player.getWorld(), player, player.getStats());

            if (item.shouldBeDepleted()) {
                amount -= 1;
            }
        }

        if (amount <= 0)
            becomeEmpty();
    }

    public void render(Graphics2D g2d, Vector2D pos, boolean doRenderText) {
        if (!isEmpty()) {
            item.renderIcon(g2d, pos);

            if (doRenderText) {
                DrawMethods.drawCenteredString(g2d, amount+"", pos.translate(GRID_SIZE/2.0, GRID_SIZE/2.0), AMOUNT_FONT, Color.WHITE);
            }
        }
    }

}
