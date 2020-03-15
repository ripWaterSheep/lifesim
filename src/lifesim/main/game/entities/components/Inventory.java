package lifesim.main.game.entities.components;


import lifesim.main.game.entities.Entity;
import lifesim.main.game.items.AllItems;
import lifesim.main.game.items.Armor;
import lifesim.main.game.items.Item;
import lifesim.main.game.items.Weapon;
import lifesim.main.game.setting.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

    private ArrayList<Item> items = new ArrayList<>();

    private Item selectedItem = AllItems.standardGun;


    public Inventory() {

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
