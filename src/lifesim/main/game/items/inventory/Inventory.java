package lifesim.main.game.items.inventory;

import lifesim.main.game.display.overlay.HotbarGUI;
import lifesim.main.game.input.MouseInput;
import lifesim.main.game.entities.Player;
import lifesim.main.game.items.Item;

import java.util.ArrayList;


public class Inventory {

    private static final int SIZE = 25;

    // Null pointer exception prevention
    public static final InventorySlot NULL_SLOT = new InventorySlot();


    private final Player player;

    private final ArrayList<InventorySlot> slots = new ArrayList<>();
    private InventorySlot selectedSlot;


    public Inventory(Player player) {
        this.player = player;

        for (int i = 0; i < SIZE; i++)
            slots.add(new InventorySlot());

        selectedSlot = slots.get(0);
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
        if (isEmpty()) selectSlot(slot);
    }


    public ArrayList<InventorySlot> getSlots() {
        return new ArrayList<>(slots);
    }

    public InventorySlot getSelectedSlot() {
        return selectedSlot;
    }

    public void selectSlot(InventorySlot slot) {
        selectedSlot = slot;
    }


    public boolean isEmpty() {
        boolean empty = true;
        for (InventorySlot slot: slots) {
            if (!slot.isEmpty()) empty = false;
            break;
        }
        return empty;
    }


    private InventorySlot getFirstEmptySlot() {
        // If this method returns null slot, then there are no slots to fill and the inventory is full
        InventorySlot lastVacantSlot = NULL_SLOT;
        for (InventorySlot slot: slots) {
            if (slot.isEmpty()) {
                // Since the slots are all in order, the first empty slot found will be the correct one.
                lastVacantSlot = slot;
                break;
            }
        }
        return lastVacantSlot;
    }


    public void control() {
        if (MouseInput.right.isClicked()) {
            selectedSlot.useItem(player);
        }
    }

}
