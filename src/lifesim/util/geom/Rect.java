package lifesim.util.geom;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static java.lang.Math.*;


public class Rect extends Rectangle2D.Double {

    public static Rect fromCorner(Vector2D pos, Vector2D dims) {
        return new Rect(pos.translate(dims.x/2, dims.y/2), new Vector2D(dims.x, dims.y));
    }

    public Rect(Vector2D centerPos, Vector2D dims) {
        super(centerPos.x - dims.x/2, centerPos.y - dims.y/2, dims.x, dims.y);
    }

    public Rect(Rectangle r) {
        this(new Vector2D(r.x, r.y), new Vector2D(r.width, r.height));
    }

    public Vector2D getCenterPos() {
        return new Vector2D(getCenterX(), getCenterY());
    }

    public Vector2D getDims() {
        return new Vector2D(width, height);
    }


    public Vector2D getPosClampedOutside(Rectangle2D.Double rect) {
        /* If one of this rect's horizontal bounds intersects the other rect's,
          don't let this rect intersect the other rect's vertical bounds (and vice versa).
        */
        if (getMinX() > rect.getMinX() && getMaxX() < rect.getMaxX()) {
            if (getMaxY() >= rect.getMaxY()) {
                y = max(y, rect.y + rect.height);
            } else if (getMinY() <= getMinY()) {
                y = min(y, rect.y - height);
            }
        }
        if (getMinY() > rect.getMinY() && getMaxY() < rect.getMaxY()) {
            if (getMaxX() >= rect.getMaxX()) {
                x = max(x, rect.x + rect.width);
            } else if (getMinX() <= getMinX()) {
                x = min(x, rect.x - width);
            }
        }
        return getCenterPos().copy();
    }

}
