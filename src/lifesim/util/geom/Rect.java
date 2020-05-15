package lifesim.util.geom;

import java.awt.geom.Rectangle2D;

import static java.lang.Math.*;


public class Rect extends Rectangle2D.Double {

    public static Rect fromCorner(Vector2D pos, Vector2D dims) {
        return new Rect(pos.translate(dims.x/2, dims.y/2), new Vector2D(dims.x, dims.y));
    }

    public Rect(Vector2D centerPos, Vector2D dims) {
        super(centerPos.x - dims.x/2, centerPos.y - dims.y/2, dims.x, dims.y);
    }

    public Vector2D getCenterPos() {
        return new Vector2D(getCenterX(), getCenterY());
    }

    public Vector2D getDims() {
        return new Vector2D(width, height);
    }


    public void translatePos(Vector2D v) {
        Rect rect = new Rect(getCenterPos().translate(v), getDims());
        setRect(rect);
    }

    public void scale(double x, double y) {
        Rect rect = new Rect(getCenterPos(), getDims().scale(x, y));
        setRect(rect);
    }


    public void clampBottomOutside(Rectangle2D.Double rect) {
        /* If this rect's bottom bound is inside the other rect, keep it outside.
        This method is used to allow entities to overlap solid entities to give the illusion of 3D
        without letting them walk over it with their feet. */
        double bottom = getMaxY() - 1;

        if (getCenterX() > rect.x && getCenterX() < rect.getMaxX()) {
            if (bottom > rect.getCenterY()) {
                bottom = max(bottom, rect.getMaxY());
            } else {
                bottom = min(bottom, rect.y);
            }
        }

        if (y > rect.y && bottom < rect.getMaxY()) {
            if (getCenterX() > rect.getCenterX()) {
                x = max(x, rect.getMaxX() - 0.01);
            } else {
                x = min(x, rect.x - width + 0.01);
            }
        }
        y = bottom + 1 - height;
    }

}
