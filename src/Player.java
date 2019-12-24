import Geometry.Point;
import Geometry.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

import static java.awt.event.KeyEvent.*;

public class Player extends Triangle implements Subsystem {
    private Telemetry telemetry = new Telemetry();
    private Color ourColor;

    private double clickAngle = 0;

    // translation
    private int movementX = 0;
    private int movementY = 0;




    public KeyAdapter ourKeyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("key pressed: " + e.toString());
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
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key released: " + e.toString());
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

            }
        }
    };




    public MouseMotionAdapter ourMouseMotionAdapter = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            clickAngle = -Math.toDegrees(Math.atan2(e.getX() - (top.x + left.x + right.x)/3f, e.getY() - (top.x + left.x + right.x)/3f)) + 90;
        }
    };




    public MouseAdapter ourMouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            clickAngle = -Math.toDegrees(Math.atan2(e.getX() - (top.x + left.x + right.x)/3f, e.getY() - (top.x + left.x + right.x)/3f)) + 90;
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
        ourColor = color;
    }








// draws from the point numbers
    private void draw(Graphics g) {
        offsetMovement();

        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform at = new AffineTransform();


        int xE = (top.x + left.x + right.x)/3;
        int yE = (top.y + left.y + right.y)/3;



        int[] xPoints = {
                left.x,
                top.x,
                right.x
        };

        int[] yPoints = {
                left.y,
                top.y,
                right.y
        };


        at.setToRotation(Math.toRadians(clickAngle+90), xE, yE);
        g2d.setTransform(at);
        g2d.setColor(ourColor);
        g2d.drawPolygon(xPoints, yPoints, 3);


        // Guide
        g2d.setColor(Color.RED);
        g2d.drawLine(xE, yE, top.x, top.y);
    }



    // this changes the point numbers
    private void offsetMovement() {
        offset(movementX, movementY);
    }





    @Override
    public void run(Graphics g) {
        draw(g);
        telemetry.run(g);
    }
}
