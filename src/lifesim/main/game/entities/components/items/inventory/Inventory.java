package lifesim.main.game.entities.components.items.inventory;

import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.Item;

import java.util.ArrayList;


public class Inventory {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 3;
    public static final int MAX_CAPACITY = WIDTH*HEIGHT;


    private ArrayList<InventorySlot> slots = new ArrayList<>();


    public Inventory() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                slots.add(new InventorySlot(new Vector2D(x, y)));
            }
        }
    }


    public ArrayList<InventorySlot> getSlots() {
        return new ArrayList<>(slots);
    }


    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        for (InventorySlot slot: slots) {
            items.add(slot.getItem());
        }

        return items;
    }


    public ArrayList<Item> getItemsWithAmounts() {
        ArrayList<Item> items = new ArrayList<>();
        for (InventorySlot slot: slots) {
            if (!slot.isEmpty()) {
                for (int i = 0; i < slot.getAmount(); i++) {
                    items.add(slot.getItem());
                }
            }
        }
        return items;
    }


    public InventorySlot getNextVacantSlot() {
        InventorySlot vacantSlot = null;
        for (InventorySlot slot: slots) {
            if (slot.isEmpty()) {
                vacantSlot = slot;
            }
        }

        return vacantSlot;
    }



    public void addItem(Item item, int amount) {
        if (getNextVacantSlot() != null)
            getNextVacantSlot().setItem(item, amount);
    }


    public void depleteItem(Item item, int amount) {
        for (InventorySlot slot: slots) {
            if (slot.getItem() == item) {
                slot.depleteItem(amount);
            }
        }
    }


}
