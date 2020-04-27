package lifesim.state.menus;

import lifesim.util.sprites.ShapeSprite;
import lifesim.state.Game;
import lifesim.state.engine.Main;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonSize;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public class PauseMenu extends Menu {

    private final Game game;
    private final Font font = FontLoader.getMainFont(18);


    public PauseMenu(Game game) {
        super(new ShapeSprite(100, 100, new Color(0, 0, 0, 0)), game);
        this.game = game;

        buttons.add(new Button("Resume", new Vector2D(0, 25), ButtonSize.WIDE) {
            @Override
            public void onClick() {
                Main.resumeGame();
            }
        });
        buttons.add(new Button("Settings",  new Vector2D(0, 50), ButtonSize.MID) {
            @Override
            protected void onClick() {
                Main.goToSettings();
            }
        });
        buttons.add(new Button("Exit", new Vector2D(0, 75), ButtonSize.MID) {
            @Override
            public void onClick() {
                Main.goToTitle();
            }
        });
    }

    @Override
    public void render(Graphics2D g2d) {
        game.render(g2d);
        super.render(g2d);
        GraphicsMethods.fillPanel(g2d, new Color(0, 0, 0, 75));
        GraphicsMethods.centeredString(g2d, "PAUSED", new Vector2D(0, 0), font, Color.WHITE);
    }
}
