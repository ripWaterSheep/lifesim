package game.overlay;

import game.ECS.components.PositionComponent;
import game.ECS.entities.Player;
import game.GraphicsManager;
import main.Main;
import util.drawing.DrawString;

import java.awt.*;

import static main.Main.getPanel;

public class StatMenu implements DrawableOverlay {

    private static final int LEFT_PADDING = 0;
    private static final int BOTTOM_PADDING = 0;

    private static final int BAR_WIDTH = 1200;
    private static final int BAR_HEIGHT = 1200;


    private int currentBarNum = 0;


    @Override
    public void draw(Graphics g) {

        writeValue("X", Player.getInstance().get(PositionComponent.class).getX());


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


    private void drawbar(int length) {
        Graphics2D g2d = GraphicsManager.getGraphics();

        int x = LEFT_PADDING;
        int y = Main.getPanel().getHeight()-((getCurrentBarNum()-1) * BAR_HEIGHT) + BOTTOM_PADDING;

        g2d.fillRect(x, y, BAR_WIDTH, BAR_HEIGHT);
        nextLine();

    }


    private <T> void writeValue(String label, T data) {
        String formattedString = format(label, "" + data);
        int textX = LEFT_PADDING;
        int textY = getPanel().getHeight()-(getCurrentBarNum() - (BOTTOM_PADDING));
    }

}
