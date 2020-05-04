package lifesim.state.menus;

import lifesim.state.engine.GameWindow;
import lifesim.state.engine.StateManager;
import lifesim.util.sprites.ImageSprite;
import lifesim.state.Game;
import lifesim.state.menus.Menu;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonType;
import lifesim.state.menus.ui.MultiStateButton;
import lifesim.util.geom.Vector2D;


public class SettingsMenu extends Menu {

    public SettingsMenu(Game game, GameWindow window, StateManager stateManager) {
        super(new ImageSprite("settings_test"));

        buttons.add(new Button("Exit", new Vector2D(200, -120), ButtonType.MID, window) {
            @Override
            protected void onClick() {
                stateManager.goToPrevious();
            }
        });
    }
}
