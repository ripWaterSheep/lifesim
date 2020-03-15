package lifesim.main.game.items;

import lifesim.main.game.entities.DroppedItemEntity;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.setting.World;

import java.awt.*;


public class Item {

    private final String name;
    private final Sprite sprite;


    public Item(String name, Sprite sprite) {
        this.name = name;
        this.sprite = sprite;
    }


    public void drop(World world, Vector2D pos) {
        world.add(new DroppedItemEntity("Dropped " + name, sprite, pos, this));
    }


    public void onClick(World world, Entity entity) { }

    public void whilePressing(World world, Entity entity) { }

    public void whileHolding(World world, Entity entity) { }


    public void render(Graphics2D g2d, Vector2D pos) {
        sprite.render(g2d, pos, new Vector2D(0, 0));
    }

}
