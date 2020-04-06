package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;

import java.awt.*;


public class MessageSystem extends Overlay {

    private String currentMessage = "";
    private double currentOpacity = 0;

    public MessageSystem(GamePanel panel, Player player) {
        super(panel, player);
    }

    public void sendMessage(String message) {
        currentMessage = message;
        currentOpacity = 4;
    }


    @Override
    public void update() {
        currentOpacity -= 0.05;
    }

    @Override
    public void render(Graphics2D g2d) {
        DrawMethods.setOpacity(g2d, currentOpacity);
        DrawMethods.drawCenteredString(g2d, currentMessage, new Vector2D(0, -100), FontLoader.getMainFont(10), Color.WHITE);
    }

}
