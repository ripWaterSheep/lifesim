import Geometry.Point;

public class FindTriangleIncenter {
    public static void main(String[] args) {
        Point a = new Point(0,0);
        Point b = new Point(20,0);
        Point c = new Point(10,20);


        double xCoord = (a.x + b.x + c.x)/3f;
        double yCoord = (a.y + b.y + c.y)/3f;


        System.out.println("xCoord: " + xCoord);
        System.out.println("yCoord: " + yCoord);
    }
}
