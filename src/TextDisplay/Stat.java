package TextDisplay;

import GameComponents.Player;
import Util.MyMath;
import Util.WindowSize;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;



public class Stat {

    static ArrayList<Stat> instances = new ArrayList<>();

    public static ArrayList<Stat> getInstances() { return instances; }


    private static final Font STAT_FONT = new Font("TimesRoman", Font.PLAIN, 20);

    public static final int LEFT_PADDING = 3;
    private static final int BOTTOM_PADDING = 5;
    private static final int VERTICAL_SPACING = 26;

    public static final int FULL_BAR_LENGTH = 225;


    private String format(String label, Double data) {
        int roundedData = MyMath.betterRound(data);
        return label + ": " + roundedData;
    }


    public static void retrieveValues() {
        Player player = Player.getInstance();
        instances.clear();

        Stat xStat = new Stat("X", player.getX());
        Stat yStat = new Stat("Y", player.getY());

        Stat healthStat = new Stat("Health", player.getHealth(), 0, 1000, Color.RED);
        Stat energyStat = new Stat("Energy", player.getEnergy(), 0, 1000, Color.ORANGE);
        Stat moneyStat = new Stat("Cash", player.getMoney(), 0, 10000, Color.green);


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

        this.barColor = barColor;
        this.showStatusBar = true;
    }


    public void draw(Graphics g) {

        int x = LEFT_PADDING+3;
        int y = WindowSize.getHeight()-((instances.indexOf(this))* VERTICAL_SPACING) - (BOTTOM_PADDING+44);

        if (showStatusBar) {
            int barX = LEFT_PADDING;
            int barY = MyMath.betterRound(y + (0.5 * VERTICAL_SPACING)) - 32;
            int barWidth = MyMath.betterRound(((value)/(maxVal)) * FULL_BAR_LENGTH);
            barWidth = MyMath.clamp(barWidth, 0, FULL_BAR_LENGTH);

            g.setColor(barColor);
            g.fillRect(barX, barY, MyMath.betterRound(barWidth), VERTICAL_SPACING);

            g.setColor(Color.BLACK);
            g.drawRect(barX, barY, FULL_BAR_LENGTH, VERTICAL_SPACING);
        }

        g.setColor(Color.WHITE);
        g.setFont(Stat.STAT_FONT);
        g.drawString(format(label, value), x, y);
    }


}

