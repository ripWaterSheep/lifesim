package com.lifesim.main;

import com.lifesim.main.game.GameManager;
import com.lifesim.main.util.MiscUtil;

import javax.swing.*;
import java.awt.*;


public class Main {


    static GamePanel panel = new GamePanel();

    public static GamePanel getPanel() {
        return panel;
    }


    private static void initPanel() {
        MiscUtil.initFrame(new JFrame(""), panel);
}


    public static void main(String[] args) {
        GameManager.startNew();
        initPanel();
    }

}
