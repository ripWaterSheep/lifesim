package lifesim.main.game.items.inventory;

import lifesim.main.game.entities.itemEntites.DroppedItem;
import lifesim.main.game.entities.Player;
import lifesim.main.util.math.Vector2D;
import lifesim.main.game.handlers.World;
import lifesim.main.game.items.Item;
import lifesim.main.game.items.ItemTypes;


public class InventorySlot {

    private static final Item EMPTY_ITEM =  ItemTypes.hand;

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

    public void swapItem(InventorySlot slot) {
        Item tempItem = item;
        int tempAmount = amount;

        setItem(slot.item, slot.amount);
        slot.setItem(tempItem, tempAmount);
    }

    public boolean isEmpty() {
        return item.equals(EMPTY_ITEM);
    }

    private void becomeEmpty() {
        item = EMPTY_ITEM;
        amount = 0;
    }


    public void dropItem(World world, Vector2D pos) {
        world.add(item.getDroppedEntity(amount), pos);
        becomeEmpty();
    }


    public int getAmount() {
        return amount;
    }

    public void changeAmount(int amount) {
        this.amount += amount;
    }

    public void useItem(Player player) {
        if (amount > 0 || isEmpty()) {
            item.use(player.getWorld(), player, player.getStats());
            amount -= 1;
        }
        if (amount <= 0)
            becomeEmpty();
    }

    public String getInfo() {
        if (amount <= 0) return "";
        else return item.name +" (" + amount + ")";
    }

}
