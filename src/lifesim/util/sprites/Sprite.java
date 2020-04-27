package lifesim.util.sprites;

import lifesim.util.geom.Rect;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public abstract class Sprite {

    protected final Vector2D size;

    public Sprite(Vector2D size) {
        this.size = size;
    }

    public final Vector2D getSize() {
        return size.copy();
    }

    public Rect getBoundsAt(Vector2D pos) {
        return new Rect(pos, size);
    }


    public abstract void render(Graphics2D g2d, Vector2D pos, Vector2D movement);

}
