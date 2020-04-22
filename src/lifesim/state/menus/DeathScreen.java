package lifesim.state.menus;

import lifesim.engine.Main;
import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.state.GameState;
import lifesim.util.math.Vector2D;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;

import java.awt.*;


public class DeathScreen implements GameState {

    private static final Color bgColor = new Color(215, 26, 26, 200);
    private static final Color textColor = new Color(125, 20, 26);

    private static final Font font = FontLoader.getMainFont(30);
    private static final Font subtitleFont = FontLoader.getMainFont(15);

    private static String deathReason = "dab";

    public static void setDeathReason(String reason) {
        deathReason = reason;
    }


    @Override
    public void update() {
        if (MouseInput.left.isClicked() || KeyInput.isAnyKeyClicked()) {
            Main.newGame();
        }
    }


    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(bgColor);
        GraphicsMethods.fillPanel(g2d, bgColor);

        GraphicsMethods.centeredString(g2d, "OOF, YOU DIED!", new Vector2D(0, 0), font, textColor);
        GraphicsMethods.centeredString(g2d, deathReason, new Vector2D(0, 25), subtitleFont, textColor);
    }


}
