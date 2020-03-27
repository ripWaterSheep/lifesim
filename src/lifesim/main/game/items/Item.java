package lifesim.main.game.items;

import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.handlers.World;

import java.awt.*;


public class Item {

    public final String name;
    public final Sprite sprite;


    public Item(String name, Sprite sprite) {
        this.name = name;
        this.sprite = sprite;
    }


    public void onClick(World world, Player player, PlayerStats stats) { }

    public void whileHolding(World world, Player player, PlayerStats stats) { }


    public void render(Graphics2D g2d, Vector2D pos) {
        sprite.render(g2d, pos.translate(sprite.getSize().scale(-0.5)), new Vector2D(0, 0));
    }

}
