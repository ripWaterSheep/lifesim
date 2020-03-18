package lifesim.main.game.overlay;

import lifesim.main.game.Game;
import lifesim.main.game.GamePanel;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.controls.MouseInputManager;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.Fonts;

import java.awt.*;

public class DeathScreen extends Overlay {

    private static final Color bgColor = new Color(215, 26, 26, 200);
    private static final Color textColor = new Color(125, 20, 26);

    private static final Font font = Fonts.getBloodFont(95);
    private static final Font subtitleFont = Fonts.getBloodFont(45);

    private static String deathReason = "dab";

    public static void setDeathReason(String reason) {
        deathReason = reason;
    }


    public DeathScreen(GamePanel panel, Player player) {
        super(panel, player);
    }



    @Override
    public void render(Graphics2D g2d) {
        if (!((PlayerStats) player.stats).isAlive()) {
            g2d.setColor(bgColor);
            Rectangle rect = Game.getPanel().getVisibleRect();
            Rectangle subtitleRect = new Rectangle(0, 0, rect.width, rect.height*3/2);

            ((Graphics2D) g2d).fill(rect);

            g2d.setFont(font);
            DrawMethods.drawCenteredString(g2d, "OOF, YOU DIED!", rect, font, textColor);
            DrawMethods.drawCenteredString(g2d, deathReason, subtitleRect, subtitleFont, textColor);

            if (MouseInputManager.left.isClicked() || KeyInputManager.isAnyKeyClicked()) {
                Game.restart();
            }
        }
    }


}
