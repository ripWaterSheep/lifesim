package lifesim.state.menus;

import lifesim.state.engine.GamePanel;
import lifesim.state.engine.GameWindow;
import lifesim.state.engine.StateManager;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonType;
import lifesim.util.sprites.ImageSprite;


public class TitleMenu extends Menu {

    public TitleMenu(GameWindow window, StateManager stateManager) {
        super(new ImageSprite("ui/title_menu"));
        buttons.add(new Button("Play Game", GamePanel.getCenterPos().translate(0, 30), ButtonType.WIDE, window) {
            @Override
            public void onClick() {
                stateManager.resumeGame();
            }
        });

        buttons.add(new Button("Settings", GamePanel.getCenterPos().translate(0, 60), ButtonType.MID, window) {
            @Override
            protected void onClick() {
                stateManager.goToSettings();
            }
        });

        buttons.add(new Button("Exit", GamePanel.getCenterPos().translate(0, 90), ButtonType.MID, window) {
            @Override
            protected void onClick() {
                stateManager.exit();
            }
        });
    }
}