import Geometry.Point;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("");
        frame.setSize(800,800);
        frame.setLocation(1080/2 , 1920/2 - 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new OurPanel());
        frame.setVisible(true);
    }
}







class OurPanel extends JPanel{
    
    private Point startingTop = new Point(15,0);
    private Point startingLeft = new Point(0,30);
    private Point startingRight = new Point(30,30);
    
    private Player ourPlayer = new Player(startingTop, startingLeft, startingRight, Color.GREEN);
    private Background ourBackground = new Background(800, 800);




    public OurPanel() {


        setFocusable(true);
        requestFocusInWindow();

        addMouseMotionListener(ourPlayer.ourMouseMotionAdapter);
        addKeyListener(ourPlayer.ourKeyAdapter);
        addMouseListener(ourPlayer.ourMouseAdapter);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ourBackground.run(g);
        ourPlayer.run(g);

        try { Thread.sleep(15); } catch (Exception ignored) {}
        repaint();
    }
}
