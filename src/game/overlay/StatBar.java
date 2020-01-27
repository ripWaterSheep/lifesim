package game.overlay;

import game.organization.components.entities.Player;
import main.WindowSize;
import util.ColorMethods;

import java.awt.*;
import java.util.ArrayList;

import static util.MyMath.betterRound;


public class StatBar {

    //TODO: Add icons (Once they are designed by Ken)

    static ArrayList<StatBar> shownStats = new ArrayList<>();


    private static final Font STAT_FONT = new Font("StayPuft", Font.PLAIN, 26);

    private static final int LEFT_PADDING = 3;
    private static final int BOTTOM_PADDING = 5;
    private static final int VERTICAL_SPACING = 36;

    private static final double DEFAULT_BAR_LENGTH_SCALE = 0.16;

    private static final int DEFAULT_OPACITY = betterRound(0.5*255);


    private String format(String label, Double data) {
        int roundedData = betterRound(data);
        return label + ": " + roundedData;
    }


    public static void retrieveValues() {
        Player player = Player.getInstance();
        shownStats.clear();

        new StatBar("X", player.getX());
        new StatBar("Y", player.getY());

        new StatBar("Health", player.getStats().getHealth(), 0, player.getStats().getStrengthDependentStatCap(), 1, Color.RED);
        new StatBar("Energy", player.getStats().getEnergy(), 0, player.getStats().getStrengthDependentStatCap(), 1, Color.ORANGE);
        new StatBar("Strength", player.getStats().getStrength(), 0, 10000, 0.1, Color.YELLOW);
        new StatBar("Cash", player.getStats().getMoney(), 0, 10000, 0.1, Color.GREEN);
        new StatBar("Intellect", player.getStats().getIntellect(), 0, 10000, 0.1, Color.BLUE);

    }


    public static void drawAll(Graphics g) {
        for (StatBar stat: shownStats) {
            stat.draw(g);
        }
    }


    private String key;
    private double value;

    private final boolean showBar;
    private Color barColor;

    private int barLength;
    private int statLength;



    public StatBar(String key, double value) {
        StatBar.shownStats.add(0, this);

        this.key = key;
        this.value = value;

        showBar = false;
    }



    public StatBar(String key, double value, double minVal, double maxVal, double barLengthScale, Color barColor) {
        StatBar.shownStats.add(0, this);

        this.key = key;
        this.value = value;

        this.barLength = betterRound(maxVal*DEFAULT_BAR_LENGTH_SCALE*barLengthScale);
        this.statLength = betterRound(barLength*(value-minVal)/(maxVal-minVal));
        this.barColor = ColorMethods.applyOpacity(barColor, DEFAULT_OPACITY);

        this.showBar = true;
    }



    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int textX = LEFT_PADDING+3;
        int textY = WindowSize.getHeight()-((shownStats.indexOf(this))* VERTICAL_SPACING) - (BOTTOM_PADDING+8);
        if (showBar) {
            int barX = LEFT_PADDING;
            int barY = betterRound(textY-29);

            // Draw bar showing data amount
            g2d.setColor(barColor);
            g2d.fillRect(barX, barY, Math.min(statLength, barLength), VERTICAL_SPACING);

            //Draw bar outline
            g2d.setColor(Color.BLACK);
            g2d.drawRect(barX, barY, barLength, VERTICAL_SPACING);
        }

        g2d.setColor(Color.WHITE);
        g2d.setFont(StatBar.STAT_FONT);
        g2d.drawString(format(key, value), textX, textY);
    }


}

