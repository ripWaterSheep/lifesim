package game.overlay;

import util.Drawing.MyFont;

import java.awt.*;

import static util.TimeUtil.getCurrentTime;


public class GameMessage {


    private static GameMessage currentMessage;
    private static long currentMessageStartTime = 0;

    private static GameMessage blankMessage = new GameMessage("", 1);

    public static void clear() {
        currentMessage = blankMessage;
    }


    public static void needMoneyMessage() {
        new GameMessage("Ya need more $!!!", 1);
    }


    static void drawCurrentMessage(Graphics g) {
        if (currentMessage != null) {
            currentMessage.draw(g);
            if (getCurrentTime() - currentMessageStartTime > currentMessage.messageTime) {
                currentMessage = blankMessage;
            }
        }

    }


    private final String message;
    private long messageTime = 1000;


    public GameMessage(String message) {
        currentMessage = this;
        this.message = message;
        currentMessageStartTime = getCurrentTime();
    }


    public GameMessage(String message, long messageTime) {
        this(message);
        this.messageTime = messageTime;
    }



    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        Font font = new Font(MyFont.getMainFont(), Font.PLAIN, 35);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(message, 125, 67);

    }




}
