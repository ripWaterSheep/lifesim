package lifesim.game.display;

import lifesim.engine.Main;
import lifesim.game.entities.Player;
import lifesim.game.entities.components.stats.PlayerStats;
import lifesim.game.entities.components.stats.StatsColors;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.math.MyMath;

import java.awt.*;

import static java.lang.Math.min;


public class StatBar extends GameDisplay {

    private static final int LEFT_PADDING = 2;
    private static final int BOTTOM_PADDING = 2;
    private static final int BAR_HEIGHT = 8;

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

        drawBar("Intellect", stats.getIntellect(), 0.05, 1000, StatsColors.intellectColor);
        drawBar("Money", stats.getMoney(), 0.005, 10000, StatsColors.moneyColor);
        drawBar("Strength", stats.getStrength(), 0.05, 1000, StatsColors.strengthColor);
        drawBar("Energy", stats.getEnergy(), 0.05, 1000, StatsColors.energyColor);
        drawBar("Health", stats.getHealth(), 0.05, 1000, StatsColors.healthColor);

        writeValue("World", player.getWorld().name);
        writeRoundedVal("Y", player.getPos().y);
        writeRoundedVal("X", player.getPos().x);

        currentBarNum = 1;
    }


    private int getX() {
        return -Main.getPanel().getScaledWidth()/2 + LEFT_PADDING;
    }

    private int getY() {
        return Main.getPanel().getScaledHeight()/2 - ((getCurrentBarNum()) * BAR_HEIGHT) - BOTTOM_PADDING;
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

        GraphicsMethods.rectVerticallyCenteredString(g2d, formattedString, getX() + TEXT_LEFT_PADDING,
                new Rectangle(getX(), getY()+1, 0, BAR_HEIGHT), STAT_FONT, Color.WHITE);
        nextLine();
    }


    private void writeRoundedVal(String label, double data) {
        writeValue(label, MyMath.betterRound(data));
    }


    private void drawBar(String label, double data, double scale, double maxDataVal, Color color) {
        int dataWidth = MyMath.betterRound(min(data, maxDataVal) * scale);

        // Draw data display bar.
        g2d.setColor(color);
        g2d.fillRect(getX(), getY(), dataWidth, BAR_HEIGHT);

        // Draw outline for bar
        g2d.setColor(Color.BLACK);
        g2d.drawRect(getX(), getY(), MyMath.betterRound(maxDataVal * scale), BAR_HEIGHT);


        writeRoundedVal(label, data);
    }

}
