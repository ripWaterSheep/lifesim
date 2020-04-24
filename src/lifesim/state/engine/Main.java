package lifesim.state.engine;


import lifesim.state.Game;
import lifesim.state.GameState;
import lifesim.game.input.KeyInput;
import lifesim.state.menus.PauseMenu;
import lifesim.state.menus.TitleMenu;


public class Main {

    private static GamePanel gamePanel;

    private static final TitleMenu titleMenu = new TitleMenu();
    private static Game currentGame = new Game();


    public static GamePanel getPanel() {
        return gamePanel;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }


    public static void newGame() {
        currentGame = new Game();
        resumeGame();
    }


    public static void resumeGame() {
        gamePanel.setCurrentState(currentGame);
    }


    public static void goToTitle() {
        gamePanel.setCurrentState(titleMenu);
    }


    private static void toggleStateType(GameState gs1, GameState gs2) {
        if (gamePanel.getCurrentState().getClass().equals(gs1.getClass())) {
            gamePanel.setCurrentState(gs2);
        } else if (gamePanel.getCurrentState().getClass().equals(gs2.getClass())) {
            gamePanel.setCurrentState(gs1);
        }
    }


    public static void manageState() {
        if (KeyInput.k_esc.isClicked() && currentGame.canBePaused()) {
            toggleStateType(currentGame, new PauseMenu(currentGame));
        }
    }


    public static void main(String[] args) {
        gamePanel = new GamePanel(titleMenu);
        gamePanel.init();
    }

}
