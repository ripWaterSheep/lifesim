package lifesim.game.items.inventory;

import lifesim.game.entities.Player;
import lifesim.game.items.ItemType;
import lifesim.engine.input.MouseInput;
import lifesim.state.Game;

import java.util.List;
import java.util.ArrayList;


public class Inventory {

    public final int width = 4;
    public final int height = 3;

    private final Player player;
    private final Game game;

    private final List<InventorySlot> slots = new ArrayList<>();


    public Inventory(Player player, Game game) {
        this.player = player;
        this.game = game;

        for (int i = 0; i < width * height; i++) {
            slots.add(new InventorySlot(this));
        }
    }


    public void addItem(ItemType item, int amount) {
        for (InventorySlot slot: slots) {
            if (slot.getItem().equals(item)) {
                slot.increase(amount);
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


    public List<InventorySlot> getSlots() {
        return new ArrayList<>(slots);
    }


    public InventorySlot getFirstEmptySlot() {
        // New slot is created so that the item doesn't affect the actual inventory slots.
        InventorySlot lastVacantSlot = new InventorySlot(this);

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
        slot.dropItem(player.getWorld(), MouseInput.getCursorPosFrom(player.getPos()));
    }

}
