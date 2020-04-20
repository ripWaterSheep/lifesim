package lifesim.main.game.display.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.input.KeyInput;
import lifesim.main.game.input.MouseInput;
import lifesim.main.game.items.inventory.Inventory;
import lifesim.main.game.items.inventory.InventorySlot;
import lifesim.main.util.math.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class HotbarGUI extends Overlay {

    private static final Sprite SELECTED_SLOT = new Sprite("selected_slot");


    private final Inventory inventory;
    private final ArrayList<InventorySlot> slots;

    private final int width;
    private final int gridSize;

    public HotbarGUI(GamePanel panel, Player player, Inventory inventory, int gridSize, int width) {
        super(panel, player);
        this.inventory = inventory;
        slots = inventory.getSlots();
        this.gridSize = gridSize;
        this.width = width;
    }


    private void navigate() {
        int newIndex = slots.indexOf(inventory.getSelectedSlot());

        if (KeyInput.k_left.isClicked()) newIndex -= 1;
        if (KeyInput.k_right.isClicked()) newIndex += 1;

        int min = 0;
        int max = width - 1;
        newIndex += MouseInput.getMouseWheelSpeed();
        if (newIndex > max)
            newIndex = min;
        if (newIndex < min)
            newIndex = max;
        inventory.selectSlot(slots.get(newIndex));
    }


    @Override
    public void update() {
        navigate();
    }

    @Override
    public void render(Graphics2D g2d) {
        Vector2D displayPos = new Vector2D(0, panel.getScaledHeight()/2.0 - 10);


        for (int i = 0; i < width; i++) {
            Vector2D itemPos = displayPos.copy().translate(((i % width) - width/2.0 + 0.5) * gridSize, 0);
            InventorySlot slot = slots.get(i);

            if (inventory.getSelectedSlot().equals(slot)) {
                SELECTED_SLOT.render(g2d, itemPos, new Vector2D(0, 0));
            }

            slot.getItem().render(g2d, itemPos);

        }


    }

}
