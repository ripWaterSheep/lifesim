package lifesim.state.menus;

import lifesim.state.engine.GameWindow;
import lifesim.state.engine.StateManager;
import lifesim.util.sprites.ImageSprite;
import lifesim.state.Game;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonType;
import lifesim.util.geom.Vector2D;

import static lifesim.state.engine.GamePanel.WIDTH;


public class SettingsMenu extends Menu {

    public SettingsMenu(Game game, GameWindow window, StateManager stateManager) {
        super(new ImageSprite("ui/settings"));

        buttons.add(new Button("Exit", new Vector2D(WIDTH - 50, 25), ButtonType.MID, window) {
            @Override
            protected void onClick() {
                stateManager.goToPrevious();
            }
        });
    }
}
