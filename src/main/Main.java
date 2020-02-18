package main;

import game.Game;
import game.controls.ControlSetup;
import game.setting.layout.DefaultLayout;
import game.setting.layout.Layout;

import javax.swing.*;


public class Main {

    static Game currentGame;

    public static Game getCurrentGame() {
        return currentGame;
    }

    private static void newGame(Layout layout) {
        currentGame = new Game(layout);
        ControlSetup.initListeners(panel);
    }

    public static void startNew() {
        newGame(new DefaultLayout());
    }

    public static void startFromSavePoint(Layout layout) {
        newGame(layout);
    }


    static MainPanel panel;

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
    }


    public static void main(String[] args) {
        initPanel();
        startNew();
    }

}
