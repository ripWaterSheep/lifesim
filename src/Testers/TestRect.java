package Testers;

import Geometry.Point;
import Geometry.Rectangle;

public class TestRect {
    public static void main(String[] args) {

        Rectangle ourRect = new Rectangle(new Point(0,10), new Point(10,10), new Point(0,0), new Point(10,0));

        System.out.println(Math.toDegrees(ourRect.getHeading()));
        System.out.println(ourRect);


        Rectangle upwardsRect = new Rectangle(new Point(0,0), new Point(0,10), new Point(10,0), new Point(10,10));

        System.out.println(Math.toDegrees(upwardsRect.getHeading()));
        System.out.println(upwardsRect);


        ourRect.shiftAlongHorizontalLine(10);
        System.out.println(ourRect);
    }
}
