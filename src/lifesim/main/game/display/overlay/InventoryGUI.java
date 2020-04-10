package lifesim.main.game.display.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.controls.KeyInput;
import lifesim.main.game.controls.MouseInput;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.items.inventory.Inventory;
import lifesim.main.game.items.inventory.InventorySlot;
import lifesim.main.util.math.Geometry;

import java.awt.*;
import java.util.ArrayList;


public class InventoryGUI extends Overlay {

    private static final int GRID_SIZE = 10;
    private static final int ROW_WIDTH = 10;

    private static final Vector2D DISPLAY_POS = new Vector2D(0, 0);
    private static final Vector2D ITEM_OFFSET = new Vector2D(0.5-ROW_WIDTH/2.0, -1);


    private static final Sprite bg = new Sprite("InventoryTest");
    private static final Sprite selectionBubble = new Sprite("selected_slot");


    private final Inventory inventory;
    private final ArrayList<InventorySlot> slots;

    private boolean opened = false;

    private InventorySlot draggedSlot = Inventory.NULL_SLOT;


    public InventoryGUI(GamePanel panel, Player player) {
        super(panel, player);
        inventory = player.inventory;
        slots = inventory.getSlots();
    }


    private Vector2D getDisplayPos(Vector2D pos) {
        pos.translate(ITEM_OFFSET);
        pos.scale(GRID_SIZE);
        pos.translate(DISPLAY_POS);

        return pos;
    }

    private Vector2D getSlotDisplayPos(InventorySlot slot) {
        int index = slots.indexOf(slot);
        int y = index / ROW_WIDTH;
        int x = index % ROW_WIDTH;
        return getDisplayPos(new Vector2D(x, y));
    }


    private Rectangle getSlotHitBox(InventorySlot slot) {
        Vector2D pos = getSlotDisplayPos(slot);
        return Geometry.getCenteredRect(pos, new Vector2D(GRID_SIZE, GRID_SIZE));
    }

    public InventorySlot getMouseOverSlot() {
        InventorySlot mouseOverSlot = Inventory.NULL_SLOT;
        for (InventorySlot slot: inventory.getSlots()) {
            if (getSlotHitBox(slot).contains(MouseInput.getPos().toPoint())) {
                mouseOverSlot = slot;
            }
        }
        return mouseOverSlot;
    }


    private void dragItems() {
        if (MouseInput.left.isPressed())
            inventory.selectSlot(getMouseOverSlot());
    }



    @Override
    public void update() {
        if (KeyInput.k_e.isClicked()) opened = !opened;
        if (KeyInput.k_esc.isClicked()) opened = false;

        if (opened) {
            dragItems();
        }
    }


    @Override
    public void render(Graphics2D g2d) {
        if (opened) {
            bg.render(g2d, DISPLAY_POS, new Vector2D(0, 0));

            if (!inventory.isSelectingNothing())
                selectionBubble.render(g2d, getSlotDisplayPos(inventory.getSelectedSlot()), new Vector2D(0, 0));

            for (InventorySlot slot : inventory.getSlots())
                slot.getItem().render(g2d, getSlotDisplayPos(slot));

        }
    }

}
