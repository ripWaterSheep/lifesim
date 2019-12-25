package Geometry;

public class Line {
    public Point startPoint;
    public Point endPoint;


    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }


    public Line(double x1, double y1, double x2, double y2) {
        startPoint = new Point((int)x1, (int)y1);
        endPoint = new Point((int)x2, (int)y2);
    }




    public Point[] getPoints() {
        return new Point[] {
                startPoint,
                endPoint
        };
    }


    public void setPoints(Point dStartPoint, Point dEndPoint) {
        startPoint = dStartPoint;
        endPoint = dEndPoint;
    }



    public double getLength() {
        return Math.hypot(startPoint.x - endPoint.x, startPoint.y - endPoint.y);
    }




    /**
     *
     * @param d - distance away
     * @return - new point that is d distance away on the line between
     */

    private Point extendLine(Point extensionPoint, double d, double m) {
        double x = extensionPoint.x + d * Math.sqrt(1.0 / (1.0 + m * m));
        double y = extensionPoint.y + m * d * Math.sqrt(1f / (1f + m * m));

        return new Point((int)x, (int)y);
    }



    public void shiftAlongLine(double d) {
        startPoint = extendLine(startPoint, d, getSlope());
        endPoint = extendLine(endPoint, d, getSlope());
    }


    /**
     * @param d length of line segment
     * @return parallel line segment with endpoints of the side vertices
     */
    public Line[] getParallelLine(double d) {
        Point firstPoint = startPoint;
        Point secondPoint = extendLine(startPoint, d, 1f/getSlope());

        Point endFirstPoint = endPoint;
        Point endSecondPoint = extendLine(endPoint, d, 1f/getSlope());

        return new Line[] {
                new Line(firstPoint, secondPoint),
                new Line(endFirstPoint, endSecondPoint)
        };
    }



    public double getSlope() {
        if(startPoint.x == endPoint.x) {
            startPoint.x += 0.003;
        }

        return ((startPoint.y - endPoint.y) * 1.0) / ((startPoint.x - endPoint.x) * 1.0);
    }






    @Override
    public String toString() {
        return "[" + startPoint + " , " + endPoint + "]";
    }
}
