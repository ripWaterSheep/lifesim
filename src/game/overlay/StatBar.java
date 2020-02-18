package game.overlay;

import game.ECS.components.HealthComponent;
import game.ECS.components.PositionComponent;
import game.ECS.components.StatsComponent;
import game.ECS.entities.Player;
import util.ColorMethods;

import java.awt.*;
import java.util.ArrayList;

import static main.Main.getPanel;
import static util.MyMath.betterRound;


public class StatBar {

    static final ArrayList<StatBar> shownStats = new ArrayList<>();


    private static final Font STAT_FONT = new Font("StayPuft", Font.PLAIN, 26);

    private static final int LEFT_PADDING = 0;
    private static final int BOTTOM_PADDING = 0;
    private static final int VERTICAL_SPACING = 36;

    private static final double DEFAULT_BAR_LENGTH_SCALE = 0.155;

    private static final int DEFAULT_OPACITY = betterRound(0.5*255);


    private String format(String label, Double data) {
        int roundedData = betterRound(data);
        return label + ": " + roundedData;
    }


    public static void retrieveValues() {
        Player player = Player.getInstance();
        PositionComponent pos = player.get(PositionComponent.class);
        HealthComponent health = player.get(HealthComponent.class);
        StatsComponent stats = player.get(StatsComponent.class);
        shownStats.clear();

        new StatBar("X", pos.getX());
        new StatBar("Y", pos.getY());

        new StatBar("Health", health.getHealth(), 0, 1000, 1, HealthComponent.Colors.bloodColor);
        new StatBar("Energy", stats.getEnergy(), 0, 1000, 1, StatsComponent.Colors.energyColor);
        new StatBar("Strength", stats.getStrength(), 0, 5000, 0.2, StatsComponent.Colors.strengthColor);
        new StatBar("Cash", stats.getMoney(), 0, 10000, 0.1, StatsComponent.Colors.moneyColor);
        new StatBar("Intellect", stats.getIntellect(), 0, 5000, 0.2, StatsComponent.Colors.intellectColor);

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

        this.barLength = (int)(maxVal*DEFAULT_BAR_LENGTH_SCALE*barLengthScale);
        this.statLength = (int)(barLength*(value-minVal)/(maxVal-minVal));
        this.barColor = ColorMethods.applyOpacity(barColor, DEFAULT_OPACITY);

        this.showBar = true;
    }



    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int textX = LEFT_PADDING+3;
        int textY = getPanel().getHeight()-((shownStats.indexOf(this))* VERTICAL_SPACING) - (BOTTOM_PADDING)-8;
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

