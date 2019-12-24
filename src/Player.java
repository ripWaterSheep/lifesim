import Geometry.Point;
import Geometry.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

import static java.awt.event.KeyEvent.*;

public class Player extends Triangle implements Subsystem {
    Telemetry telemetry = new Telemetry();


    Color color;

    public int movementX = 0;
    public int movementY = 0;

    public double heading = 0;
    public double headingChanger;
    public double lastHeading = 0;
    double angle;
    Point clickPoint = new Point(100,100);








    public MouseAdapter mouseListener = new MouseAdapter() {

        public void mouseDragged(MouseEvent e) {
            System.out.println("Mouse dragged called");

            angle = -Math.toDegrees(Math.atan2(e.getPoint().x - clickPoint.x, e.getPoint().y - clickPoint.y)) + 180;


        }
    };







    public KeyListener ourListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_D :
                    movementX = 5;
                    break;

                case VK_A:
                    movementX = -5;
                    break;

                case VK_W:
                    movementY = -5;
                    break;

                case VK_S:
                    movementY = 5;
                    break;

                case VK_F:
                    headingChanger = 1;
                    break;

                case VK_G:
                    headingChanger = -1;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case VK_D :
                    movementX = 0;
                    break;

                case VK_A:
                    movementX = 0;
                    break;

                case VK_W:
                    movementY = 0;
                    break;

                case VK_S:
                    movementY = 0;
                    break;

                case VK_F:
                    headingChanger = 0;
                    break;

                case VK_G:
                    headingChanger = 0;
                    break;
            }
        }
    };








    /**
     *
     * @param top top point
     * @param left left point
     * @param right right point
     * @param color color
     */

    public Player(Point top, Point left, Point right, Color color) {
        super(top,left,right);
        this.color = color;
    }









// draws from the point numbers
    private void draw(Graphics g) {
        int[] xPoints = { top.x, left.x, right.x};
        int[] yPoints = { top.y, left.y, right.y };

        // find size of triangle
        int maxX = 0;
        int maxY = 0;
        for (int index = 0; index < xPoints.length; index++) {
            maxX = Math.max(maxX, xPoints[index]);
        }
        for (int index = 0; index < yPoints.length; index++) {
            maxY = Math.max(maxY, yPoints[index]);
        }

        Dimension triangleDimension = new Dimension(maxX, maxY);




        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.GREEN);
        AffineTransform at = new AffineTransform();
        int x =  (triangleDimension.width / 2);
        int y =  (triangleDimension.height / 2);
        at.translate(x, y);
        at.rotate(Math.toRadians(angle), x, y);
        g2d.setTransform(at);
        g2d.drawPolygon(xPoints, yPoints, 3);
        // Guide
        g2d.setColor(Color.RED);
        g2d.drawLine(triangleDimension.width / 2, 0, triangleDimension.width / 2, triangleDimension.height / 2);
        g2d.dispose();






        telemetry.clear();
        telemetry.addData("heading", heading);
        telemetry.addData("last heading", lastHeading);
        telemetry.addData("movementX,Y,Turn", movementX + " , " + movementY + " , " + headingChanger);
        telemetry.addData("xPoints", Arrays.toString(xPoints));
        telemetry.addData("yPoints", Arrays.toString(yPoints));
        telemetry.addData("maxX,Y", maxX + " , " + maxY);
}







    // this changes the point numbers
    private void offsetMovement() {
        offset(movementX, movementY);
    }


    private double AngleWrap(double angle) {
        while(angle >= Math.PI * 2) {
            angle -= Math.PI * 2;
        }

        while(angle < 0) {
            angle += Math.PI * 2;
        }
        return angle;
    }




    @Override
    public void run(Graphics g) {
        offsetMovement();
        draw(g);
        lastHeading = heading;


        telemetry.run(g);
    }
}
