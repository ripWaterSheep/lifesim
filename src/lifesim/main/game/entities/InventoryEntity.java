package lifesim.main.game.entities;

import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.components.Inventory;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.stats.Stats;
import lifesim.main.game.items.Item;
import lifesim.main.game.setting.World;

import java.awt.*;

public class InventoryEntity extends MovementEntity {

    private final Inventory inventory = new Inventory();


    public InventoryEntity(String name, Sprite sprite, double speed, Stats stats) {
        super(name, sprite, speed, stats);
    }


    public void acquireItem(Item item) {
        inventory.addItem(item);
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
