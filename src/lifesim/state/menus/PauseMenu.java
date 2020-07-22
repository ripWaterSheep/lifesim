package lifesim.state.menus;

import lifesim.io.output.GamePanel;
import lifesim.io.output.GameWindow;
import lifesim.state.StateManager;
import lifesim.util.sprites.ShapeSprite;
import lifesim.state.Game;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonType;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;

import java.awt.*;


public class PauseMenu extends Menu {

    private final Game game;
    private final Font font = FontLoader.getMainFont(36);


    public PauseMenu(Game game, GameWindow window, StateManager stateManager) {
        super(new ShapeSprite(100, 100, new Color(0, 0, 0, 0)));
        this.game = game;

        buttons.add(new Button("Resume", GamePanel.getCenterPos().translate(0, 24), ButtonType.MID, window) {
            @Override
            public void onClick() {
                stateManager.resumeGame();
            }
        });
        /*buttons.add(new Button("Settings", GamePanel.getCenterPos().translate(0, 60), ButtonType.MID, window) {
            @Override
            protected void onClick() {
                stateManager.goToSettings();
            }
        });*/
        buttons.add(new Button("Exit", GamePanel.getCenterPos().translate(0, 60), ButtonType.SMALL, window) {
            @Override
            public void onClick() {
                stateManager.goToTitle();
            }
        });
    }

    @Override
    public void render(Graphics2D g2d) {
        game.render(g2d);
        super.render(g2d);
        GraphicsMethods.fillPanel(g2d, new Color(0, 0, 0, 75));
        GraphicsMethods.centeredString(g2d, "PAUSED", GamePanel.getCenterPos().translate(0, -30), font, Color.WHITE);
    }
}
