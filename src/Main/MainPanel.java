package Main;

import Controls.KeyboardControls;
import GameComponents.Player;

import static Util.WindowSize.*;
import static Util.MiscUtil.*;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {

    private EventLoop eventLoop = new EventLoop();


    public MainPanel() {
        addKeyListener(KeyboardControls.keyAdapter);
        setFocusable(true);
        requestFocusInWindow();
        setSize(defaultWidth, defaultHeight);

        eventLoop.init(this);

    }


    @Override
    public void paintComponent(Graphics g) {
        setBackground(Player.getInstance().getWorld().getOuterColor());
        super.paintComponent(g);
        eventLoop.loop(g);
        sleep(10);
        repaint();
    }


}
