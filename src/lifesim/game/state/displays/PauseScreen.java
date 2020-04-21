package lifesim.game.state.displays;

import lifesim.util.DrawMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.math.Vector2D;

import java.awt.*;


public class PauseScreen implements RenderableDisplay {

    private Font font = FontLoader.getMainFont(18);


    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2d) {
        DrawMethods.fillPanel(g2d, new Color(0, 0, 0, 100));
        DrawMethods.drawCenteredString(g2d, "PAUSED", new Vector2D(0, 0), font, Color.WHITE);
    }
}
