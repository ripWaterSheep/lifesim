package main;

import gamesession.game.control.KeyboardControls;
import gamesession.game.control.MouseControls;
import gamesession.game.gamecomponents.Player;
import gamesession.GameSession;

import static util.WindowSize.*;
import static util.MiscUtil.*;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {

    private GameSession gameSession = new GameSession();


    public MainPanel() {
        addKeyListener(KeyboardControls.keyAdapter);
        addMouseListener(MouseControls.mouseAdapter);
        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);

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
