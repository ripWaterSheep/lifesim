package lifesim.state.menus;


import lifesim.state.engine.GameWindow;
import lifesim.state.engine.StateManager;
import lifesim.util.sprites.ImageSprite;
import lifesim.state.engine.Main;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonSize;
import lifesim.util.geom.Vector2D;

public class TitleMenu extends Menu {

    public TitleMenu(GameWindow window, StateManager stateManager) {
        super(new ImageSprite("menu_test"));

        buttons.add(new Button("Play", new Vector2D(0, 25), ButtonSize.WIDE, window) {
            @Override
            public void onClick() {
                stateManager.resumeGame();
            }
        });
        buttons.add(new Button("Settings",  new Vector2D(0, 50), ButtonSize.MID, window) {
            @Override
            protected void onClick() {
                stateManager.goToSettings();
            }
        });
        buttons.add(new Button("Exit",  new Vector2D(0, 75), ButtonSize.MID, window) {
            @Override
            protected void onClick() {
                stateManager.exit();
            }
        });
    }

}