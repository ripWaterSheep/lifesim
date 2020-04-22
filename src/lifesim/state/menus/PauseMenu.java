package lifesim.state.menus;

import lifesim.state.GameState;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.math.Vector2D;

import java.awt.*;


public class PauseMenu implements GameState {

    private Font font = FontLoader.getMainFont(18);


    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2d) {
        GraphicsMethods.fillPanel(g2d, new Color(0, 0, 0, 75));
        GraphicsMethods.centeredString(g2d, "PAUSED", new Vector2D(0, 0), font, Color.WHITE);
    }
}
