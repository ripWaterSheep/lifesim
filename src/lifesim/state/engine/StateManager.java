package lifesim.state.engine;

import lifesim.game.entities.Player;
import lifesim.game.input.KeyInput;
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

    private GameState lastState;


    public StateManager(GameWindow window) {
        this.window = window;

        currentGame = new Game(window, this);
        titleMenu = new TitleMenu(window, this);
        settingsMenu = new SettingsMenu(currentGame, window, this);

        this.panel = new GamePanel(window, this, titleMenu);
    }

    public void newGame() {
        currentGame = new Game(window, this);
        titleMenu = new TitleMenu(window, this);
        settingsMenu = new SettingsMenu(currentGame, window, this);
        panel.setCurrentState(currentGame);
    }


    private void setState(GameState gs) {
        lastState = panel.getCurrentState();
        panel.setCurrentState(gs);
    }


    private void toggleStateType(GameState gs1, GameState gs2) {
        if (panel.getCurrentState().getClass().equals(gs1.getClass())) {
            setState(gs2);
        } else if (panel.getCurrentState().getClass().equals(gs2.getClass())) {
            setState(gs1);
        }
    }


    Player getCurrentPlayer() {
        return currentGame.getPlayer();
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
        if (KeyInput.k_esc.isClicked() && currentGame.canBePaused()) {
            toggleStateType(currentGame, new PauseMenu(currentGame, window, this));
        }
    }

}
