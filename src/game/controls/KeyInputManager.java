package game.controls;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;


public final class KeyInputManager {

    static final ArrayList<KeyInputListener> keys = new ArrayList<>();

    public static final KeyInputListener k_a = new KeyInputListener(VK_A);
    public static final KeyInputListener k_b = new KeyInputListener(VK_B);
    public static final KeyInputListener k_c = new KeyInputListener(VK_C);
    public static final KeyInputListener k_d = new KeyInputListener(VK_D);
    public static final KeyInputListener k_e = new KeyInputListener(VK_E);
    public static final KeyInputListener k_f = new KeyInputListener(VK_F);
    public static final KeyInputListener k_g = new KeyInputListener(VK_G);
    public static final KeyInputListener k_h = new KeyInputListener(VK_H);
    public static final KeyInputListener k_i = new KeyInputListener(VK_I);
    public static final KeyInputListener k_j = new KeyInputListener(VK_J);
    public static final KeyInputListener k_k = new KeyInputListener(VK_K);
    public static final KeyInputListener k_l = new KeyInputListener(VK_L);
    public static final KeyInputListener k_m = new KeyInputListener(VK_M);
    public static final KeyInputListener k_n = new KeyInputListener(VK_N);
    public static final KeyInputListener k_o = new KeyInputListener(VK_O);
    public static final KeyInputListener k_p = new KeyInputListener(VK_P);
    public static final KeyInputListener k_q = new KeyInputListener(VK_Q);
    public static final KeyInputListener k_r = new KeyInputListener(VK_R);
    public static final KeyInputListener k_s = new KeyInputListener(VK_S);
    public static final KeyInputListener k_t = new KeyInputListener(VK_T);
    public static final KeyInputListener k_u = new KeyInputListener(VK_U);
    public static final KeyInputListener k_v = new KeyInputListener(VK_V);
    public static final KeyInputListener k_w = new KeyInputListener(VK_W);
    public static final KeyInputListener k_x = new KeyInputListener(VK_X);
    public static final KeyInputListener k_y = new KeyInputListener(VK_Y);
    public static final KeyInputListener k_z = new KeyInputListener(VK_Z);

    public static final KeyInputListener k_1 = new KeyInputListener(VK_1);
    public static final KeyInputListener k_2 = new KeyInputListener(VK_2);
    public static final KeyInputListener k_3 = new KeyInputListener(VK_3);
    public static final KeyInputListener k_4 = new KeyInputListener(VK_4);
    public static final KeyInputListener k_5 = new KeyInputListener(VK_5);
    public static final KeyInputListener k_6 = new KeyInputListener(VK_6);
    public static final KeyInputListener k_7 = new KeyInputListener(VK_7);
    public static final KeyInputListener k_8 = new KeyInputListener(VK_8);
    public static final KeyInputListener k_9 = new KeyInputListener(VK_9);
    public static final KeyInputListener k_0 = new KeyInputListener(VK_0);

    public static final KeyInputListener k_up = new KeyInputListener(VK_UP);
    public static final KeyInputListener k_down = new KeyInputListener(VK_DOWN);
    public static final KeyInputListener k_left = new KeyInputListener(VK_LEFT);
    public static final KeyInputListener k_right = new KeyInputListener(VK_RIGHT);

    public static final KeyInputListener k_space = new KeyInputListener(VK_SPACE);
    public static final KeyInputListener k_shift = new KeyInputListener(VK_SHIFT);


    public static boolean isAnyKeyClicked() {
        for (KeyInputListener key: keys) {
            if (key.isClicked()) {
                return true;
            }
        }
        return false;
    }



    static void init(JPanel panel) {
        panel.addKeyListener(keyAdapter);
        panel.addFocusListener(AFKKeyPreventor);
    }


    static void run() {
        for (KeyInputListener key: keys) {
            key.run();
        }
    }


    private static KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            for (KeyInputListener key: keys) {
                if (key.getIntCode() == keyCode) {
                    key.doPress();
                }
            }
            Cheats.cheatLogic(keyCode);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            for (KeyInputListener key: keys) {
                if (key.getIntCode() == e.getKeyCode()) {
                    key.doRelease();
                }
            }
        }

    };


    private static FocusAdapter AFKKeyPreventor = new FocusAdapter() {

        @Override
        public void focusLost(FocusEvent e) {
            for (KeyInputListener key: keys) {
                key.doRelease();
            }
        }
    };

}
