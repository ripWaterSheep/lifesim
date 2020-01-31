package game.overlay;

import game.activity.controls.MouseControls;
import main.MainPanel;
import main.WindowSize;
import drawing.DrawString;
import drawing.MyFont;

import java.awt.*;


public class DeathScreen {

    private static boolean started = false;

    public static boolean iStarted() {
        return started;
    }

    private static Color bgColor = new Color(215, 26, 26, 200);
    private static Color textColor = new Color(125, 16, 25);

    private static Font font = new Font(MyFont.getBloodFont(), Font.BOLD, 90);


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

            if (MouseControls.getLeftClicked()) {
                MainPanel.restartGame();
            }
        }

    }



}
