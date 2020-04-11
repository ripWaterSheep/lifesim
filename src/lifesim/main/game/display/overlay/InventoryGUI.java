package lifesim.main.game.display.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.controls.KeyInput;
import lifesim.main.game.controls.MouseInput;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
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

    private static final int GRID_SIZE = 12;
    private static final int ROW_WIDTH = 9;
    private static final Vector2D DISPLAY_POS = new Vector2D(0, 30);
    private static final Vector2D ITEM_OFFSET = new Vector2D(0.5-ROW_WIDTH/2.0, -1);

    private static final Sprite bg = new Sprite("inventory_test");
    private static final Sprite selectionBubble = new Sprite("selected_slot");

    private static final Font INFO_FONT = FontLoader.getMainFont(6);


    private final Inventory inventory;
    private final ArrayList<InventorySlot> slots;

    private boolean opened = false;

    private InventorySlot draggedSlot = NULL_SLOT;


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
        InventorySlot mouseOverSlot = NULL_SLOT;
        for (InventorySlot slot: inventory.getSlots()) {
            if (getSlotHitBox(slot).contains(MouseInput.getPos().toPoint())) {
                mouseOverSlot = slot;

                System.out.println(slot.getItem().name);
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
                inventory.selectSlot(mouseOverSlot);
            }

            if (MouseInput.left.isReleased()) {
                getMouseOverSlot().swapItem(draggedSlot);
                inventory.selectSlot(mouseOverSlot);
                draggedSlot = NULL_SLOT;
            }
        } else if (MouseInput.left.isReleased()) {
            inventory.selectSlot(draggedSlot);
            draggedSlot = NULL_SLOT;
            inventory.getSelectedSlot().dropItem(player.getWorld(), player.getPos().translate(15, 51));
        }


    }

    private void navigate() {
        int newIndex = slots.indexOf(inventory.getSelectedSlot());

        if (KeyInput.k_left.isClicked()) newIndex -= 1;
        if (KeyInput.k_right.isClicked()) newIndex += 1;

        newIndex += MouseInput.getMouseWheelSpeed();
        if (newIndex > slots.size() - 1)
            newIndex = 0;
        if (newIndex < 0)
            newIndex = slots.size() - 1;
        inventory.selectSlot(slots.get(newIndex));
    }



    @Override
    public void update() {
        if (KeyInput.k_e.isClicked()) opened = !opened;
        if (KeyInput.k_esc.isClicked()) opened = false;

        if (opened) dragItems();
        navigate();
    }


    @Override
    public void render(Graphics2D g2d) {
        if (!opened) {
            Vector2D miniPos = panel.getScaledSize().scale(0.5).translate(bg.getSize().scale(-0.5));
            g2d.translate(miniPos.x, miniPos.y);
            g2d.scale(0.7, 0.7);
        }

        bg.render(g2d, DISPLAY_POS, new Vector2D(0, 0));
        selectionBubble.render(g2d, getSlotDisplayPos(inventory.getSelectedSlot()), new Vector2D(0, 0));

        for (InventorySlot slot : inventory.getSlots())
            slot.getItem().render(g2d, getSlotDisplayPos(slot));

        if (!draggedSlot.isEmpty())
            draggedSlot.getItem().render(g2d, MouseInput.getPos());

        DrawMethods.drawCenteredString(g2d, inventory.getSelectedSlot().getInfo(), DISPLAY_POS.copy().translate(0, 5), INFO_FONT, Color.WHITE);
    }

}
