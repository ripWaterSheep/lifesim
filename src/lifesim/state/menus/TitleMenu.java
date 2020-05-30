package lifesim.state.menus;

import lifesim.engine.output.GamePanel;
import lifesim.engine.output.GameWindow;
import lifesim.state.StateManager;
import lifesim.state.menus.ui.Button;
import lifesim.state.menus.ui.ButtonType;
import lifesim.util.sprites.ImageSprite;


public class TitleMenu extends Menu {

    public TitleMenu(GameWindow window, StateManager stateManager) {
        super(new ImageSprite("ui/title_menu"));

        buttons.add(new Button("New Game", GamePanel.getCenterPos().translate(0, 24), ButtonType.MID, window) {
            @Override
            protected void onClick() {
                stateManager.newGame();
            }
        });

        buttons.add(new Button("Continue Game", GamePanel.getCenterPos().translate(0, 60), ButtonType.WIDE, window) {
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

        buttons.add(new Button("Exit", GamePanel.getCenterPos().translate(0, 96), ButtonType.SMALL, window) {
            @Override
            protected void onClick() {
                stateManager.exit();
            }
        });
    }
}