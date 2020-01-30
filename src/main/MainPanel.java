package main;

import game.activity.GameSession;
import game.activity.controls.ControlSetup;
import drawing.MyFont;

import javax.swing.*;
import java.awt.*;

import static main.WindowSize.defaultHeight;
import static main.WindowSize.defaultWidth;
import static util.TimeUtil.sleep;


public class MainPanel extends JPanel {

    GameSession gameSession = new GameSession();

    public MainPanel() {
        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);
        MyFont.initFonts();
        ControlSetup.initListeners(this);
        gameSession.init();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameSession.run(g);
        sleep(8);
        repaint();
    }


}
