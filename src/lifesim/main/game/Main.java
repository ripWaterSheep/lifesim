package lifesim.main.game;


import lifesim.main.util.MiscUtil;

import javax.swing.*;


public class Main {

    private static GamePanel gamePanel;

    public static GamePanel getPanel() {
        return gamePanel;
    }

    public static Game getGame() {
        return gamePanel.getGame();
    }

    public static void start() {
        gamePanel = new GamePanel();
        MiscUtil.initFrame(new JFrame(""), gamePanel);
    }


    public static void restart() {
        gamePanel.startGame();
    }


    public static void main(String[] args) {
        start();
    }

}
