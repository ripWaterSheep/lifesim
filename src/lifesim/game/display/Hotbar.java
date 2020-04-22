package lifesim.game.display;

import lifesim.engine.Main;
import lifesim.game.entities.Player;
import lifesim.game.entities.components.sprites.ImageSprite;
import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.game.items.inventory.Inventory;
import lifesim.game.items.inventory.InventorySlot;
import lifesim.util.math.Vector2D;

import java.awt.*;
import java.util.ArrayList;


public class Hotbar extends GameDisplay {

    private static final Sprite SLOT_SELECTION = new ImageSprite("slot_selection");


    private final Inventory inventory;
    private final ArrayList<InventorySlot> slots;

    private final int minIndex;
    private final int maxIndex;


    public Hotbar(Player player) {
        this.inventory = player.inventory;
        slots = inventory.getSlots();

        minIndex = Inventory.SIZE-Inventory.WIDTH;
        maxIndex = minIndex + Inventory.WIDTH - 1;
    }


    private void navigate() {
        int newIndex = slots.indexOf(inventory.getSelectedSlot());

        if (KeyInput.k_left.isClicked()) newIndex -= 1;
        if (KeyInput.k_right.isClicked()) newIndex += 1;

        newIndex += MouseInput.getMouseWheelSpeed();
        if (newIndex > maxIndex)
            newIndex = minIndex;
        if (newIndex < minIndex)
            newIndex = maxIndex;
        inventory.selectSlot(slots.get(newIndex));
    }


    @Override
    public void update() {
        navigate();
    }

    @Override
    public void render(Graphics2D g2d) {
        Vector2D displayPos = new Vector2D(0, Main.getPanel().getScaledHeight()/2.0 - 10);


        for (int i = minIndex; i < maxIndex; i++) {
            Vector2D itemPos = displayPos.copy().translate(((i % Inventory.WIDTH) - Inventory.WIDTH*0.5 + 0.5) * InventoryGUI.GRID_SIZE, 0);
            InventorySlot slot = slots.get(i);

            if (inventory.getSelectedSlot().equals(slot)) {
                SLOT_SELECTION.render(g2d, itemPos, new Vector2D(0, 0));
            }

            slot.render(g2d, itemPos, inventory.getSelectedSlot().equals(slot));
        }
    }

}
