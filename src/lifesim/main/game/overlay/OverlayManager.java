package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;

import java.awt.*;
import java.util.ArrayList;


public class OverlayManager extends Overlay {

    private ArrayList<Overlay> overlays = new ArrayList<>();

    public OverlayManager(GamePanel panel, Player player) {
        super(panel, player);
        overlays.add(new StatBar(panel, player));
        overlays.add(new DeathScreen(panel, player));
       overlays.add(new InventoryGUI(panel, player));
    }


        public void render(Graphics2D g2d) {
        for (Overlay overlay: overlays) {
            g2d = (Graphics2D) g2d.create();
            overlay.render(g2d);
        }
    }
}
