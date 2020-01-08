package game.overlay;

import game.GameSession;
import game.components.GameComponent;
import game.components.player.Player;
import game.components.World;
import main.WindowSize;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


import static util.ColorMethods.applyOpacity;
import static util.MyMath.betterRound;


public class MiniMap {

    private static final int PADDING = 5;

    private static final int DIAMETER = 100;

    private static int getRadius() { return DIAMETER/2; }


    private static final double MAP_SCALE = 0.03;

    private static int scale(int dimension) { return betterRound(dimension * MAP_SCALE); }


    private static Ellipse2D.Double getFrameShape() { return new Ellipse2D.Double(0, 0, DIAMETER+(PADDING*2), DIAMETER+(PADDING*2)); }

    private static Ellipse2D.Double getMapShape() { return new Ellipse2D.Double(PADDING, PADDING, DIAMETER, DIAMETER); }


    private static final int OPACITY = betterRound(0.5 * 255);

    private static Color frameColor = applyOpacity(new Color(50, 50, 50), OPACITY);



    static void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(frameColor);
        g2d.fill(getFrameShape());

        g2d.clip(getMapShape());
        g2d.setColor(Player.getInstance().getWorld().getOuterColor());
        g2d.fill(getMapShape());

        for (ArrayList<? extends  GameComponent> components: GameSession.getUsedComponents()) {
            for (GameComponent component: components) {

                if (Player.getInstance().getWorld() == component.getWorld()) {
                    // Scale features to fit on mini map
                    int x = scale(component.getDisplayX() - WindowSize.getMidWidth()) + getRadius() + PADDING;
                    int y = scale(component.getDisplayY() - WindowSize.getMidHeight()) + getRadius() + PADDING;
                    int width = scale(component.getWidth());
                    int height = scale(component.getHeight());
                    g2d.setColor(component.getColor());

                    if (component.isElliptical()) {
                        g2d.fillOval(x, y, width, height);
                    } else {
                        g2d.fillRect(x, y, width, height);
                    }
                }
            }
        }


    }


}
