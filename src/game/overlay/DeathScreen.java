package game.overlay;

import java.awt.*;

public class DeathScreen {


    private static boolean started = false;
    private static int currentOpacity = 0;


    public static void show() {
        started = true;
    }


    public static void draw(Graphics g) {
        if(started) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setBackground(new Color(200, 15, 15, currentOpacity));

            currentOpacity += 1;
            currentOpacity = Math.max(currentOpacity, 255);

        }

    }



}
