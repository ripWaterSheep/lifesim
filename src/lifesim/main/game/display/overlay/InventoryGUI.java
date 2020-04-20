package lifesim.main.game.display.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.sprites.ImageSprite;
import lifesim.main.game.input.KeyInput;
import lifesim.main.game.input.MouseInput;
import lifesim.main.util.math.Vector2D;
import lifesim.main.game.entities.components.sprites.Sprite;
import lifesim.main.game.items.inventory.Inventory;
import lifesim.main.game.items.inventory.InventorySlot;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;
import lifesim.main.util.math.Geometry;

import java.awt.*;
import java.util.ArrayList;

import static lifesim.main.game.items.inventory.Inventory.NULL_SLOT;


public class InventoryGUI extends Overlay {

    public static final int GRID_SIZE = 12;
    private static final int ROW_WIDTH = 9;

    private static final Vector2D DISPLAY_POS = new Vector2D(0, 30);
    private static final Vector2D ITEM_OFFSET = new Vector2D(0.5-ROW_WIDTH/2.0, -1);

    private static final Sprite BG = new ImageSprite("inventory_test");

    private static final Font INFO_FONT = FontLoader.getMainFont(6);


    private final Inventory inventory;
    private final HotbarGUI hotbarGUI;
    private final ArrayList<InventorySlot> slots;

    private InventorySlot draggedSlot = NULL_SLOT;

    private boolean opened = false;


    public InventoryGUI(GamePanel panel, Player player) {
        super(panel, player);
        inventory = player.inventory;
        slots = inventory.getSlots();
        hotbarGUI = new HotbarGUI(panel, player, inventory, GRID_SIZE, ROW_WIDTH);
    }


    private Vector2D getDisplayPos(Vector2D pos) {
        pos.translate(ITEM_OFFSET);
        pos.scale(GRID_SIZE);
        pos.translate(DISPLAY_POS);

        return pos;
    }

    private Vector2D getSlotDisplayPos(InventorySlot slot) {
        int index = slots.indexOf(slot);
        int x = index % ROW_WIDTH;
        int y = index / ROW_WIDTH;
        return getDisplayPos(new Vector2D(x, y));
    }


    private Rectangle getSlotHitBox(InventorySlot slot) {
        Vector2D pos = getSlotDisplayPos(slot);
        return Geometry.getCenteredRect(pos, new Vector2D(GRID_SIZE, GRID_SIZE));
    }

    public InventorySlot getMouseOverSlot() {
        InventorySlot mouseOverSlot = NULL_SLOT;
        for (InventorySlot slot: inventory.getSlots()) {
            if (getSlotHitBox(slot).contains(MouseInput.getPos().toPoint())) {
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
                //inventory.selectSlot(mouseOverSlot);
            }

            if (MouseInput.left.isReleased()) {
                getMouseOverSlot().swapItem(draggedSlot);
                //inventory.selectSlot(mouseOverSlot);
                draggedSlot = NULL_SLOT;
            }
        } else if (MouseInput.left.isReleased()) {
            inventory.selectSlot(draggedSlot);
            draggedSlot = NULL_SLOT;
            inventory.getSelectedSlot().dropItem(player.getWorld(), player.getPos().translate(MouseInput.getPos()));
        }
    }



    @Override
    public void update() {
        if (KeyInput.k_e.isClicked()) opened = !opened;
        if (KeyInput.k_esc.isClicked()) opened = false;

        if (opened) dragItems();
        hotbarGUI.update();
    }


    @Override
    public void render(Graphics2D g2d) {
        if (opened) {
            DrawMethods.fillPanel(g2d, panel, new Color(0, 0, 0, 100));

            BG.render(g2d, DISPLAY_POS, new Vector2D(0, 0));

            for (InventorySlot slot : inventory.getSlots()) {
                slot.render(g2d, getSlotDisplayPos(slot), true);
            }
            if (!draggedSlot.isEmpty()) {
                draggedSlot.getItem().renderIcon(g2d, MouseInput.getPos());
            }

            DrawMethods.drawCenteredString(g2d, inventory.getSelectedSlot().getInfo(),
                    DISPLAY_POS.copy().translate(0, 25), INFO_FONT, Color.WHITE);
        }

        hotbarGUI.render(g2d);
    }

}
