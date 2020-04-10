package lifesim.main.game.display.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.controls.KeyInput;
import lifesim.main.game.controls.MouseInput;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;
import lifesim.main.util.math.Geometry;

import java.awt.*;


public class DeathScreen extends Overlay {

    private static final Color bgColor = new Color(215, 26, 26, 200);
    private static final Color textColor = new Color(125, 20, 26);

    private static final Font font = FontLoader.getMainFont(30);
    private static final Font subtitleFont = FontLoader.getMainFont(15);

    private static String deathReason = "dab";

    public static void setDeathReason(String reason) {
        deathReason = reason;
    }


    private boolean showing = false;



    public DeathScreen(GamePanel panel, Player player) {
        super(panel, player);
    }

    @Override
    public void update() {
        if (!player.getStats().isAlive()) {
            showing = true;
            if (MouseInput.left.isClicked() || KeyInput.isAnyKeyClicked()) {
                panel.newGame();
                showing = false;
            }
        }
    }


    @Override
    public void render(Graphics2D g2d) {
        if (showing) {
            g2d.setColor(bgColor);
            Rectangle rect = Geometry.getCenteredRect(new Vector2D(0, 0), panel.getScaledSize());
            g2d.fill(rect);

            DrawMethods.drawCenteredString(g2d, "OOF, YOU DIED!", new Vector2D(0, 0), font, textColor);
            DrawMethods.drawCenteredString(g2d, deathReason, new Vector2D(0, 25), subtitleFont, textColor);

        }
    }


}
