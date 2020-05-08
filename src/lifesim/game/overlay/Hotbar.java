package lifesim.game.overlay;

import lifesim.game.entities.Player;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;
import lifesim.engine.input.KeyInput;
import lifesim.engine.input.MouseInput;
import lifesim.game.items.inventory.Inventory;
import lifesim.game.items.inventory.InventorySlot;
import lifesim.util.geom.Vector2D;
import lifesim.engine.output.GameWindow;

import java.awt.*;
import java.util.List;

import static lifesim.engine.output.GamePanel.WIDTH;


public class Hotbar extends Overlay {

    private static final Sprite BG = new ImageSprite("ui/hotbar");
    private static final Sprite SLOT_SELECTION = new ImageSprite("ui/slot_selection");

    private static final Vector2D DISPLAY_POS = new Vector2D(WIDTH/2.0, BG.getSize().y/2 - 1);


    private final Player player;
    private final Inventory inventory;
    private final List<InventorySlot> slots;

    private final int minIndex;
    private final int maxIndex;

    private InventorySlot selectedSlot;

    private final GameWindow window;

    public Hotbar(Player player, GameWindow window) {
        this.player = player;
        inventory = player.inventory;
        slots = inventory.getSlots();

        minIndex = 0;
        maxIndex = minIndex + inventory.width - 1;
        selectedSlot = inventory.getSlots().get(0);

        this.window = window;
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
        BG.render(g2d, DISPLAY_POS, new Vector2D(0, 0));

        for (int i = minIndex; i < maxIndex + 1; i++) {
            double itemX = ((i % inventory.width) - inventory.width/2.0) + 0.5;
            itemX *= InventoryGUI.GRID_SIZE;
            Vector2D itemPos = DISPLAY_POS.copy().translate(itemX, 0);

            InventorySlot slot = slots.get(i);

            if (selectedSlot.equals(slot)) {
                SLOT_SELECTION.render(g2d, itemPos, new Vector2D(0, 0));
            }

            slot.render(g2d, itemPos, selectedSlot.equals(slot));
        }
        selectedSlot.getItem().render(g2d, player, window);
    }

}
