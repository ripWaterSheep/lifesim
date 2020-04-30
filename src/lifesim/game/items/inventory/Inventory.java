package lifesim.game.items.inventory;

import lifesim.game.entities.Player;
import lifesim.game.input.MouseInput;
import lifesim.game.items.Item;
import lifesim.state.Game;

import java.util.ArrayList;


public class Inventory {

    public final int width = 3;
    public final int height = 3;
    public final int size = width * height;

    private final Player player;
    private final Game game;

    private final ArrayList<InventorySlot> slots = new ArrayList<>();


    public Inventory(Player player, Game game) {
        this.player = player;
        this.game = game;

        for (int i = 0; i < size; i++) {
            slots.add(new InventorySlot());
        }
    }


    public void addItem(Item item, int amount) {
        for (InventorySlot slot: slots) {
            if (slot.getItem().equals(item)) {
                slot.changeAmount(amount);
                return;
            }
        }
        InventorySlot slot = getFirstEmptySlot();
        
        slot.setItem(item, amount);

        if (!slots.contains(slot)) {
            // If no empty slot exists, throw the item out.
            dropItemInWorld(slot);
            game.displayMessage("Inventory is full!");
        }
    }


    public ArrayList<InventorySlot> getSlots() {
        return new ArrayList<>(slots);
    }


    public InventorySlot getFirstEmptySlot() {
        // New slot is created so that the item doesn't affect the actual inventory slots.
        InventorySlot lastVacantSlot = new InventorySlot();

        for (InventorySlot slot: slots) {
            if (slot.isEmpty()) {
                // Since the slots are all in order, the first empty slot found will be the correct one.
                lastVacantSlot = slot;
                break;
            }
        }
        return lastVacantSlot;
    }


    public void dropItemInWorld(InventorySlot slot) {
        slot.dropItem(player.getWorld(), player.getPos().translate(MouseInput.getCursorPos()));
    }


}
