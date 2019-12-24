import Geometry.Point;
import Geometry.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_DOWN;

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
    
    public OurPanel() {
        setFocusable(true);
        requestFocusInWindow();

        addMouseListener(ourPlayer.mouseListener);
        addKeyListener(ourPlayer.ourListener);
    }









    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight()); // clear the screen


        ourPlayer.run(g);

        try { Thread.sleep(15); } catch (Exception ignored) {}
        repaint();
    }
}
