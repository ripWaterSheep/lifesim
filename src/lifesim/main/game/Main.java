package lifesim.main.game;


import javax.swing.*;
import java.awt.*;


public class Main {

    private static GamePanel gamePanel;

    public static GamePanel getPanel() {
        return gamePanel;
    }

    public static Game getGame() {
        return gamePanel.getGame();
    }


    public static void main(String[] args) {
        gamePanel = new GamePanel();
        init(new JFrame(""), gamePanel);
    }

    private static void init(JFrame frame, GamePanel panel) {
        frame.setSize(panel.getSize());
        frame.setMinimumSize(new Dimension(1000, 800));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

}
