package game.overlay;

import util.MyFonts;

import java.awt.*;

import static util.TimeUtil.getCurrentTime;


public class GameMessage {

    private static String currentMessage = "";

    public static void send(String message) {
        currentMessage = message;
        currentMessageStartTime = getCurrentTime();
    }


    public static void NeedMoneyMessage() {
        send("Ya need more $!!!");
    }


    private static final long MESSAGE_TIME = 1000;
    private static long currentMessageStartTime = 0;


    static void drawCurrentMessage(Graphics g) {

        if (currentMessage.length() > 0) {

            Graphics2D g2d = (Graphics2D) g.create();
            Font font = new Font(MyFonts.getMainFont(), Font.PLAIN, 35);

            //centerStringInRect(g2d, currentMessage, WindowSize.getRect(), font, color);
            g2d.setColor(Color.WHITE);
            g2d.setFont(font);
            g2d.drawString(currentMessage, 125, 75);
        }

        if (getCurrentTime()-currentMessageStartTime > MESSAGE_TIME) {
            currentMessage = "";
        }

    }


}
