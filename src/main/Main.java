package main;

import javax.swing.*;

import static main.WindowSize.*;


public class Main {

    static MainPanel panel;

    public static MainPanel getPanel() {
        return panel;
    }


    public static void main(String[] args) {
        panel = new MainPanel();
        JFrame frame = new JFrame("LifeSim");
        frame.setSize(defaultWidth, defaultHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
}
