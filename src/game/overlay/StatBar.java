package game.overlay;

import game.GameManager;
import game.ecs.components.HealthComponent;
import game.ecs.components.PositionComponent;
import game.ecs.components.StatsComponent;
import game.ecs.entities.player.Player;
import main.Main;
import util.drawing.DrawString;
import util.drawing.FontManager;

import java.awt.*;

import static java.lang.Math.min;
import static main.Main.getPanel;
import static util.ColorMethods.applyOpacity;
import static util.MyMath.betterRound;

public class StatBar implements DrawableOverlay {

    private static final int LEFT_PADDING = 4;
    private static final int BOTTOM_PADDING = 4;

    private static final int BAR_HEIGHT = 40;

    private static final int BAR_OPACITY = 125;

    private static final int TEXT_SIZE = 30;


    private Graphics g;

    private int currentBarNum = 1;



    @Override
    public void draw(Graphics g) {
        this.g = g;
        Player player = GameManager.getPlayer();
        StatsComponent stats = player.get(StatsComponent.class);

        drawBar("Intellect", stats.getIntellect(), 0.2, 1000, StatsComponent.Colors.intellectColor);
        drawBar("Money", stats.getMoney(), 0.02, 10000, StatsComponent.Colors.moneyColor);
        drawBar("Strength", stats.getStrength(), 0.2, 1000, StatsComponent.Colors.strengthColor);
        drawBar("Energy", stats.getEnergy(), 0.2, 1000, StatsComponent.Colors.energyColor);
        drawBar("Health", player.get(HealthComponent.class).getHealth(), 0.2, 1000, HealthComponent.Colors.bloodColor);

        writeValue("World", player.getWorld().getName());
        writeNum("Y", player.getPos().getY());
        writeNum("X", player.getPos().getX());


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


    private <T> void drawBar(String label, double data, double scale, double maxDataVal, Color color) {
        int y = Main.getPanel().getHeight() - ((getCurrentBarNum()) * BAR_HEIGHT) - BOTTOM_PADDING;
        int dataWidth = betterRound(min(data, maxDataVal) * scale);

        g.setColor(Color.BLACK);
        g.drawRect(LEFT_PADDING, y, betterRound(maxDataVal * scale), BAR_HEIGHT);

        g.setColor(applyOpacity(color, BAR_OPACITY));
        g.fillRect(LEFT_PADDING, y, dataWidth, BAR_HEIGHT);

        writeValue(label, data);
    }


    private <T> void writeValue(String label, T data) {
        String formattedString = format(label, data+"");

        int textY = getPanel().getHeight() - (getCurrentBarNum()*BAR_HEIGHT) - BOTTOM_PADDING;
        DrawString.drawVerticallyCenteredString(g, formattedString, LEFT_PADDING+3,
                new Rectangle(LEFT_PADDING, textY, 0, BAR_HEIGHT), FontManager.getMainFont(TEXT_SIZE), Color.WHITE);
        nextLine();
    }


    private <T> void writeNum(String label, double data) {
        writeValue(label, betterRound(data));
    }

}
