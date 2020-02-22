package game.activity.controls;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.BUTTON1;
import static java.awt.event.MouseEvent.BUTTON3;

public class MouseControls {


    private static boolean leftClicked = false,
            rightClicked = false;


    public static boolean getLeftClicked() {
        return leftClicked;
    }

    public static boolean getRightClicked() {
        return rightClicked;
    }

    private static int lastClickX = 0,
            lastClickY = 0;

    public static int getLastClickX() {
        return lastClickX;
    }

    public static int getLastClickY() {
        return lastClickY;
    }


    static MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            lastClickX = e.getX();
            lastClickY = e.getY();
            //System.out.println(KeyboardControls.getLastClickX() + "  " + KeyboardControls.getLastClickY());
            switch (e.getButton()) {
                case BUTTON1:
                    leftClicked = true;
                    break;

                case BUTTON3:
                    rightClicked = true;
                    break;
            }
        }


    };



    // Reset in the beginning of each frame
    public static void reset() {
        leftClicked = false;
        rightClicked = false;
    }


    static void init(JPanel panel) {
        panel.addMouseListener(mouseAdapter);
    }

}
