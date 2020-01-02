package game.overlay.overlays;

import game.components.player.Player;
import util.ColorMethods;
import util.WindowSize;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static util.MyMath.betterRound;


public class Stat {

    static ArrayList<Stat> instances = new ArrayList<>();

    public static ArrayList<Stat> getInstances() { return instances; }


    private static final Font STAT_FONT = new Font("TimesRoman", Font.PLAIN, 20);

    private static final int LEFT_PADDING = 3;
    private static final int BOTTOM_PADDING = 5;
    private static final int VERTICAL_SPACING = 26;

    private static final double BAR_LENGTH_SCALE = 0.125;

    private static final int DEFAULT_OPACITY = betterRound(0.5*255);


    private String format(String label, Double data) {
        int roundedData = betterRound(data);
        return label + ": " + roundedData;
    }


    public static void retrieveValues() {
        Player player = Player.getInstance();
        instances.clear();

        Stat xStat = new Stat("X", player.getX());
        Stat yStat = new Stat("Y", player.getY());

        Stat healthStat = new Stat("Health", player.getHealth(), 0, player.getStrengthDependentStatCap(), Color.RED);
        //Stat
        Stat energyStat = new Stat("Energy", player.getEnergy(), 0, player.getStrengthDependentStatCap(), Color.ORANGE);
        Stat strengthStat = new Stat("Strength", player.getStrength(), 0, Math.max(1000, player.getStrength()), Color.YELLOW);
        Stat moneyStat = new Stat("Cash", player.getMoney(), 0, Math.max(player.getMoney(), 1000), Color.GREEN);
        Stat intellectStat = new Stat("Intellect", player.getIntellect(), 0, Math.max(1000, player.getIntellect()), Color.BLUE);

        Collections.reverse(instances);

    }


    public static void drawAll(Graphics g) {
        for (Stat stat: instances) {
            stat.draw(g);
        }
    }


    private String label;

    private double value;

    private double minVal;
    private double maxVal;

    private final boolean showStatusBar;

    private Color barColor;


    public Stat(String label, double value) {
        Stat.instances.add(this);

        this.label = label;
        this.value = value;

        showStatusBar = false;
    }


    public Stat(String label, double value, double minVal, double maxVal, Color barColor) {
        Stat.instances.add(this);

        this.label = label;
        this.value = value;
        this.minVal = minVal;
        this.maxVal = maxVal;

        this.barColor = ColorMethods.applyTransparency(barColor, DEFAULT_OPACITY);
        this.showStatusBar = true;
    }



    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int x = LEFT_PADDING+3;
        int y = WindowSize.getHeight()-((instances.indexOf(this))* VERTICAL_SPACING) - (BOTTOM_PADDING+44);

        if (showStatusBar) {
            int barX = LEFT_PADDING;
            int barY = betterRound(y + (0.5 * VERTICAL_SPACING)) - 32;
            int barWidth = betterRound((value-minVal) * BAR_LENGTH_SCALE);

            g2d.setColor(barColor);
            g2d.fillRect(barX, barY, betterRound(barWidth), VERTICAL_SPACING);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(barX, barY, betterRound((maxVal-minVal)*BAR_LENGTH_SCALE), VERTICAL_SPACING);
        }

        g2d.setColor(Color.WHITE);
        g2d.setFont(Stat.STAT_FONT);
        g2d.drawString(format(label, value), x, y);
    }


}

