package main;

import javax.swing.*;


public class Main {


    static MainPanel panel = new MainPanel();

    public static MainPanel getPanel() {
        return panel;
    }


    private static void initPanel() {
        JFrame frame = new JFrame("");
        frame.setSize(panel.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        GameManager.startNew();
        initPanel();
    }

}
