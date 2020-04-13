package lifesim.main.util.math;

import java.awt.*;
import java.awt.geom.Area;

import static lifesim.main.util.math.MyMath.betterRound;


public class Geometry {

    public static Rectangle getCenteredRect(Vector2D pos, Vector2D size) {
        return new Rectangle(betterRound(pos.x - (size.x/2)), betterRound(pos.y - (size.y/2)), betterRound(size.x), betterRound(size.y));
    }


    /** Keeps angle within 0 to 360 degrees while preserving angle measure */
    public static double angleWrap(double deg) {
        while (deg < 0) deg += 360;
        while (deg >= 360) deg -= 360;
        return deg;
    }


    public static boolean testIntersection(Shape shapeA, Shape shapeB) {
        Area areaA = new Area(shapeA);
        areaA.intersect(new Area(shapeB));
        return !areaA.isEmpty();
    }

}
