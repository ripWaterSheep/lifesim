package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.game.entities.itemEntites.DroppedItem;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.AllyType;
import lifesim.game.entities.types.FactoryType;
import lifesim.game.entities.types.ProjectileType;
import lifesim.game.handlers.World;
import lifesim.engine.output.GameWindow;
import lifesim.game.items.inventory.Inventory;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import static lifesim.util.MyMath.getRandInt;


public enum ItemType {

    HAND("Hand", new ShapeSprite(0, 0, Color.BLACK), new LaunchFunctionality(ProjectileType.FIST, -3),
            0, 0),

    STARTER_FACTORY("Starter Factory", new AnimatedSprite(new Animation("utilities", 200,
            new Vector2D(0, 0), new Vector2D(16, 16))), new SpawnFunctionality(FactoryType.STARTER), 1, 50),

    ADVANCED_FACTORY("Advanced Factory", new AnimatedSprite(new Animation("utilities", 175,
            new Vector2D(0, 64), new Vector2D(16, 16))), new SpawnFunctionality(FactoryType.ADVANCED_FACTORY), 1, 15),


    WALLBOT("Wallbot", new ImageSprite("utilities", new Vector2D(0, 32), new Vector2D(16, 16)),
            new SpawnFunctionality(AllyType.WALLBOT), 3, 125),

    BOMB("Bomb", new AnimatedSprite(new Animation("weapons", 75,
            new Vector2D(0, 0), new Vector2D(16, 16))), new LaunchFunctionality(ProjectileType.BOMB, 0),
            4, 100),

    HAMMER("Hammer", new ImageSprite("utilities", new Vector2D(0, 48), new Vector2D(16, 16)),
            new LaunchFunctionality(ProjectileType.HAMMER, 0), 1, 1);

    /*SCREWDRIVER("Screwdriver", new ImageSprite("weapons", new Vector2D(0, 32), new Vector2D(8, 8)),
            new LaunchFunctionality(ProjectileType.THROWABLE_WALL, 5), 15, 2);*/



    /** Get random item type to be used in generating loot for item packages.
     * @param luck increases chances of returning rarer loot. */
    public static ItemType getRandomLoot(int luck) {
        List<ItemType> selectionPool = new ArrayList<>();

        for (ItemType itemType: values()) {
            // More common item types appear more frequently in list and are more likely to be chosen.
            // Luck is added, so that the higher the luck is the lesser the proportion is between more common and more rare item rarities.
            for (int i = 0; i < itemType.relativeFrequency + luck; i++) {
                selectionPool.add(itemType);
            }
        }
        int randomIndex = getRandInt(0, selectionPool.size() - 1);
        return selectionPool.get(randomIndex);
    }


    public final String name;
    public final Sprite icon;
    private final ItemFunctionality functionality;
    public final int avgLootAmount; // How many items of this type will tend to appear together in package loot.
    private final int relativeFrequency; // How many times item will appear in a list that will be selected from during loot generation.


    ItemType(String name, Sprite icon, ItemFunctionality functionality, int avgLootAmount, int relativeFrequency) {
        this.name = name;
        this.icon = icon;
        this.functionality = functionality;
        this.avgLootAmount = avgLootAmount;
        this.relativeFrequency = relativeFrequency;
    }

    public DroppedItem getDroppedEntity(int amount) {
        return new DroppedItem(this, amount);
    }

    public boolean canBeUsed(World world, Player player) {
        return functionality.canBeUsed(world, player);
    }

    public void renderIcon(Graphics2D g2d, Vector2D pos) {
        icon.render(g2d, pos, new Vector2D(0, 0));
    }

    public void render(Graphics2D g2d, Player player, GameWindow window) {
        functionality.render(g2d, player, window);
    }

    public void use(World world, Player player, PlayerStats stats) {
        functionality.use(world, player, stats);
    }

}



