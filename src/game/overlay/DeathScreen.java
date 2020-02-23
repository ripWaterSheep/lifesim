package game.overlay;

import game.GameManager;
import game.controls.BetterKeyboard;
import game.controls.BetterMouse;
import util.drawing.DrawString;
import util.drawing.FontManager;

import java.awt.*;

public class DeathScreen implements DrawableOverlay {

    private static final Color bgColor = new Color(215, 26, 26, 200);
    private static final Color textColor = new Color(125, 16, 25);

    private static final Font font = FontManager.getBloodFont(90);


    @Override
    public void draw(Graphics g) {

        g.setColor(bgColor);
        Rectangle rect = main.Main.getPanel().getRect();

        ((Graphics2D) g).fill(rect);

        g.setColor(bgColor);
        g.setFont(font);
        DrawString.drawCenteredString(g, "OOF, YOU DIED!", rect, font, textColor);

        if (BetterMouse.left.isClicked() || BetterKeyboard.isAnyKeyPressed()) {
            GameManager.startFromLastSave();
        }
    }


}
