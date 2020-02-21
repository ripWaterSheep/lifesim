package main;

import game.Game;
import game.controls.ControlSetup;
import game.setting.layout.DefaultLayout;
import game.setting.layout.Layout;

import javax.swing.*;


public class Main {

    private static MainPanel panel;

    public static MainPanel getPanel() {
        return panel;
    }


    private static void initPanel() {
        JFrame frame = new JFrame("");
        panel = new MainPanel();
        frame.setSize(panel.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);

        ControlSetup.initListeners(panel);
    }


    public static void main(String[] args) {
        GameManager.startNew();
        initPanel();
    }

}
