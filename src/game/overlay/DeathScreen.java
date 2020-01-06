package game.overlay;

import main.WindowSize;
import util.DrawString;
import util.MyFonts;

import java.awt.*;

public class DeathScreen {


    private static boolean started = false;
    private static int currentOpacity = 255;

    private static Color color = new Color(200, 15, 15, currentOpacity);

    // TODO: use like totally cool font with like blood dripping down
    private static Font font = new Font(MyFonts.getMainFont(), Font.BOLD, 75);


    public static void show() {
        started = true;
    }


    public static void draw(Graphics g) {

        if(started) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(color);

            //currentOpacity += 1;
            //currentOpacity = Math.max(currentOpacity, 255);

            Rectangle rect = WindowSize.getRect();
            g2d.fill(rect);
            g2d.setFont(font);
            DrawString.drawCenteredString(g2d, "OOF, YOU DIED!", rect, font, Color.WHITE);

        }

    }



}
