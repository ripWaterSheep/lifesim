package lifesim.main.util;

import lifesim.main.game.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MiscUtil {

    public static void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignored) {}
    }

    public static void initFrame(JFrame frame, GamePanel panel) {
        frame.setSize(panel.getSize());
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



}
