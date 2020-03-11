package lifesim.main.game;


import lifesim.main.game.setting.Layout;
import lifesim.main.util.MiscUtil;

import javax.swing.*;


public class Game {

    static GameSession gameSession;

    public static GameSession getSession() {
        return gameSession;
    }


    static GamePanel gamePanel;

    public static GamePanel getPanel() {
        return gamePanel;
    }


    public static void start() {
        gameSession = new GameSession(new Layout());
        gamePanel = new GamePanel(gameSession);

        MiscUtil.initFrame(new JFrame(""), gamePanel);
    }


    public static void restart() {
        gameSession = new GameSession(new Layout());
        gamePanel.gameSession = gameSession;
    }


    public static void main(String[] args) {
        start();
    }

}
