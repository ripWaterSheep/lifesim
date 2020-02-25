package game.controls;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;


public final class BetterKeyboard {

    static final ArrayList<BetterKey> keys = new ArrayList<>();

    public static final BetterKey k_a = new BetterKey(VK_A);
    public static final BetterKey k_b = new BetterKey(VK_B);
    public static final BetterKey k_c = new BetterKey(VK_C);
    public static final BetterKey k_d = new BetterKey(VK_D);
    public static final BetterKey k_e = new BetterKey(VK_E);
    public static final BetterKey k_f = new BetterKey(VK_F);
    public static final BetterKey k_g = new BetterKey(VK_G);
    public static final BetterKey k_h = new BetterKey(VK_H);
    public static final BetterKey k_i = new BetterKey(VK_I);
    public static final BetterKey k_j = new BetterKey(VK_J);
    public static final BetterKey k_k = new BetterKey(VK_K);
    public static final BetterKey k_l = new BetterKey(VK_L);
    public static final BetterKey k_m = new BetterKey(VK_M);
    public static final BetterKey k_n = new BetterKey(VK_N);
    public static final BetterKey k_o = new BetterKey(VK_O);
    public static final BetterKey k_p = new BetterKey(VK_P);
    public static final BetterKey k_q = new BetterKey(VK_Q);
    public static final BetterKey k_r = new BetterKey(VK_R);
    public static final BetterKey k_s = new BetterKey(VK_S);
    public static final BetterKey k_t = new BetterKey(VK_T);
    public static final BetterKey k_u = new BetterKey(VK_U);
    public static final BetterKey k_v = new BetterKey(VK_V);
    public static final BetterKey k_w = new BetterKey(VK_W);
    public static final BetterKey k_x = new BetterKey(VK_X);
    public static final BetterKey k_y = new BetterKey(VK_Y);
    public static final BetterKey k_z = new BetterKey(VK_Z);

    public static final BetterKey k_1 = new BetterKey(VK_1);
    public static final BetterKey k_2 = new BetterKey(VK_2);
    public static final BetterKey k_3 = new BetterKey(VK_3);
    public static final BetterKey k_4 = new BetterKey(VK_4);
    public static final BetterKey k_5 = new BetterKey(VK_5);
    public static final BetterKey k_6 = new BetterKey(VK_6);
    public static final BetterKey k_7 = new BetterKey(VK_7);
    public static final BetterKey k_8 = new BetterKey(VK_8);
    public static final BetterKey k_9 = new BetterKey(VK_9);
    public static final BetterKey k_0 = new BetterKey(VK_0);

    public static final BetterKey k_up = new BetterKey(VK_UP);
    public static final BetterKey k_down = new BetterKey(VK_DOWN);
    public static final BetterKey k_left = new BetterKey(VK_LEFT);
    public static final BetterKey k_right = new BetterKey(VK_RIGHT);

    public static final BetterKey k_space = new BetterKey(VK_SPACE);
    public static final BetterKey k_shift = new BetterKey(VK_SHIFT);


    public static boolean isAnyKeyClicked() {
        for (BetterKey key: keys) {
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
        for (BetterKey key: keys) {
            key.run();
        }
    }

    static void reset() {
        for (BetterKey key: keys) {
            key.reset();
        }
    }


    private static KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            for (BetterKey key: keys) {
                if (key.getIntCode() == keyCode) {
                    key.doPress();
                }
            }
            Cheats.cheatLogic(keyCode);
        }


        @Override
        public void keyReleased(KeyEvent e) {
            for (BetterKey key: keys) {
                if (key.getIntCode() == e.getKeyCode()) {
                    key.doRelease();
                }
            }
        }

    };


    private static FocusAdapter AFKKeyPreventor = new FocusAdapter() {

        @Override
        public void focusLost(FocusEvent e) {
            for (BetterKey key: keys) {
                key.doRelease();
            }
        }
    };

}
