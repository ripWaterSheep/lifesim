package main;

import game.Game;
import game.GraphicsManager;
import game.controls.ControlSetup;

import static main.WindowSize.*;
import static util.TimeUtil.*;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {

    Game currentGame;


    public MainPanel() {
        setPanel();
        currentGame = new Game();
        ControlSetup.initListeners(this);
    }


    private void setPanel() {
        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);
    }


    @Override
    public void paintComponent(Graphics g) {
        GraphicsManager.setGraphics(g);
        super.paintComponent(g);

        currentGame.run();

        sleep(9);
        repaint();
    }


}
