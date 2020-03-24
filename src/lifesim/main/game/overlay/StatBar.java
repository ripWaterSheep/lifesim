package lifesim.main.game.overlay;

import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.stats.HealthStats;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.util.ColorMethods;
import lifesim.main.util.DrawMethods;
import lifesim.main.util.fileIO.FontLoader;

import java.awt.*;

import static java.lang.Math.min;
import static lifesim.main.util.math.MyMath.betterRound;


public class StatBar extends Overlay {

    private static final int LEFT_PADDING = 2;
    private static final int BOTTOM_PADDING = 2;
    private static final int BAR_HEIGHT = 8;

    private static final int BAR_OPACITY = 145;

    private static final Font STAT_FONT = FontLoader.getMainFont(6);
    private static final int TEX_LEFT_PADDING = 2;


    private Graphics2D g2d;
    private int currentBarNum = 1;


    public StatBar(GamePanel panel, Player player) {
        super(panel, player);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g2d) {
        this.g2d = g2d;
        PlayerStats stats = (PlayerStats) player.stats;

        drawBar("Intellect", stats.getIntellect(), 0.05, 1000, PlayerStats.Colors.intellectColor);
        drawBar("Money", stats.getMoney(), 0.005, 10000, PlayerStats.Colors.moneyColor);
        drawBar("Strength", stats.getStrength(), 0.05, 1000, PlayerStats.Colors.strengthColor);
        drawBar("Energy", stats.getEnergy(), 0.05, 1000, PlayerStats.Colors.energyColor);
        drawBar("Health", stats.getHealth(), 0.05, 1000, HealthStats.Colors.bloodColor);

        writeValue("World", player.getWorld().name);
        writeRoundedVal("Y", player.pos.y);
        writeRoundedVal("X", player.pos.x);

        currentBarNum = 1;
    }


    private int getX() {
        return -panel.getScaledWidth()/2 + LEFT_PADDING;
    }

    private int getY() {
        return panel.getScaledHeight()/2 - ((getCurrentBarNum()) * BAR_HEIGHT) - BOTTOM_PADDING;
    }



    private void nextLine() {
        currentBarNum += 1;
    }

    private int getCurrentBarNum() {
        return currentBarNum;
    }


    private String format(String label, String data) {
        return label + ": " + data;
    }


    private <T> void writeValue(String label, T data) {
        String formattedString = format(label, data+"");

        DrawMethods.drawVerticallyCenteredString(g2d, formattedString, getX() + TEX_LEFT_PADDING,
                new Rectangle(getX(), getY()+1, 0, BAR_HEIGHT), STAT_FONT, Color.WHITE);
        nextLine();
    }


    private void writeRoundedVal(String label, double data) {
        writeValue(label, betterRound(data));
    }


    private void drawBar(String label, double data, double scale, double maxDataVal, Color color) {
        int dataWidth = betterRound(min(data, maxDataVal) * scale);

        // Draw data display bar.
        g2d.setColor(ColorMethods.applyOpacity(color, BAR_OPACITY));
        g2d.fillRect(getX(), getY(), dataWidth, BAR_HEIGHT);

        // Draw outline for bar
        g2d.setColor(Color.BLACK);
        g2d.drawRect(getX(), getY(), betterRound(maxDataVal * scale), BAR_HEIGHT);


        writeRoundedVal(label, data);
    }


}
