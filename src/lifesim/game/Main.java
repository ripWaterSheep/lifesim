package lifesim.game;


import javafx.scene.layout.Pane;
import lifesim.game.state.Game;

import javax.swing.*;
import java.awt.*;


public class Main {

    private static Game game;
    private static GamePanel gamePanel;

    public static GamePanel getPanel() {
        return gamePanel;
    }

    public static Game getGame() {
        return game;
    }


    public static void newGame() {
        game = new Game();
        gamePanel.game = game;
    }


    public static void main(String[] args) {
        game = new Game();
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
