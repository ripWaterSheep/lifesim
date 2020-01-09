package main;

import game.components.player.Player;
import game.GameSession;
import util.MyFont;

import static main.WindowSize.*;
import static util.TimeUtil.*;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {

    private GameSession gameSession = new GameSession();

    public MainPanel() {
        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);
        MyFont.initFonts();
        gameSession.init(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        setBackground(Player.getInstance().getWorld().getOuterColor());
        super.paintComponent(g);
        gameSession.loop(g);
        sleep(10);
        repaint();
    }


}
