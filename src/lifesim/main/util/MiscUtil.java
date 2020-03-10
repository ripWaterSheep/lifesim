package lifesim.main.util;

import lifesim.main.GamePanel;

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }



}
