package main;


import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame frame;
    public static JPanel panel;

    public static void main(String[] args) {
        panel = new MainPanel();
        frame = new JFrame("LifeSim");
        frame.setSize(panel.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);

    }
}
