package lifesim.main.game.items;

import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;

import java.awt.*;


public abstract class Item {

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

}
