package lifesim.state.engine;

import lifesim.game.entities.Player;
import lifesim.input.KeyInput;
import lifesim.state.Game;
import lifesim.state.GameState;
import lifesim.state.menus.Menu;
import lifesim.state.menus.PauseMenu;
import lifesim.state.menus.TitleMenu;
import lifesim.state.menus.settings.SettingsMenu;

public class StateManager {

    private final GameWindow window;
    private final GamePanel panel;

    private Game currentGame;
    private Menu titleMenu;
    private Menu settingsMenu;
    private Menu pauseMenu;

    private GameState lastState;


    public StateManager(GameWindow window) {
        this.window = window;

        init();
        panel = new GamePanel(window, this, titleMenu);
    }

    Player getCurrentPlayer() {
        return currentGame.getPlayer();
    }


    public void init() {
        currentGame = new Game(window, this);
        titleMenu = new TitleMenu(window, this);
        pauseMenu = new PauseMenu(currentGame, window, this);
        settingsMenu = new SettingsMenu(currentGame, window, this);
    }


    public void newGame() {
        init();
        panel.setCurrentState(currentGame);
    }


    private void setState(GameState gs) {
        lastState = panel.getCurrentState();
        panel.setCurrentState(gs);
    }

    public void resumeGame() {
        setState(currentGame);
    }

    public void goToPrevious() {
        setState(lastState);
    }


    public void goToTitle() {
        setState(titleMenu);
    }

    public void goToSettings() {
        setState(settingsMenu);
    }

    public void exit() {
        System.exit(0);
    }


    public void manageState() {
        GameState state = panel.getCurrentState();

        if (KeyInput.k_esc.isClicked()) {
            if (state.equals(currentGame) && currentGame.canBePaused()) {
                setState(pauseMenu);
            } else {
                if (state.equals(pauseMenu)) {
                    setState(currentGame);
                }
            }
        }
    }

}
