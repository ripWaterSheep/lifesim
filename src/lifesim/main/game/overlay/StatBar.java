package lifesim.main.game.overlay;

import lifesim.main.game.Game;
import lifesim.main.game.GamePanel;
import lifesim.main.game.entities.Player;
import lifesim.main.game.entities.components.stats.HealthStats;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.util.ColorMethods;

import java.awt.*;

import static java.lang.Math.min;
import static lifesim.main.util.math.MyMath.betterRound;


public class StatBar extends Overlay {

    private static final int LEFT_PADDING = 4;
    private static final int BOTTOM_PADDING = 4;

    private static final int BAR_HEIGHT = 36;

    private static final int BAR_OPACITY = 125;

    private static final int TEXT_SIZE = 28;

    private Graphics g;
    private int currentBarNum = 1;


    public StatBar(GamePanel panel, Player player) {
        super(panel, player);
    }

    @Override
    public void draw(Graphics g) {
        this.g = g;
        Player player = Game.getSession().getPlayer();
        PlayerStats stats = (PlayerStats) player.stats;

        drawBar("Intellect", stats.getIntellect(), 0.2, 1000, PlayerStats.Colors.intellectColor);
        drawBar("Money", stats.getMoney(), 0.02, 10000, PlayerStats.Colors.moneyColor);
        drawBar("Strength", stats.getStrength(), 0.2, 1000, PlayerStats.Colors.strengthColor);
        drawBar("Energy", stats.getEnergy(), 0.2, 1000, PlayerStats.Colors.energyColor);
        drawBar("Health", stats.getHealth(), 0.2, 1000, HealthStats.Colors.bloodColor);

        writeValue("World", player.getWorld().name);
        writeRoundedVal("Y", player.pos.x);
        writeRoundedVal("X", player.pos.y);

        currentBarNum = 1;
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

        int textY = panel.getHeight() - (getCurrentBarNum()*BAR_HEIGHT) - BOTTOM_PADDING;
        /*DrawString.drawVerticallyCenteredString(g, formattedString,
                new Vector2D(LEFT_PADDING, textY, 0, BAR_HEIGHT), FontLoader.getMainFont(TEXT_SIZE), Color.WHITE);*/
        nextLine();
    }


    private <T> void writeRoundedVal(String label, double data) {
        writeValue(label, betterRound(data));
    }


    private <T> void drawBar(String label, double data, double scale, double maxDataVal, Color color) {
        int y = Game.getPanel().getHeight() - ((getCurrentBarNum()) * BAR_HEIGHT) - BOTTOM_PADDING;
        int dataWidth = betterRound(min(data, maxDataVal) * scale);

        g.setColor(Color.BLACK);
        g.drawRect(LEFT_PADDING, y, betterRound(maxDataVal * scale), BAR_HEIGHT);

        g.setColor(ColorMethods.applyOpacity(color, BAR_OPACITY));
        g.fillRect(LEFT_PADDING, y, dataWidth, BAR_HEIGHT);

        writeRoundedVal(label, data);
    }


}
