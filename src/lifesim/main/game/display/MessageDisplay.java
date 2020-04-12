package lifesim.main.game.display;

import lifesim.main.util.math.Vector2D;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;

import java.awt.*;


public class MessageDisplay implements RenderableDisplay  {

    private final Font font;
    private final Color textColor;
    protected final Vector2D pos;

    private String currentMessage = "";
    protected double currentOpacity = 0;


    public MessageDisplay(int textSize, Color textColor, Vector2D pos) {
        font = FontLoader.getMainFont(textSize);
        this.textColor = textColor;
        this.pos = pos;
    }


    public void displayMessage(String message) {
        currentMessage = message;
        currentOpacity = 3;
    }

    public void update() {
        currentOpacity -= 0.1;
    }

    public void render(Graphics2D g2d) {
        DrawMethods.setOpacity(g2d, currentOpacity);
        DrawMethods.drawCenteredString(g2d, currentMessage, pos, font, textColor);
    }
}
