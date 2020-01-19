package game.overlay;

import game.components.entities.player.Player;
import util.ColorMethods;
import main.WindowSize;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static util.MyMath.betterRound;


public class Stat {

    //TODO: Add icons (Once they are designed by Ken)

    static ArrayList<Stat> shownStats = new ArrayList<>();


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

        Stat xStat = new Stat("X", player.getX());
        Stat yStat = new Stat("Y", player.getY());

        Stat healthStat = new Stat("Health", player.getHealth(), 0, player.getStrengthDependentStatCap(), 1, Color.RED);
        Stat energyStat = new Stat("Energy", player.getEnergy(), 0, player.getStrengthDependentStatCap(), 1, Color.ORANGE);
        Stat strengthStat = new Stat("Strength", player.getStrength(), 0, 10000, 0.1, Color.YELLOW);
        Stat moneyStat = new Stat("Cash", player.getMoney(), 0, 10000, 0.1, Color.GREEN);
        Stat intellectStat = new Stat("Intellect", player.getIntellect(), 0, 10000, 0.1, Color.BLUE);

        Collections.reverse(shownStats);
    }


    public static void drawAll(Graphics g) {
        for (Stat stat: shownStats) {
            stat.draw(g);
        }
    }


    private String key;
    private double value;

    private final boolean showBar;
    private Color barColor;

    private int barLength;
    private int statLength;




    public Stat(String key, double value) {
        Stat.shownStats.add(this);

        this.key = key;
        this.value = value;

        showBar = false;
    }



    public Stat(String key, double value, double minVal, double maxVal, double barLengthScale, Color barColor) {
        Stat.shownStats.add(this);

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
        g2d.setFont(Stat.STAT_FONT);
        g2d.drawString(format(key, value), textX, textY);
    }


}

