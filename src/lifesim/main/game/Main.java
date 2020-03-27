package lifesim.main.game;


import lifesim.main.game.entities.components.stats.Alliance;

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
        initFrame(new JFrame(""), gamePanel);
        for (Alliance alliance1: Alliance.values()) {
            for (Alliance alliance2: Alliance.values()) {
                System.out.println(alliance1+"  "+alliance2+"  "+alliance1.canAttack(alliance2));
            }
        }

    }

    private static void initFrame(JFrame frame, GamePanel panel) {
        frame.setSize(panel.getSize());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
