package lifesim.game.overlay;

import lifesim.io.input.KeyInput;
import lifesim.io.input.MouseInput;
import lifesim.io.output.GamePanel;
import lifesim.state.StateManager;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;

import java.awt.*;


public class DeathScreen extends ToggleableOverlay {

    private static final Color bgColor = new Color(215, 26, 26);
    private static final Color textColor = new Color(125, 20, 26);

    private static final Font font = FontLoader.getMainFont(30);
    private static final Font subtitleFont = FontLoader.getMainFont(15);

    private static final long fadeDuration = 1500;

    private static final String deathReason = "";

    private double opacity = 0;
    private long fadeStartTime = 0;

    private final StateManager stateManager;

    public DeathScreen(StateManager stateManager) {
        this.stateManager = stateManager;
    }


    @Override
    public void start() {
        fadeStartTime = System.currentTimeMillis();
    }


    @Override
    public void update() {
        if ((MouseInput.left.isClicked() || KeyInput.isAnyKeyClicked()) && opacity >= 1) {
            stateManager.newGame();
            hide();
        }

        opacity = (double) (System.currentTimeMillis()-fadeStartTime) / fadeDuration;
    }


    @Override
    public void render(Graphics2D g2d) {
        GraphicsMethods.setOpacity(g2d, opacity);

        g2d.setColor(bgColor);
        GraphicsMethods.fillPanel(g2d, bgColor);

        GraphicsMethods.centeredString(g2d, "OOF, YOU DIED!", GamePanel.getCenterPos(), font, textColor);
        GraphicsMethods.centeredString(g2d, deathReason, GamePanel.getCenterPos().translate(0, 30), subtitleFont, textColor);
    }

}
