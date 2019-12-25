package Testers;

import Geometry.Line;
import Geometry.Point;

public class TestLine {
    public static void main(String[] args) {
        Line firstLine = new Line(new Point(0,0), new Point(0,10));
        Line secondLine = new Line(new Point(0,0), new Point(10,0));
        Line thirdLine = new Line(new Point(0,0), new Point(4,4));


    //    thirdLine.endPoint = thirdLine.extendLine(thirdLine.endPoint, 4 * Math.sqrt(2));

        System.out.println(firstLine.getSlope()); // very big number
        System.out.println(secondLine.getSlope()); // 0
        System.out.println(thirdLine.getSlope()); // 1
   //     System.out.println(thirdLine.endPoint.x + " , " + thirdLine.endPoint.y); // 8,8

        thirdLine.shiftAlongLine(8 * Math.sqrt(2));

        System.out.println(thirdLine.endPoint.x + " , " + thirdLine.endPoint.y); // 12,12
        System.out.println(thirdLine.startPoint.x + " , " + thirdLine.startPoint.y);

        for(int i=0; i<5; i++) {
            thirdLine.shiftAlongLine(2 * Math.sqrt(2));
            System.out.println("\n");
            System.out.println("\n" + "------------");
            System.out.println(thirdLine.endPoint.x + " , " + thirdLine.endPoint.y);
            System.out.println(thirdLine.startPoint.x + " , " + thirdLine.startPoint.y);
        }



        Line[] parallelLines = thirdLine.getParallelLine(10);
        System.out.println("\n");
        System.out.println("\n" + "------ p lines -----");
        System.out.println(parallelLines[0].startPoint.x + " , " + parallelLines[0].startPoint.y);
        System.out.println(parallelLines[0].endPoint.x + " , " + parallelLines[0].endPoint.y);
        System.out.println(parallelLines[1].startPoint.x + " , " + parallelLines[1].startPoint.y);
        System.out.println(parallelLines[1].endPoint.x + " , " + parallelLines[1].endPoint.y);

        System.out.println("\n" + "distance between endpoint1: " + Math.hypot(parallelLines[0].endPoint.x - parallelLines[0].startPoint.x, parallelLines[0].endPoint.y - parallelLines[0].startPoint.y));
        System.out.println("distance between endpoint2: " + Math.hypot(parallelLines[1].endPoint.x - parallelLines[1].startPoint.x, parallelLines[1].endPoint.y - parallelLines[1].startPoint.y));

    }
}
