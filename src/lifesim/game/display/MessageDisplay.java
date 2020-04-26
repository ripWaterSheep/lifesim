package lifesim.game.display;

import lifesim.util.geom.Vector2D;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;

import java.awt.*;


public class MessageDisplay extends GameDisplay {

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
        GraphicsMethods.setOpacity(g2d, currentOpacity);
        GraphicsMethods.centeredString(g2d, currentMessage, pos, font, textColor);
    }
}
