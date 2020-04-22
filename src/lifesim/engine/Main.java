package lifesim.engine;


import lifesim.game.input.MouseInput;
import lifesim.state.GameState;
import lifesim.state.menus.DeathScreen;
import lifesim.game.input.KeyInput;
import lifesim.state.Game;
import lifesim.state.menus.PauseMenu;

import javax.swing.*;
import java.awt.*;


public class Main {

    private static GamePanel gamePanel;


    private static Game game = new Game();
    private static final PauseMenu pauseMenu = new PauseMenu();
    private static final DeathScreen deathScreen = new DeathScreen();


    public static GamePanel getPanel() {
        return gamePanel;
    }

    public static Game getGame() {
        return game;
    }


    public static void newGame() {
        game = new Game();
        gamePanel.setGameState(game);
    }


    private static void toggleState(GameState gs1, GameState gs2) {
        if (gamePanel.getGameState().getClass().equals(gs1.getClass())) {
            gamePanel.setGameState(gs2);
        } else if (gamePanel.getGameState().getClass().equals(gs2.getClass())) {
            gamePanel.setGameState(gs1);
        }
    }


    public static void manageState() {
        if (gamePanel.getGameState().equals(game)) {

            if (KeyInput.k_esc.isClicked()) {
                toggleState(game, pauseMenu);
            }
            if (!game.getPlayer().getStats().isAlive()) {
                gamePanel.setGameState(deathScreen);
                if (KeyInput.isAnyKeyClicked() || MouseInput.left.isClicked()) {
                    newGame();
                }
            }
        }
    }


    public static void main(String[] args) {
        gamePanel = new GamePanel(game);
        init(new JFrame(), gamePanel);
    }

    private static void init(JFrame frame, JPanel panel) {
        frame.setSize(panel.getSize());
        frame.setMinimumSize(new Dimension(1000, 800));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

}
