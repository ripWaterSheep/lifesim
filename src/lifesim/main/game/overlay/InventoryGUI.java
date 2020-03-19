package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.items.inventory.Inventory;
import lifesim.main.game.entities.components.items.Item;
import lifesim.main.game.entities.components.items.inventory.InventorySlot;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.ImageLoader;

import java.awt.*;


public class InventoryGUI extends Overlay {

    private static final int SLOT_SIZE = 112;

    private static final Image bg = ImageLoader.loadImage("inventory");


    private Inventory inventory;


    public InventoryGUI(GamePanel panel, Player player) {
        super(panel, player);
        inventory = player.inventory;
    }


    public void renderBG(Graphics2D g2d) {
        DrawMethods.drawCenteredImage(g2d, bg, panel.getWidth()/2.0, panel.getHeight()/2.0,
                bg.getWidth(null), bg.getHeight(null));
    }


    void renderItems(Graphics2D g2d) {
        for (InventorySlot slot: inventory.getSlots()) {
            slot.renderItem(g2d);
        }
    }




    @Override
    void render(Graphics2D g2d) {
        //renderBG(g2d);
        //renderItems(g2d);
    }



}
