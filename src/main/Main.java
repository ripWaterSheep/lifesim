package main;


import javax.swing.*;

public class Main {

    static JPanel panel;

    public static JPanel getPanel() {
        return panel;
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("LifeSim");
        panel = new MainPanel();
        frame.setSize(panel.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);

    }
}
