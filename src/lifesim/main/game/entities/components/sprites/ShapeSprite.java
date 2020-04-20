package lifesim.main.game.entities.components.sprites;

import lifesim.main.util.math.Vector2D;

import java.awt.*;

public class ShapeSprite extends Sprite {

    protected final Color color;

    public ShapeSprite(double width, double height, Color color) {
       super(new Vector2D(width, height));
        this.color = color;
    }


    @Override
    public void render(Graphics2D g2d, Vector2D pos, Vector2D velocity) {
        if (color != null) {
            g2d.setColor(color);
            g2d.fill(getShapeAt(pos));
        }
    }

}
