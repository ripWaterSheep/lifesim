package lifesim.main.game.menus;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;
import lifesim.main.game.items.*;
import lifesim.main.game.overlay.Overlay;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static lifesim.main.game.items.AllItems.*;


public class ItemCompendium extends Overlay {

    private final ArrayList<Consumable> consumables = new ArrayList<>();
    private final ArrayList<Weapon> weapons = new ArrayList<>();
    private final ArrayList<Item> utilities = new ArrayList<>();

    private int currentPage = 0;
    private ArrayList<? extends Item> currentSection = consumables;


    public ItemCompendium(GamePanel panel, Player player) {
        super(panel, player);

        consumables.add(bread);
        consumables.add(banana);
        consumables.add(virtualCoin);
        consumables.add(mysteriousPill);

        weapons.add(waterGun);
        weapons.add(laserGun);
        weapons.add(bomb);

        utilities.add(jetPack);
    }


    @Override
    public void update() {
    }


    @Override
    public void render(Graphics2D g2d) {
        for (Item item: currentSection) {
        }
    }

}
