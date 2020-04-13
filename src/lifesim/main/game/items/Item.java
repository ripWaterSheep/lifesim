package lifesim.main.game.items;

import lifesim.main.game.entities.DroppedItem;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.types.Spawnable;
import lifesim.main.util.math.Vector2D;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;

import java.awt.*;


public abstract class Item implements Spawnable {

    public final String name;
    public final Sprite sprite;


    public Item(String name, Sprite sprite) {
        this.name = name;
        this.sprite = sprite;
    }


    public abstract void use(World world, Player player, PlayerStats stats);

    public void render(Graphics2D g2d, Vector2D pos) {
        sprite.render(g2d, pos, new Vector2D(0, 0));
    }

    @Override
    public Entity spawnEntity() {
        return new DroppedItem(this, 1);
    }
}
