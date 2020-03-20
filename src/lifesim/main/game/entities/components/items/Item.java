package lifesim.main.game.entities.components.items;

import lifesim.main.game.entities.DroppedItemEntity;
import lifesim.main.game.entities.Entity;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.handlers.World;

import java.awt.*;


public class Item {

    private final String name;
    public final Sprite sprite;


    public Item(String name, Sprite sprite) {
        this.name = name;
        this.sprite = sprite;
    }

    public DroppedItemEntity getDroppedEntity() {
       return new DroppedItemEntity("Dropped " + name, sprite, this);
    }


    public void onClick(World world, Entity entity) { }

    public void whilePressing(World world, Entity entity) { }

    public void whileHolding(World world, Entity entity) { }


    public void render(Graphics2D g2d, Vector2D pos) {
        sprite.render(g2d, pos.translate(sprite.size.scale(-0.5)), new Vector2D(0, 0));
    }

}
