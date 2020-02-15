package main;

import game.Game;
import game.GraphicsManager;
import game.controls.ControlSetup;
import game.entities.Player;
import game.systems.RenderSystem;
import game.world.WorldRenderer;

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

        sleep(8);
        repaint();
    }


}
