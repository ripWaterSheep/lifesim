package game.overlay;

import main.WindowSize;
import util.ColorMethods;
import util.DrawString;
import util.MyFonts;

import java.awt.*;

import static util.MyMath.*;



public class DeathScreen {

    private static boolean started = false;

    private static Color bgColor = new Color(215, 30, 40, 215);
    private static Color textColor = new Color(150, 20, 30, 255);

    private static Font font = new Font(MyFonts.getBloodFont(), Font.BOLD, 80);


    public static void show() {
        started = true;
    }


    public static void draw(Graphics g) {

        if(started) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(bgColor);

            Rectangle rect = WindowSize.getRect();
            g2d.fill(rect);
            g2d.setFont(font);
            DrawString.drawCenteredString(g2d, "OOF, YOU DIED!", rect, font, textColor);

        }

    }



}
