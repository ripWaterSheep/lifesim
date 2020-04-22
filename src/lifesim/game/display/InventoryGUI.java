package lifesim.game.display;

import lifesim.game.entities.Player;
import lifesim.game.entities.components.sprites.ImageSprite;
import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.input.MouseInput;
import lifesim.util.math.Vector2D;
import lifesim.game.items.inventory.Inventory;
import lifesim.game.items.inventory.InventorySlot;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.math.Geometry;

import java.awt.*;
import java.util.ArrayList;

import static lifesim.game.items.inventory.Inventory.NULL_SLOT;
import static lifesim.game.items.inventory.Inventory.WIDTH;


public class InventoryGUI extends ToggleableDisplay {

    public static final int GRID_SIZE = 12;

    private static final Vector2D DISPLAY_POS = new Vector2D(0, 30);
    private static final Vector2D ITEM_OFFSET = new Vector2D(0.5 - (Inventory.WIDTH*0.5), -1);

    private static final Sprite BG = new ImageSprite("inventory_test");
    private static final Font INFO_FONT = FontLoader.getMainFont(6);


    private final Player player;

    private final Inventory inventory;
    private final ArrayList<InventorySlot> slots;

    private InventorySlot draggedSlot = NULL_SLOT;


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


    private Rectangle getSlotHitBox(InventorySlot slot) {
        Vector2D pos = getSlotDisplayPos(slot);
        return Geometry.getCenteredRect(pos, new Vector2D(GRID_SIZE, GRID_SIZE));
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


    private void dragItems() {
        InventorySlot mouseOverSlot = getMouseOverSlot();
        if (!mouseOverSlot.equals(NULL_SLOT)) {

            if (MouseInput.left.isClicked() && !mouseOverSlot.isEmpty()) {
                draggedSlot = mouseOverSlot;
            }

            if (MouseInput.left.isReleased()) {
                getMouseOverSlot().swapItem(draggedSlot);
                draggedSlot = NULL_SLOT;
            }
        } else if (MouseInput.left.isReleased()) {
            inventory.selectSlot(draggedSlot);
            draggedSlot = NULL_SLOT;
            inventory.getSelectedSlot().dropItem(player.getWorld(), player.getPos().translate(MouseInput.getCursorPos()));
        }
    }



    @Override
    public void update() {
        dragItems();
    }


    @Override
    public void render(Graphics2D g2d) {
        GraphicsMethods.fillPanel(g2d, new Color(0, 0, 0, 75));
        BG.render(g2d, DISPLAY_POS, new Vector2D(0, 0));

        for (InventorySlot slot : inventory.getSlots()) {
            slot.render(g2d, getSlotDisplayPos(slot), true);
        }
        if (!draggedSlot.isEmpty()) {
            draggedSlot.getItem().renderIcon(g2d, MouseInput.getCursorPos());
        }

        GraphicsMethods.centeredString(g2d, inventory.getSelectedSlot().getInfo(),
                DISPLAY_POS.copy().translate(0, 25), INFO_FONT, Color.WHITE);
    }

}
