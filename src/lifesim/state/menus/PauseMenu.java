package lifesim.state.menus;

import lifesim.engine.Main;
import lifesim.state.Game;
import lifesim.state.GameState;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.math.Vector2D;

import javax.swing.*;
import java.awt.*;


public class PauseMenu implements GameState {

    private final Game game;
    private final Font font = FontLoader.getMainFont(18);


    public PauseMenu(Game game) {
        this.game = game;
    }

    @Override
    public void update() {

    }


    @Override
    public void render(Graphics2D g2d) {
        game.render(g2d);
        GraphicsMethods.fillPanel(g2d, new Color(0, 0, 0, 75));
        GraphicsMethods.centeredString(g2d, "PAUSED", new Vector2D(0, 0), font, Color.WHITE);
    }
}
