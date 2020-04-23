package lifesim.util.math.geom;

import java.awt.*;
import java.awt.geom.Area;


public class Geometry {

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
