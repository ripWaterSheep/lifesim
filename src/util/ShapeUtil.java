package util;

import game.components.GameComponent;

import java.awt.*;
import java.awt.geom.Area;

public class ShapeUtil {

    /** Since ellipse.intersects doesn't accept another ellipse as a parameter, (kinda cringe ik)
     * its just better to use one function for everything.
     */

    public static boolean testIntersection(Shape shapeA, Shape shapeB) {
        Area areaA = new Area(shapeA);
        areaA.intersect(new Area(shapeB));
        return !areaA.isEmpty();
    }


}
