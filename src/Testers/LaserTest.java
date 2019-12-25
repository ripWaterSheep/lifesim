package Testers;

import Geometry.Line;
import Subsystems.Laser;
import Util.UtilMethods;

import javax.swing.*;
import java.awt.*;

public class LaserTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800,800);
        frame.setLocation(0,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new LaserPanel());
        frame.setVisible(true);
    }
}

class LaserPanel extends JPanel {

    private Laser laser = new Laser(new Line(400,400,405,405), 5);


    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(),getHeight());


        laser.run(g);
        UtilMethods.sleep(15);

        repaint();
    }
}
