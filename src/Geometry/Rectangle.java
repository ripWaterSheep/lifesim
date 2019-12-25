package Geometry;

public class Rectangle {
    Line topLine;
    Line rightLine;
    Line leftLine;
    Line bottomLine;

    public Rectangle(Point frontLeft, Point frontRight, Point backLeft, Point backRight) {
        topLine = new Line(frontLeft, frontRight);
        leftLine = new Line(backLeft, frontLeft);
        rightLine = new Line(backRight, frontRight);
        bottomLine = new Line(backLeft, backRight);
    }






    public void shiftAlongHorizontalLine(double d) {
        topLine.shiftAlongLine(d);
        rightLine.shiftAlongLine(d);

        leftLine = topLine.getParallelLine(leftLine.getLength())[1];
        rightLine = topLine.getParallelLine(rightLine.getLength())[0];
    }


    public void shiftAlongVerticalLine(double d) {
        leftLine.shiftAlongLine(d);
        rightLine.shiftAlongLine(d);

        topLine = leftLine.getParallelLine(topLine.getLength())[1];
        bottomLine = leftLine.getParallelLine(bottomLine.getLength())[0];
    }



    public double getHeading() {
        return Math.atan2(topLine.endPoint.y - topLine.startPoint.y, topLine.endPoint.x - topLine.startPoint.x);
    }




    @Override
    public String toString() {
        return "[" + leftLine.endPoint + " , " + topLine.endPoint + " , " + leftLine.startPoint + " , " + rightLine.startPoint + " ] ";
    }
}
