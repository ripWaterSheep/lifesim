package lifesim.main.game.entities;

import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.components.items.Inventory;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.game.setting.World;

import java.awt.*;

import static lifesim.main.util.math.MyMath.getRand;

public class InventoryEntity extends MovementEntity {

    protected final Inventory inventory = new Inventory();


    public InventoryEntity(String name, Sprite sprite, Stats stats, double speed) {
        super(name, sprite, stats, speed);
    }


    public InventoryEntity acquireItem(Item item) {
        inventory.addItem(item);
        return this;
    }


    @Override
    public void onRemoval(World world) {
        super.onRemoval(world);
        for (Item item: inventory.getItems()) {
            Vector2D dropPos = new Vector2D(0, 0);
            dropPos.setMagnDir(getRand(1, 10), getRand(0, 360));
            world.add(item.getDroppedEntity(), dropPos);
        }
    }

    @Override
    public void update(World world) {
        super.update(world);
        if (MouseInputManager.right.isClicked()) {
            inventory.clickSelectedItem(pos, world, this);
        }
    }


    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        inventory.renderSelectedItemsAt(g2d, pos);
    }

}
