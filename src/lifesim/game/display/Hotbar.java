package lifesim.game.display;

import lifesim.state.engine.GamePanel;
import lifesim.game.entities.Player;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;
import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.game.items.inventory.Inventory;
import lifesim.game.items.inventory.InventorySlot;
import lifesim.util.geom.Vector2D;

import java.awt.*;
import java.util.ArrayList;


public class Hotbar extends Overlay {

    private final Vector2D displayPos = new Vector2D(0, GamePanel.HEIGHT/2.0 - 10);
    private static final Sprite SLOT_SELECTION = new ImageSprite("slot_selection");


    private final Player player;
    private final Inventory inventory;
    private final ArrayList<InventorySlot> slots;

    private final int minIndex;
    private final int maxIndex;

    private InventorySlot selectedSlot;



    public Hotbar(Player player) {
        this.player = player;
        inventory = player.inventory;
        slots = inventory.getSlots();

        minIndex = 0;
        maxIndex = minIndex + Inventory.WIDTH - 1;
        selectedSlot = inventory.getSlots().get(0);
    }


    private void navigate() {
        int newIndex = slots.indexOf(selectedSlot);

        if (KeyInput.k_left.isClicked()) newIndex -= 1;
        if (KeyInput.k_right.isClicked()) newIndex += 1;

        newIndex += MouseInput.getMouseWheelSpeed();
        if (newIndex > maxIndex)
            newIndex = minIndex;
        if (newIndex < minIndex)
            newIndex = maxIndex;
        selectedSlot = slots.get(newIndex);
    }


    @Override
    public void update() {
        navigate();
        selectedSlot.useItem(player);
        if (KeyInput.k_q.isPressed()) {
            inventory.dropItemInWorld(selectedSlot);
        }
    }

    @Override
    public void render(Graphics2D g2d) {

        for (int i = minIndex; i < maxIndex + 1; i++) {
            Vector2D itemPos = displayPos.copy().translate(((i % Inventory.WIDTH) - Inventory.WIDTH*0.5 + 0.5) * InventoryGUI.GRID_SIZE, 0);
            InventorySlot slot = slots.get(i);

            if (selectedSlot.equals(slot)) {
                SLOT_SELECTION.render(g2d, itemPos, new Vector2D(0, 0));
            }

            slot.render(g2d, itemPos, selectedSlot.equals(slot));
        }
        selectedSlot.getItem().renderOnPlayer(g2d, player);
    }

}
