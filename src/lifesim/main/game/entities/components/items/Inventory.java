package lifesim.main.game.entities.components.items;


import java.util.ArrayList;

public class Inventory {

    public static final int MAX_CAPACITY = 36;


    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }


    public void addItem(Item item) {
        items.add(item);
    }


    public void depleteItem(Item item) {
        items.remove(item);
    }


}
