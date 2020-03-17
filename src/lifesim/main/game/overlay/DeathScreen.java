package lifesim.main.game.overlay;

import lifesim.main.game.Game;
import lifesim.main.game.GamePanel;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.util.fileIO.FontLoader;

import java.awt.*;

public class DeathScreen extends Overlay {

    private static boolean showing = false;
    private static String subtitle = "";

    public DeathScreen(GamePanel panel, Player player) {
        super(panel, player);
    }

    public static void show(String theReason) {
        if (!KeyInputManager.isAnyKeyClicked() && !showing) {
            //Game.getPanel().getOverlayManager().overlays.add(new DeathScreen());
            showing = true;
            subtitle = theReason;
        }
    }


    private static final Color bgColor = new Color(215, 26, 26, 200);
    private static final Color textColor = new Color(125, 20, 26);

    private static final Font font = FontLoader.getBloodFont(90);
    private static final Font subtitleFont = FontLoader.getBloodFont(45);


    @Override
    public void draw(Graphics g) {
        if (showing) {
            g.setColor(bgColor);
            Rectangle rect = Game.getPanel().getVisibleRect();
            Rectangle subtitleRect = new Rectangle(0, 0, rect.width, rect.height*3/2);

            ((Graphics2D) g).fill(rect);

            g.setFont(font);
            //DrawString.drawCenteredString(g, "OOF, YOU DIED!", rect, font, textColor);
            //DrawString.drawCenteredString(g, subtitle, subtitleRect, subtitleFont, textColor);

            if (MouseInputManager.left.isClicked() || KeyInputManager.isAnyKeyClicked()) {
                Game.restart();
                showing = false;
            }
        }
    }


}
