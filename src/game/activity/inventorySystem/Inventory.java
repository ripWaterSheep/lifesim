package game.activity.inventorySystem;

import game.components.entities.player.Player;
import game.components.entities.stats.PlayerStats;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Item> items;


    public Inventory() {
        PlayerStats stats = Player.getInstance().getStats();
        items = new ArrayList<>();
        items.add(
                new Item("Bandage", 10) {
                    void onClick() {
                        stats.heal(1);
                    }
                });
        items.add(
                new Item("Energy Drink", 20) {
                    void onClick() {
                        stats.energize(10);
                    }
                });

    }




}
