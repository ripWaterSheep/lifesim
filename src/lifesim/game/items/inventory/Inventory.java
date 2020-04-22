package lifesim.game.items.inventory;

import lifesim.game.entities.Player;
import lifesim.game.items.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class Inventory {

    public static final int WIDTH = 9;
    public static final int HEIGHT = 3;
    public static final int SIZE = WIDTH * HEIGHT;

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
            } else {
                break;
            }
        }
        return lastVacantSlot;
    }


    public void useSelectedItem() {
        selectedSlot.useItem(player);
    }

    public void renderHoldingItem(Graphics2D g2d) {
        selectedSlot.getItem().renderWhileHolding(g2d, player);
    }

}
