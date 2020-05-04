package lifesim.game.overlay;

import lifesim.game.entities.Player;
import lifesim.state.engine.GamePanel;
import lifesim.state.engine.Main;
import lifesim.state.menus.ui.CursorType;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;
import lifesim.input.KeyInput;
import lifesim.input.MouseInput;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;
import lifesim.game.items.inventory.Inventory;
import lifesim.game.items.inventory.InventorySlot;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;

import java.awt.*;
import java.util.List;


public class InventoryGUI extends ToggleableOverlay {

    public static final int GRID_SIZE = 20;

    private static final Vector2D DISPLAY_POS = GamePanel.getCenterPos();
    private static final Sprite BG = new ImageSprite("ui/inventory");
    private static final Font INFO_FONT = FontLoader.getMainFont(8);


    private final Inventory inventory;
    private final List<InventorySlot> slots;

    private InventorySlot lastDraggedSlot = new InventorySlot();


    public InventoryGUI(Player player) {
        inventory = player.inventory;
        slots = inventory.getSlots();
    }

    // Get the position a slot should be displayed at based on it's index in the inventory's slots.
    private Vector2D getSlotDisplayPos(InventorySlot slot) {
        int index = slots.indexOf(slot);
        int x = index % inventory.width;
        int y = index / inventory.width;

        Vector2D pos = new Vector2D(x, y);
        pos.translate(0.5 - (inventory.width *0.5), -1); // Make position start in corner of inventory.
        pos.scale(GRID_SIZE);
        pos.translate(DISPLAY_POS);
        return pos;
    }


    private Rect getSlotHitBox(InventorySlot slot) {
        Vector2D pos = getSlotDisplayPos(slot);
        return new Rect(pos, new Vector2D(GRID_SIZE, GRID_SIZE));

    }

    // Get the slot that contains the mouse cursor in its hit box.
    public InventorySlot getMouseHoveringSlot() {
        InventorySlot mouseOverSlot = new InventorySlot();
        for (InventorySlot slot: inventory.getSlots()) {
            if (getSlotHitBox(slot).contains(MouseInput.getCursorPos().toPoint())) {
                mouseOverSlot = slot;
                break;
            }
        }
        return mouseOverSlot;
    }


    private void moveItems() {
        InventorySlot hoveringSlot = getMouseHoveringSlot();
        if (slots.contains(hoveringSlot)) {
            if (!hoveringSlot.isEmpty()) { // Show interaction mouse cursor only if the slot contains an item to move.
                Main.getWindow().changeCursor(CursorType.POINTER);

                if (MouseInput.left.isClicked()) {
                    lastDraggedSlot = hoveringSlot;

                    if (KeyInput.k_shift.isPressed()) {
                        lastDraggedSlot.swapItem(inventory.getFirstEmptySlot());
                    }
                }
            }

            if (MouseInput.left.isReleased()) {
                hoveringSlot.swapItem(lastDraggedSlot);
                lastDraggedSlot = new InventorySlot();
            }

        } else if (MouseInput.left.isReleased()) {
            inventory.dropItemInWorld(lastDraggedSlot);
            lastDraggedSlot = new InventorySlot();
        }
    }


    @Override
    public void start() {
    }


    @Override
    public void update() {
        moveItems();
        if (KeyInput.k_esc.isClicked()) {
            hide();
        }
    }


    @Override
    public void render(Graphics2D g2d) {
        GraphicsMethods.fillPanel(g2d, new Color(0, 0, 0, 75));

        BG.render(g2d, DISPLAY_POS, new Vector2D(0, 0));

        for (InventorySlot slot : inventory.getSlots()) {
            slot.render(g2d, getSlotDisplayPos(slot), false);
        }

        GraphicsMethods.centeredString(g2d, lastDraggedSlot.getInfo(),
                DISPLAY_POS.copy().translate(0, BG.getSize().intY()/2.0 + 8), INFO_FONT, Color.WHITE);

        if (!lastDraggedSlot.isEmpty()) {
            lastDraggedSlot.getItem().renderIcon(g2d, MouseInput.getCursorPos());
        }
    }


}
