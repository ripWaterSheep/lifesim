package main;


import game.Game;

import javax.swing.*;
import java.awt.*;

public class Main {

    static MainPanel panel;

    public static void restartGame() {
        initPanel();
    }


    private static void initPanel() {
        JFrame frame = new JFrame("");
        panel = new MainPanel();
        frame.setSize(panel.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void setPanelColor(Color color) {
        panel.setBackground(color);
    }


    public static void main(String[] args) {
        initPanel();
    }


}
