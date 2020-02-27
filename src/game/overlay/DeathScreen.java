package game.overlay;

import game.GameManager;
import game.controls.BetterKey;
import game.controls.BetterKeyboard;
import game.controls.BetterMouse;
import main.Main;
import main.MainPanel;
import util.drawing.DrawString;
import util.drawing.FontManager;

import java.awt.*;

public class DeathScreen implements DrawableOverlay {

    private static boolean showing = false;

    public static void show() {
        if (!BetterKeyboard.isAnyKeyClicked() && !showing) {
            Main.getPanel().getOverlayManager().overlays.add(new DeathScreen());
            showing = true;
        }
    }


    private static final Color bgColor = new Color(215, 26, 26, 200);
    private static final Color textColor = new Color(125, 20, 26);

    private static final Font font = FontManager.getBloodFont(90);


    @Override
    public void draw(Graphics g) {
        if (showing) {
            g.setColor(bgColor);
            Rectangle rect = main.Main.getPanel().getRect();

            ((Graphics2D) g).fill(rect);
            g.setFont(font);
            DrawString.drawCenteredString(g, "OOF, YOU DIED!", rect, font, textColor);

            if (BetterMouse.left.isClicked() || BetterKeyboard.isAnyKeyClicked()) {
                GameManager.startFromLastSave();
                showing = false;
            }
        }
    }


}
