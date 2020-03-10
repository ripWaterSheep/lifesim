package lifesim.main.game.ecs.entities.player.inventory;

import lifesim.main.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Inventory {

    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;

    static final int MAX_ITEMS = 25;


    ArrayList<InventoryItem> items = new ArrayList<>();

    public Inventory(InventoryItem... startingItems) {
        items.addAll(Arrays.asList(startingItems));
    }


    public void addItem(InventoryItem item) {
        if (items.size() < MAX_ITEMS) items.add(item);
        else System.out.println("Inventory is too full to add " + item.getName() + ".");
    }

    public void depleteItem(InventoryItem item) {
        try {
            items.remove(item);
        } catch (Exception e) {
            if (items != null)
                System.out.println("Cannot find " + item.getName() + " to remove from inventory.");
        }
    }

    public void draw(Graphics g) {
        g.fillRect((Game.getPanel().getWidth() - WIDTH)/2, (Game.getPanel().getHeight() - HEIGHT )/2, WIDTH, HEIGHT);
    }

}
