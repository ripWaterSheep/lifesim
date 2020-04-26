package lifesim.game.display;

import lifesim.state.engine.Main;
import lifesim.game.input.KeyInput;
import lifesim.game.input.MouseInput;
import lifesim.util.geom.Vector2D;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;

import java.awt.*;


public class DeathScreen extends ToggleableDisplay {

    private static final Color bgColor = new Color(215, 26, 26);
    private static final Color textColor = new Color(125, 20, 26);

    private static final Font font = FontLoader.getMainFont(30);
    private static final Font subtitleFont = FontLoader.getMainFont(15);

    private static final long fadeDuration = 1500;

    private static String deathReason = "";


    private double opacity = 0;
    private long fadeStartTime = 0;


    @Override
    public void whenFirstShown() {
        fadeStartTime = System.currentTimeMillis();
    }


    @Override
    public void update() {
        if ((MouseInput.left.isClicked() || KeyInput.isAnyKeyClicked()) && opacity >= 1) {
            hide();
            Main.newGame();
        }

        opacity = (double) (System.currentTimeMillis()-fadeStartTime) / fadeDuration;
    }


    @Override
    public void render(Graphics2D g2d) {
        GraphicsMethods.setOpacity(g2d, opacity);

        g2d.setColor(bgColor);
        GraphicsMethods.fillPanel(g2d, bgColor);

        GraphicsMethods.centeredString(g2d, "OOF, YOU DIED!", new Vector2D(0, 0), font, textColor);
        GraphicsMethods.centeredString(g2d, deathReason, new Vector2D(0, 25), subtitleFont, textColor);
    }

}
