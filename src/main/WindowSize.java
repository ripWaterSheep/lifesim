package main;


import java.awt.*;

import static main.Main.panel;


public class WindowSize {

    public static final int defaultWidth = 1800;
    public static final int defaultHeight = 1000;


    public static int getWidth() {
        return panel.getWidth();
    }

    public static int getHeight() {
        return panel.getHeight();
    }

    public static Rectangle getRect() {
        return new Rectangle(0, 0, getWidth(), getHeight());
    }

    public static int getMidWidth() {
        return panel.getWidth()/2;
    }

    public static int getMidHeight() {
        return panel.getHeight()/2;
    }


}
