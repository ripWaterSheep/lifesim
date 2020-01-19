package game.overlay;

import game.GameSession;
import game.components.GameComponent;
import game.components.entities.player.Player;
import main.WindowSize;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static util.MyMath.betterRound;


public class MiniMap {

    private static final int PADDING = 5;

    private static final int DIAMETER = 100;

    private static int getRadius() { return DIAMETER/2; }


    private static final double MAP_SCALE = 0.055;

    private static double scale(double dimension) { return dimension * MAP_SCALE; }


    private static Ellipse2D.Double getFrameShape() { return new Ellipse2D.Double(0, 0, DIAMETER+(PADDING*2), DIAMETER+(PADDING*2)); }

    private static Ellipse2D.Double getMapShape() { return new Ellipse2D.Double(PADDING, PADDING, DIAMETER, DIAMETER); }


    private static Color frameColor = new Color(0, 0, 0);



    static void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(frameColor);
        g2d.fill(getFrameShape());

        g2d.clip(getMapShape());
        g2d.setColor(Player.getInstance().getWorld().getOuterColor());
        g2d.fill(getMapShape());
        for (GameComponent component: GameSession.getUsedComponents()) {

            if (component.getWorld() == Player.getInstance().getWorld() && component.isVisible()) {
                // Scale features to fit on mini map
                int scaledX = betterRound(scale(component.getDisplayX() - WindowSize.getMidWidth()) + getRadius() + PADDING);
                int scaledY = betterRound(scale(component.getDisplayY() - WindowSize.getMidHeight()) + getRadius() + PADDING);

                int scaledWidth = betterRound(scale(component.getWidth()));
                int scaledHeight = betterRound(scale(component.getHeight()));

                // If component has a color, fill the shape with that color.
                if (component.getColor() != null) {
                    g2d.setColor(component.getColor());
                    if (component.getShape() instanceof Ellipse2D.Double) {
                        g2d.fillOval(scaledX, scaledY, scaledWidth, scaledHeight);
                    } else {
                        g2d.fillRect(scaledX, scaledY, scaledWidth, scaledHeight);
                    }
                } else {
                    component.getImage().draw(g, scaledX, scaledY, scaledWidth, scaledHeight);
                }

            }
        }


    }


}
