package game.overlay;

import game.GameManager;
import game.controls.KeyInputManager;
import game.controls.MouseInputManager;
import main.Main;
import util.drawing.DrawString;
import util.drawing.FontManager;

import java.awt.*;

public class DeathScreen implements DrawableOverlay {

    private static boolean showing = false;
    private static String subtitle = "";

    public static void show(String theReason) {
        if (!KeyInputManager.isAnyKeyClicked() && !showing) {
            Main.getPanel().getOverlayManager().overlays.add(new DeathScreen());
            showing = true;
            subtitle = theReason;
        }
    }


    private static final Color bgColor = new Color(215, 26, 26, 200);
    private static final Color textColor = new Color(125, 20, 26);

    private static final Font font = FontManager.getBloodFont(90);
    private static final Font subtitleFont = FontManager.getBloodFont(45);


    @Override
    public void draw(Graphics g) {
        if (showing) {
            g.setColor(bgColor);
            Rectangle rect = main.Main.getPanel().getRect();
            Rectangle subtitleRect = new Rectangle(0, 0, rect.width, rect.height*3/2);

            ((Graphics2D) g).fill(rect);

            g.setFont(font);
            DrawString.drawCenteredString(g, "OOF, YOU DIED!", rect, font, textColor);
            DrawString.drawCenteredString(g, subtitle, subtitleRect, subtitleFont, textColor);

            if (MouseInputManager.left.isClicked() || KeyInputManager.isAnyKeyClicked()) {
                GameManager.startNew();
                showing = false;
            }
        }
    }


}
