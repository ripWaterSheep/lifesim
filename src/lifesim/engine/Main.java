package lifesim.engine;


import lifesim.state.GameState;
import lifesim.game.input.KeyInput;
import lifesim.state.Game;
import lifesim.state.menus.PauseMenu;

import javax.swing.*;


public class Main {

    private static GamePanel gamePanel;


    private static Game currentGame = new Game();

    public static GamePanel getPanel() {
        return gamePanel;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }


    public static void newGame() {
        currentGame = new Game();
        gamePanel.setGameState(currentGame);
    }


    private static void toggleState(GameState gs1, GameState gs2) {
        if (gamePanel.getGameState().getClass().equals(gs1.getClass())) {
            gamePanel.setGameState(gs2);
        } else if (gamePanel.getGameState().getClass().equals(gs2.getClass())) {
            gamePanel.setGameState(gs1);
        }
    }


    public static void manageState() {
        if (KeyInput.k_esc.isClicked() && currentGame.isPausable()) {
            toggleState(currentGame, new PauseMenu(currentGame));
        }
    }


    public static void main(String[] args) {
        gamePanel = new GamePanel(currentGame);
        gamePanel.initFrame(new JFrame(""));
    }

}
