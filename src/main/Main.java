package main;


import javax.swing.*;
import java.awt.*;

public class Main {

    static JPanel panel;

    public static void main(String[] args) {

        JFrame frame = new JFrame("LifeSim");
        panel = new MainPanel();
        frame.setSize(panel.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);

    }
}
