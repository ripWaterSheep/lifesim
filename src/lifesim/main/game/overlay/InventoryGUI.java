package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.items.AllItems;
import lifesim.main.game.items.inventory.Inventory;
import lifesim.main.game.items.inventory.ItemStack;
import lifesim.main.game.entities.components.sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;

public class InventoryGUI extends Overlay {

    private static final int GRID_SIZE = 1;

    private static final Sprite bg = new Sprite("inventory");
    private static final Sprite selectedBubble = new Sprite("selected_slot");

    // Define the edges of the inside of the inventory.
    public static final Vector2D inventoryBounds = new Vector2D(bg.getSize().scale(0.48, 0.3).translate(0, 5));


    private final Inventory inventory;

    private boolean opened = false;
    private ItemStack draggedStack;


    public InventoryGUI(GamePanel panel, Player player) {
        super(panel, player);
        this.inventory = player.inventory;
    }


    @Override
    public void update() {
        if (KeyInputManager.k_e.isClicked()) opened = !opened;
        if (KeyInputManager.k_esc.isClicked()) opened = false;

        if (opened) dragItems();
        scrollThroughItems();
    }


    private void dragItems() {
        Vector2D mousePos = MouseInputManager.left.getScaledPos();

        if (MouseInputManager.left.isClicked()) {
            for (ItemStack stack: inventory.getStacks()) {
                if (stack.getItem().sprite.containsPointAt(mousePos, stack.inventoryPos)) {
                    draggedStack = stack;
                    inventory.bringStackToFront(stack);
                    inventory.selectStack(stack);
                }
            }
        }
        if (draggedStack != null) {
            draggedStack.drag(mousePos, inventoryBounds);

            if (!MouseInputManager.left.isPressed() && draggedStack != null) {
                draggedStack = null;
            }
        }
    }


    private void scrollThroughItems() {
        if (inventory.getStacks().size() > 0) {
            ArrayList<ItemStack> stacks = inventory.getStacks();
            int newIndex = stacks.indexOf(inventory.getSelectedStack());
            newIndex += MouseInputManager.getMouseWheelSpeed();
            if (newIndex > stacks.size() - 1)
                newIndex = 0;
            if (newIndex < 0)
                newIndex = stacks.size() - 1;
            inventory.selectStack(stacks.get(newIndex));
        } else {
            inventory.selectNothing();
        }
    }



    @Override
    public void render(Graphics2D g2d) {
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f);
        g2d.setComposite(ac);

        if (!opened) {
            g2d.translate(panel.getScaledWidth()/2.0 - inventoryBounds.x * 0.75, -panel.getScaledHeight()/2.0 + inventoryBounds.y * 0.85);
            g2d.scale(0.6, 0.6);
        }
        bg.render(g2d, new Vector2D(0, 0), new Vector2D(0, 0));

        // Draw the stack selection bubble over the selected stack, and display item details under the inventory
        ItemStack selectedStack = inventory.getSelectedStack();
        if (selectedStack.getItem() != AllItems.empty) {
            selectedBubble.render(g2d, selectedStack.inventoryPos, new Vector2D(0, 0));
            selectedStack.renderDetailsAt(g2d, inventoryBounds.translate(-inventoryBounds.x-4, 5));
        }

        for (ItemStack stack : inventory.getStacks())
            stack.render(g2d);
    }

}
