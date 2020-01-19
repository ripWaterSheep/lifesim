package main;

import game.components.entities.player.Player;
import game.GameSession;
import game.activity.controls.ControlSetup;
import util.Drawing.MyFont;

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
        ControlSetup.initListeners(this);
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
