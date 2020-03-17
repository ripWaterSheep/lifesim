package lifesim.main.game.entities.components.items;


import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.AllItems;
import lifesim.main.game.entities.components.items.Armor;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.game.setting.World;

import java.awt.*;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items = new ArrayList<>();

    private Item selectedItem = AllItems.standardGun;


    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }


    public void addItem(Item item) {
        items.add(item);
    }


    public void depleteItem(Item item) {

    }


    public void renderSelectedItemsAt(Graphics2D g2d, Vector2D pos) {
        for (Item item: items) {
            if (item instanceof Armor)
                item.render(g2d, pos);
        }
    }

    public void clickSelectedItem(Vector2D pos, World world, Entity entity) {
        selectedItem.onClick(world, entity);
    }


}
