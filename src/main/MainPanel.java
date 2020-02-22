package main;

import game.activity.GameSession;
import game.activity.controls.ControlSetup;

import javax.swing.*;
import java.awt.*;

import static main.WindowSize.defaultHeight;
import static main.WindowSize.defaultWidth;
import static util.TimeUtil.sleep;


public class MainPanel extends JPanel {

    private static GameSession gameSession = new GameSession();

    public static GameSession getGameSession() {
        return gameSession;
    }


    public static void restartGame() {
       gameSession = new GameSession();
       gameSession.init();
    }


    public MainPanel() {
        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);
        ControlSetup.initListeners(this);
        gameSession.init();

    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameSession.run(g);
        sleep(7);
        repaint();
    }


}
