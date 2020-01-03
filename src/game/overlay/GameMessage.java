package game.overlay;

import java.awt.*;

import static util.TimeUtil.getCurrentTime;


public class GameMessage {

    private static String currentMessage = "";

    public static void setMessage (String message) {
        currentMessage = message;
        currentMessageStartTime = getCurrentTime();
    }


    private static final long MESSAGE_TIME = 3000;
    private static long currentMessageStartTime = 0;


    static void drawLatest(Graphics g) {

        if (currentMessage.length() > 0) {

            Graphics2D g2d = (Graphics2D) g.create();
            Font font = new Font("Comic Sans MS", Font.PLAIN, 50);

            //centerStringInRect(g2d, currentMessage, WindowSize.getRect(), font, color);
            g2d.setFont(font);
            g2d.drawString(currentMessage, 125, 75);
        }
        System.out.println(getCurrentTime()-currentMessageStartTime);

        if (getCurrentTime()-currentMessageStartTime > MESSAGE_TIME) {
            currentMessage = "";
        }

    }


}
