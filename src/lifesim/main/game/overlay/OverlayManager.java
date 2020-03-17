package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;

import java.awt.*;
import java.util.ArrayList;


public class OverlayManager extends Overlay {

    ArrayList<Overlay> overlays = new ArrayList<>();


    public OverlayManager(GamePanel panel, Player player) {
        super(panel, player);
        overlays.add(new StatBar(panel, player));
        //overlays.add(new DeathScreen());
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        for (Overlay overlay: overlays) {
            overlay.draw(g2d);
        }
    }
}
