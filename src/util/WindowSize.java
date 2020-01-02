package util;

import main.Main;

import java.awt.*;

public class WindowSize {

    public static int defaultWidth = 1400;
    public static int defaultHeight = 800;


    public static int getWidth() { return Main.frame.getWidth(); }

    public static int getHeight() { return Main.frame.getHeight(); }

    public static Rectangle getRect() {
        return new Rectangle(0, 0, WindowSize.getWidth(), WindowSize.getHeight());
    }


    public static int getMidWidth() {
        return Main.frame.getWidth()/2;
    }

    public static int getMidHeight() {
        return Main.frame.getHeight()/2;
    }



}
