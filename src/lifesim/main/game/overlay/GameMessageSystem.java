package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.Vector2D;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;

import java.awt.*;
import java.util.ArrayList;

public class GameMessageSystem extends Overlay {

    private static final GameMessage EMPTY_MESSAGE = new GameMessage("", 0);


    private ArrayList<GameMessage> queuedMessages = new ArrayList<>();
    private GameMessage currentMessage = EMPTY_MESSAGE;
    private long currentMessageStartTime = System.currentTimeMillis();

    public GameMessageSystem(GamePanel panel, Player player) {
        super(panel, player);
    }


    public void sendMessage(GameMessage message) {
        queuedMessages.add(message);
    }


    @Override
    public void update() {
        if (System.currentTimeMillis() - currentMessageStartTime > currentMessage.duration) {
            if (queuedMessages.isEmpty()) currentMessage = EMPTY_MESSAGE;
            else {
                currentMessage = queuedMessages.get(0);
                currentMessageStartTime = System.currentTimeMillis();
            }
            queuedMessages.remove(currentMessage);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        DrawMethods.drawCenteredString(g2d, currentMessage.message, new Vector2D(0, -100), FontLoader.getMainFont(10), Color.WHITE);
    }
}
