package lifesim.game.display;

import lifesim.game.entities.Player;
import lifesim.game.entities.components.sprites.ImageSprite;
import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.util.math.geom.Rect;
import lifesim.util.math.geom.Vector2D;
import lifesim.game.items.inventory.Inventory;
import lifesim.game.items.inventory.InventorySlot;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;

import java.awt.*;
import java.util.ArrayList;

import static lifesim.game.items.inventory.Inventory.NULL_SLOT;
import static lifesim.game.items.inventory.Inventory.WIDTH;


public class InventoryGUI extends ToggleableDisplay {

    public static final int GRID_SIZE = 12;
    public static final double DISPLAY_SCALE = 1.5;

    private static final Vector2D DISPLAY_POS = new Vector2D(0, 0);
    private static final Vector2D ITEM_OFFSET = new Vector2D(0.5 - (Inventory.WIDTH*0.5), -1);

    private static final Sprite BG = new ImageSprite("inventory_bg");
    private static final Sprite FG = new ImageSprite("inventory_fg");
    private static final Font INFO_FONT = FontLoader.getMainFont(4);


    private final Player player;

    private final Inventory inventory;
    private final ArrayList<InventorySlot> slots;

    private InventorySlot lastDraggedSlot = NULL_SLOT;


    public InventoryGUI(Player player) {
        this.player = player;
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
        int x = index % WIDTH;
        int y = index / WIDTH;
        return getDisplayPos(new Vector2D(x, y));
    }


    private Rect getSlotHitBox(InventorySlot slot) {
        Vector2D pos = getSlotDisplayPos(slot);
        return new Rect(pos.scale(DISPLAY_SCALE), new Vector2D(GRID_SIZE, GRID_SIZE).scale(DISPLAY_SCALE));

    }

    public InventorySlot getMouseOverSlot() {
        InventorySlot mouseOverSlot = NULL_SLOT;
        for (InventorySlot slot: inventory.getSlots()) {
            if (getSlotHitBox(slot).contains(MouseInput.getCursorPos().toPoint())) {
                mouseOverSlot = slot;
                break;
            }
        }
        return mouseOverSlot;
    }


    private void moveItems() {
        InventorySlot mouseOverSlot = getMouseOverSlot();
        if (!mouseOverSlot.equals(NULL_SLOT)) {

            if (MouseInput.left.isClicked() && !mouseOverSlot.isEmpty()) {
                lastDraggedSlot = mouseOverSlot;
                if (KeyInput.k_shift.isPressed()) {
                    lastDraggedSlot.swapItem(inventory.getFirstEmptySlot());
                }
            }

            if (MouseInput.left.isReleased()) {
                getMouseOverSlot().swapItem(lastDraggedSlot);
                lastDraggedSlot = NULL_SLOT;
            }
        } else if (MouseInput.left.isReleased()) {
            inventory.selectSlot(lastDraggedSlot);
            lastDraggedSlot = NULL_SLOT;
            inventory.getSelectedSlot().dropItem(player.getWorld(), player.getPos().translate(MouseInput.getCursorPos()));
        }
    }


    @Override
    public void whenFirstShown() {

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
        g2d.scale(DISPLAY_SCALE, DISPLAY_SCALE);

        BG.render(g2d, DISPLAY_POS, new Vector2D(0, 0));

        for (InventorySlot slot : inventory.getSlots()) {
            slot.render(g2d, getSlotDisplayPos(slot), false);
        }
        FG.render(g2d, DISPLAY_POS, new Vector2D(0, 0));

        if (!lastDraggedSlot.isEmpty()) {
            lastDraggedSlot.getItem().renderIcon(g2d, MouseInput.getCursorPos().scale(1.0/DISPLAY_SCALE));
        }

        GraphicsMethods.centeredString(g2d, lastDraggedSlot.getInfo(),
                DISPLAY_POS.copy().translate(0, 20 * DISPLAY_SCALE), INFO_FONT, Color.WHITE);
    }

}
