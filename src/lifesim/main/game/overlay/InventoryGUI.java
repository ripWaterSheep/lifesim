package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.game.entities.components.items.inventory.Inventory;
import lifesim.main.game.entities.components.items.inventory.InventoryStack;
import lifesim.main.game.entities.components.sprites.Sprite;

import javax.swing.*;
import java.awt.*;

public class InventoryGUI extends Overlay {

    private static final Sprite bg = new Sprite("inventory");

    private final Inventory inventory;

    private boolean opened = false;
    private InventoryStack draggedStack;

    public InventoryGUI(GamePanel panel, Player player) {
        super(panel, player);
        this.inventory = player.inventory;
    }


    public void dragItems(Graphics2D g2d) {
        if (MouseInputManager.left.isClicked()) {
            for (InventoryStack stack: inventory.getStacks()) {
                if (MouseInputManager.left.getPos().inRect(stack.pos, stack.pos.translate(8, 8))) {
                    draggedStack = stack;
                }
            }
        }

        if (!MouseInputManager.left.isPressed()) {
            //draggedStack.();
        }
    }



    @Override
    void render(Graphics2D g2d) {
        if (KeyInputManager.k_e.isClicked()) {
            opened = !opened;
        }


        if (opened) {
            bg.render(g2d, bg.size.scale(-0.5), new Vector2D(0, 0));

            Vector2D corner = bg.size.scale(-0.5);
            g2d.translate(corner.x, corner.y);
            dragItems(g2d);

            for (InventoryStack stack : inventory.getStacks()) {
                stack.renderItem(g2d, 1);
            }
        }
    }

}
