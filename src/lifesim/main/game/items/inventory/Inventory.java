package lifesim.main.game.items.inventory;

import lifesim.main.game.controls.MouseInput;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.handlers.World;
import lifesim.main.game.items.Item;

import java.util.ArrayList;

public class Inventory {

    private static int SIZE = 30;

    // Null pointer exception prevention
    public static InventorySlot NULL_SLOT = new InventorySlot();


    private final Player player;

    private InventorySlot selectedSlot;
    private final ArrayList<InventorySlot> slots = new ArrayList<>();


    public Inventory(Player player) {
        this.player = player;

        for (int i = 0; i < SIZE; i++)
            slots.add(new InventorySlot());

        selectedSlot = slots.get(0);
    }


    public void addItem(Item item, int amount) {
        getFirstEmptySlot().setItem(item, amount);
    }

    public ArrayList<InventorySlot> getSlots() {
        return new ArrayList<InventorySlot>(slots);
    }

    public InventorySlot getSelectedSlot() {
        return selectedSlot;
    }

    public void selectSlot(InventorySlot slot) {
        selectedSlot = slot;
    }

    public void unselectSlot() {
        selectedSlot = NULL_SLOT;
    }

    public boolean isSelectingNothing() {
        return selectedSlot.equals(NULL_SLOT);
    }


    public boolean isEmpty() {
        return getFirstEmptySlot() == NULL_SLOT;
    }

    private InventorySlot getFirstEmptySlot() {
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


    public void droSelectedSlot(World world, Vector2D pos) {
        selectedSlot.dropItem(world, pos);
        selectedSlot = getFirstEmptySlot();
    }


    public void control() {
        if (MouseInput.right.isClicked()) {
            selectedSlot.useItem(player);
        }
    }

}
