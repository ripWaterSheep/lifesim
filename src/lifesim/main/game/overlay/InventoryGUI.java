package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.items.AllItems;
import lifesim.main.game.entities.components.items.inventory.Inventory;
import lifesim.main.game.entities.components.items.inventory.InventoryStack;
import lifesim.main.game.entities.components.sprites.Sprite;

import java.awt.*;

public class InventoryGUI extends Overlay {

    private static final Sprite bg = new Sprite("inventory");
    private static final Sprite selectedSlot = new Sprite("selected_slot");

    // Define the edges of the inside of the inventory.
    public static final Vector2D inventoryBounds = new Vector2D(bg.size.scale(0.48, 0.3).translate(0, 5));


    private final Inventory inventory;

    private boolean opened = false;
    private InventoryStack draggedStack;

    public InventoryGUI(GamePanel panel, Player player) {
        super(panel, player);
        this.inventory = player.inventory;
    }


    @Override
    public void update() {
        if (KeyInputManager.k_e.isClicked()) opened = !opened;
        if (KeyInputManager.k_esc.isClicked()) opened = false;

        dragItems();
    }


    public void dragItems() {
        Vector2D mousePos = MouseInputManager.left.getPos();

        if (MouseInputManager.left.isClicked()) {
            for (InventoryStack stack: inventory.getStacks()) {
                if (stack.getItem().sprite.containsPointAt(mousePos, stack.pos)) {
                    draggedStack = stack;
                    inventory.bringStackToFront(stack);
                    inventory.selectStack(stack);
                }
            }
        }

        if (draggedStack != null) {
            draggedStack.drag(mousePos, inventoryBounds);

            if (!MouseInputManager.left.isPressed() && draggedStack != null) {
                draggedStack.release();
                draggedStack = null;
            }
        }
    }



    @Override
    protected void render(Graphics2D g2d) {
        if (opened) {
            bg.render(g2d, new Vector2D(0, 0), new Vector2D(0, 0));

            if (draggedStack != null) {
                selectedSlot.render(g2d, draggedStack.dragPos, new Vector2D(0, 0));
            } else if (inventory.getSelectedItem() != AllItems.empty)
                selectedSlot.render(g2d, inventory.getSelectedStack().pos, new Vector2D(0, 0));

            for (InventoryStack stack : inventory.getStacks()) {
                stack.renderItem(g2d);
            }
        }
    }

}
