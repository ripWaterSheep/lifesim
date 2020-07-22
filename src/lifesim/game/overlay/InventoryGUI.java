package lifesim.game.overlay;

import lifesim.io.output.GameWindow;
import lifesim.game.entities.Player;
import lifesim.io.output.GamePanel;
import lifesim.io.output.CursorType;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;
import lifesim.io.input.KeyInput;
import lifesim.io.input.MouseInput;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;
import lifesim.game.item.inventory.Inventory;
import lifesim.game.item.inventory.InventorySlot;
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
    private final GameWindow window;

    private InventorySlot lastDraggedSlot;


    public InventoryGUI(Player player, GameWindow window) {
        inventory = player.inventory;
        lastDraggedSlot = new InventorySlot(inventory);
        slots = inventory.getSlots();
        this.window = window;
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


    private Rect getSlotHitbox(InventorySlot slot) {
        Vector2D pos = getSlotDisplayPos(slot);
        return new Rect(pos, new Vector2D(GRID_SIZE, GRID_SIZE));

    }

    // Get the slot that contains the mouse cursor in its hit box.
    public InventorySlot getMouseHoveringSlot() {
        InventorySlot mouseOverSlot = new InventorySlot(inventory);
        for (InventorySlot slot: inventory.getSlots()) {
            if (getSlotHitbox(slot).contains(MouseInput.getCursorPos().toPoint())) {
                mouseOverSlot = slot;
                break;
            }
        }
        return mouseOverSlot;
    }


    private void moveItems() {
        InventorySlot hoveringSlot = getMouseHoveringSlot();
        if (slots.contains(hoveringSlot)) {
            if (!hoveringSlot.isEmpty()) {
                window.changeCursor(CursorType.POINTER); // Show pointer mouse cursor only if slot contains an item to move.

                if (MouseInput.left.isClicked()) {
                    lastDraggedSlot = hoveringSlot;
                }
            }

            if (MouseInput.left.isReleased()) {
                hoveringSlot.swapItem(lastDraggedSlot);
                lastDraggedSlot = new InventorySlot(inventory);
            }

        } else if (MouseInput.left.isReleased()) {
            inventory.dropItemInWorld(lastDraggedSlot);
            lastDraggedSlot = new InventorySlot(inventory);
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
