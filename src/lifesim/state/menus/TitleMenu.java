package lifesim.state.menus;

import lifesim.state.engine.GameWindow;
import lifesim.state.engine.StateManager;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonType;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.geom.Vector2D;


public class TitleMenu extends Menu {

    public TitleMenu(GameWindow window, StateManager stateManager) {
        super(new ImageSprite("menu_test"));

        buttons.add(new Button("Play Game", new Vector2D(0, 30), ButtonType.WIDE, window) {
            @Override
            public void onClick() {
                stateManager.resumeGame();
            }
        });

        buttons.add(new Button("Settings",  new Vector2D(0, 60), ButtonType.MID, window) {
            @Override
            protected void onClick() {
                stateManager.goToSettings();
            }
        });

        buttons.add(new Button("Exit",  new Vector2D(0, 90), ButtonType.MID, window) {
            @Override
            protected void onClick() {
                stateManager.exit();
            }
        });
    }
}