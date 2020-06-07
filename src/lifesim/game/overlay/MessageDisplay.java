package lifesim.game.overlay;

import lifesim.util.geom.Vector2D;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;

import java.awt.*;

import static java.lang.Math.max;


public class MessageDisplay extends Overlay {

    private final Font font;
    protected final Vector2D pos;

    private String currentMessage = "";

    private final long fadeTimeout; // How long it takes for the message to fade away once displayed.
    private long lastUpdateTime = System.currentTimeMillis();
    protected double currentOpacity = 0;


    public MessageDisplay(int textSize, int fadeTimeout, Vector2D pos) {
        font = FontLoader.getMainFont(textSize);
        this.fadeTimeout = fadeTimeout;
        this.pos = pos;
    }


    public void displayMessage(String message) {
        currentMessage = message;
        currentOpacity = 1;
        lastUpdateTime = System.currentTimeMillis();
    }


    public void update() {
        if (System.currentTimeMillis() - lastUpdateTime > fadeTimeout) {
            currentOpacity -= 0.05;
            currentOpacity = max(currentOpacity, 0);
        }
    }

    public void render(Graphics2D g2d) {
        GraphicsMethods.setOpacity(g2d, currentOpacity);
        GraphicsMethods.centeredString(g2d, currentMessage, pos, font, Color.WHITE);
    }
}
