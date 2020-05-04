package lifesim.game.items;

import lifesim.game.entities.Player;
import lifesim.game.entities.itemEntites.DroppedItem;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.types.AllyType;
import lifesim.game.entities.types.FactoryType;
import lifesim.game.entities.types.ProjectileType;
import lifesim.game.handlers.World;
import lifesim.state.engine.GameWindow;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.*;

import java.awt.*;


public enum ItemType {

    HAND("Hand", new ShapeSprite(0, 0, Color.BLACK), new LaunchFunctionality(ProjectileType.FIST, -3), 0),

    STARTER_FACTORY("Starter Factory", new AnimatedSprite(new Animation("utilities", 250,
            new Vector2D(0, 0), new Vector2D(16, 16))), new SpawnFunctionality(FactoryType.STARTER), 2),

    WALLBOT("Wallbot", new ImageSprite("utilities", new Vector2D(0, 32), new Vector2D(16, 16)),
            new SpawnFunctionality(AllyType.WALLBOT), 2),

    BOMB("Bomb", new AnimatedSprite(new Animation("weapons", 75,
            new Vector2D(0, 0), new Vector2D(16, 16))), new LaunchFunctionality(ProjectileType.BOMB, 0), 2),

    //HAMMER("")

    SCREWDRIVER("Screwdriver", new ImageSprite("weapons", new Vector2D(0, 32), new Vector2D(8, 8)),
            new LaunchFunctionality(ProjectileType.THROWABLE_WALL, 5), 2);




    public final String name;
    public final Sprite icon;
    private final ItemFunctionality functionality;
    public final int relativeRarity; // How many times item will appear in a list that will be selected from during loot generation.


    ItemType(String name, Sprite icon, ItemFunctionality functionality, int relativeRarity) {
        this.name = name;
        this.icon = icon;
        this.functionality = functionality;
        this.relativeRarity = relativeRarity;
    }

    public DroppedItem getDroppedEntity(int amount) {
        return new DroppedItem(this, amount);
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


