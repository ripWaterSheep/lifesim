package main;


import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame frame;
    public static JPanel panel;

    public static void main(String[] args) {
        panel = new MainPanel();
        frame = new JFrame("");
        frame.setSize(panel.getSize());
        frame.setLocation(0, 0);
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);

    }
}
