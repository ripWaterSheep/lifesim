package lifesim.game.overlay;

import lifesim.game.entities.Player;
import lifesim.game.entities.stats.PlayerStats;
import lifesim.game.entities.stats.StatsColors;
import lifesim.state.engine.GamePanel;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;
import lifesim.util.sprites.ImageSprite;
import lifesim.util.sprites.Sprite;

import java.awt.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static lifesim.util.MyMath.betterRound;


public class StatBar extends Overlay {

    private static final int LEFT_PADDING = 2;
    private static final int BOTTOM_PADDING = 2;
    private static final int BAR_WIDTH = 52;
    private static final int BAR_HEIGHT = 10;

    private static final Sprite STAT_OUTLINE = new ImageSprite("stat_outline");

    private static final Font STAT_FONT = FontLoader.getMainFont(6);
    private static final int TEXT_LEFT_PADDING = 2;

    private final Player player;


    private Graphics2D g2d;
    private int currentBarNum = 1;

    public StatBar(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics2D g2d) {
        this.g2d = g2d;
        PlayerStats stats = player.getStats();

        drawBar("Intellect", stats.getIntellect(), 1000, StatsColors.intellectColor);
        drawBar("Money", stats.getMoney(), 10000, StatsColors.moneyColor);
        drawBar("Strength", stats.getStrength(), 1000, StatsColors.strengthColor);
        drawBar("Energy", stats.getEnergy(), 1000, StatsColors.energyColor);
        drawBar("Health", stats.getHealth(), 1000, StatsColors.healthColor);

        writeValue("World", player.getWorld().name);
        writeRoundedVal("Y", player.getPos().y);
        writeRoundedVal("X", player.getPos().x);

        currentBarNum = 1;
    }


    private Vector2D getPos() {
        Vector2D pos = GamePanel.getPixelDims().scale(-0.5, 0.5);
        pos.translate(LEFT_PADDING, -(getCurrentBarNum() * BAR_HEIGHT) - BOTTOM_PADDING);
        return pos;
    }

    private int getCurrentBarNum() {
        return currentBarNum;
    }

    private String format(String label, String data) {
        return label + ": " + data;
    }

    private <T> void writeValue(String label, T data) {
        String formattedString = format(label, data + "");

        GraphicsMethods.verticallyCenteredString(g2d, formattedString, getPos().x + TEXT_LEFT_PADDING,
                getPos().y + BAR_HEIGHT/2.0 + 1, STAT_FONT, Color.WHITE);
        currentBarNum += 1;
    }

    private void writeRoundedVal(String label, double data) {
        writeValue(label, betterRound(data));
    }


    private void drawBar(String label, double data, double dataMax, Color color) {
        int dataWidth = betterRound((data / dataMax) * BAR_WIDTH);
        dataWidth = (int) min(dataWidth, dataMax * BAR_WIDTH);
        // Draw data display bar.
        g2d.setColor(color);
        g2d.fill(Rect.fromCorner(getPos().translate(1, 0), new Vector2D(dataWidth, BAR_HEIGHT)));
        // Draw outline for bar.
        STAT_OUTLINE.render(g2d, getPos().translate(STAT_OUTLINE.getSize().scale(0.5)), new Vector2D(0, 0));

        writeRoundedVal(label, data);
    }

}