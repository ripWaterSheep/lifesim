package lifesim.main.game.entities.components.sprites;

import lifesim.main.util.math.Vector2D;
import lifesim.main.util.fileIO.ImageLoader;
import lifesim.main.util.math.Geometry;

import java.awt.*;


public abstract class Sprite {

    protected final Vector2D size;

    public Sprite(Vector2D size) {
        this.size = size;
    }

    public final Vector2D getSize() {
        return size.copy();
    }

    public Shape getShapeAt(Vector2D pos) {
        return Geometry.getCenteredRect(pos, size);
    }


    public abstract void render(Graphics2D g2d, Vector2D pos, Vector2D movement);

}
