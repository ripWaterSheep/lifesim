package lifesim.state.engine;


import lifesim.state.Game;
import lifesim.state.GameState;
import lifesim.game.input.KeyInput;
import lifesim.state.menus.Menu;
import lifesim.state.menus.PauseMenu;
import lifesim.state.menus.TitleMenu;
import lifesim.state.menus.settings.SettingsMenu;


public class Main {

    private static GamePanel gamePanel;

    private static Game currentGame = new Game();
    private static Menu titleMenu = new TitleMenu(currentGame);
    private static Menu settingsMenu = new SettingsMenu(currentGame);

    private static GameState lastState = titleMenu;


    public static GamePanel getPanel() {
        return gamePanel;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }


    public static void main(String[] args) {
        gamePanel = new GamePanel(titleMenu);
        gamePanel.init();
    }


    private static void setState(GameState gs) {
        lastState = gamePanel.getCurrentState();
        gamePanel.setCurrentState(gs);
    }

    private static void toggleStateType(GameState gs1, GameState gs2) {
        if (gamePanel.getCurrentState().getClass().equals(gs1.getClass())) {
            setState(gs2);
        } else if (gamePanel.getCurrentState().getClass().equals(gs2.getClass())) {
            setState(gs1);
        }
    }


    public static void newGame() {
        currentGame = new Game();
        titleMenu = new TitleMenu(currentGame);
        settingsMenu = new SettingsMenu(currentGame);
        resumeGame();
    }


    public static void resumeGame() {
        setState(currentGame);
    }

    public static void goToPrevious() {
        setState(lastState);
    }


    public static void goToTitle() {
        setState(titleMenu);
    }

    public static void goToSettings() {
        setState(settingsMenu);
    }

    public static void exit() {
        System.exit(0);
    }


    public static void manageState() {
        if (KeyInput.k_esc.isClicked() && currentGame.canBePaused()) {
            toggleStateType(currentGame, new PauseMenu(currentGame));
        }
    }


}
