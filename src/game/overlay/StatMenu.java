package game.overlay;

import game.GameManager;
import game.ecs.components.PositionComponent;
import game.ecs.entities.player.Player;
import main.Main;
import util.drawing.DrawString;
import util.drawing.FontManager;

import java.awt.*;

import static main.Main.getPanel;

public class StatMenu implements DrawableOverlay {

    private static final int LEFT_PADDING = 0;
    private static final int BOTTOM_PADDING = 0;

    private static final int BAR_WIDTH = 1200;
    private static final int BAR_HEIGHT = 1200;

    private static final int TEXT_SIZE = 45;


    private int currentBarNum = 0;

    private Graphics g;


    @Override
    public void draw(Graphics g) {
        this.g = g;

        writeValue("X", GameManager.getPlayer().get(PositionComponent.class).getX());

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


    private void drawBar(int length) {
        int y = Main.getPanel().getHeight()-((getCurrentBarNum()-1) * BAR_HEIGHT) + BOTTOM_PADDING;

        g.fillRect(LEFT_PADDING, y, BAR_WIDTH, BAR_HEIGHT);
        nextLine();

    }


    private <T> void writeValue(String label, T data) {
        String formattedString = format(label, "" + data);

        int textY = getPanel().getHeight()-(getCurrentBarNum() - (BOTTOM_PADDING));
        DrawString.drawCenteredString(g, formattedString, new Rectangle(LEFT_PADDING, textY, BAR_WIDTH, BAR_HEIGHT), FontManager.getMainFont(TEXT_SIZE), Color.WHITE);
    }

}
