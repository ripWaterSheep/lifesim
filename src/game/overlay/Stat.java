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

    private static final double BAR_LENGTH_SCALE = 0.16;
    private static final double MAX_BAR_LENGTH = 400;

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
        Stat strengthStat = new Stat("Strength", player.getStrength(), 0, Math.max(1000, player.getStrength()), 1, Color.YELLOW);
        Stat moneyStat = new Stat("Cash", player.getMoney(), 0, Math.max(player.getMoney(), 5000), 0.2, Color.GREEN);
        Stat intellectStat = new Stat("Intellect", player.getIntellect(), 0, Math.max(5000, player.getIntellect()), 0.2, Color.BLUE);

        Collections.reverse(shownStats);
    }


    public static void drawAll(Graphics g) {
        for (Stat stat: shownStats) {
            stat.draw(g);
        }
    }


    private String key;

    private double value;

    private double minVal;
    private double maxVal;

    private final boolean showStatusBar;
    private double scale;

    private Color barColor;


    public Stat(String key, double value) {
        Stat.shownStats.add(this);

        this.key = key;
        this.value = value;

        showStatusBar = false;
    }




    public Stat(String key, double value, double minVal, double maxVal, double scale, Color barColor) {
        Stat.shownStats.add(this);

        this.key = key;
        this.value = value;
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.scale = scale;

        this.barColor = ColorMethods.applyOpacity(barColor, DEFAULT_OPACITY);
        this.showStatusBar = true;
    }



    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int x = LEFT_PADDING+3;
        int y = WindowSize.getHeight()-((shownStats.indexOf(this))* VERTICAL_SPACING) - (BOTTOM_PADDING+8);
        if (showStatusBar) {
            int barX = LEFT_PADDING;
            int barY = betterRound(y-29);
            int barWidth = betterRound((value-minVal) * BAR_LENGTH_SCALE*scale);

            // Draw bar showing data amount
            g2d.setColor(barColor);
            g2d.fillRect(barX, barY, betterRound(Math.min(betterRound(barWidth), MAX_BAR_LENGTH)), VERTICAL_SPACING);

            //Draw bar outline
            g2d.setColor(Color.BLACK);
            g2d.drawRect(barX, barY, betterRound(Math.min((maxVal-minVal)*BAR_LENGTH_SCALE*scale, MAX_BAR_LENGTH)), VERTICAL_SPACING);
        }

        g2d.setColor(Color.WHITE);
        g2d.setFont(Stat.STAT_FONT);
        g2d.drawString(format(key, value), x, y);
    }


}

