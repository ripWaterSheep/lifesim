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


    public Rect copy() {
        return new Rect(getCenterPos(), getDims());
    }


    public Vector2D getCornerPos() {
        return new Vector2D(x, y);
    }

    public Vector2D getCenterPos() {
        return new Vector2D(getCenterX(), getCenterY());
    }

    public Vector2D getDims() {
        return new Vector2D(width, height);
    }


    public Vector2D getPosClampedOutside(Rect rect) {
        final Vector2D pos = rect.getCenterPos();
        final double midWidth1 = getDims().x / 2;
        final double midHeight1 = getDims().y / 2;
        final double midWidth2 = rect.getDims().x / 2;
        final double midHeight2 = rect.getDims().y / 2;

        final double sumMidWidths = midWidth1 + midWidth2;
        final double sumMidHeights = midHeight2 + midHeight1;

        double x = getCenterX();
        double y =getCenterY();
        if (x - midWidth1 > pos.x - midWidth2 && x + midWidth1 < pos.x + midWidth2) {
            if (y > pos.y) {
                y = max(y, pos.y + sumMidHeights);
            } else {
                y = min(y, pos.y - sumMidHeights);
            }
        }
        if (y - midHeight1 > pos.y - midHeight2 && y + midHeight1 < pos.y + midHeight2) {
            if (x > pos.x) {
                x = max(x, pos.x + sumMidWidths);
            } else {
                x = min(x, pos.x - sumMidWidths);
            }
        }
        return new Vector2D(x, y);
    }

}
