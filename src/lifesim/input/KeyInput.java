package lifesim.input;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;


public final class KeyInput {

    static final ArrayList<InputListener> keys = new ArrayList<>();

    public static final InputListener k_a = new InputListener(VK_A);
    public static final InputListener k_b = new InputListener(VK_B);
    public static final InputListener k_c = new InputListener(VK_C);
    public static final InputListener k_d = new InputListener(VK_D);
    public static final InputListener k_e = new InputListener(VK_E);
    public static final InputListener k_f = new InputListener(VK_F);
    public static final InputListener k_g = new InputListener(VK_G);
    public static final InputListener k_h = new InputListener(VK_H);
    public static final InputListener k_i = new InputListener(VK_I);
    public static final InputListener k_j = new InputListener(VK_J);
    public static final InputListener k_k = new InputListener(VK_K);
    public static final InputListener k_l = new InputListener(VK_L);
    public static final InputListener k_m = new InputListener(VK_M);
    public static final InputListener k_n = new InputListener(VK_N);
    public static final InputListener k_o = new InputListener(VK_O);
    public static final InputListener k_p = new InputListener(VK_P);
    public static final InputListener k_q = new InputListener(VK_Q);
    public static final InputListener k_r = new InputListener(VK_R);
    public static final InputListener k_s = new InputListener(VK_S);
    public static final InputListener k_t = new InputListener(VK_T);
    public static final InputListener k_u = new InputListener(VK_U);
    public static final InputListener k_v = new InputListener(VK_V);
    public static final InputListener k_w = new InputListener(VK_W);
    public static final InputListener k_x = new InputListener(VK_X);
    public static final InputListener k_y = new InputListener(VK_Y);
    public static final InputListener k_z = new InputListener(VK_Z);

    public static final InputListener k_1 = new InputListener(VK_1);
    public static final InputListener k_2 = new InputListener(VK_2);
    public static final InputListener k_3 = new InputListener(VK_3);
    public static final InputListener k_4 = new InputListener(VK_4);
    public static final InputListener k_5 = new InputListener(VK_5);
    public static final InputListener k_6 = new InputListener(VK_6);
    public static final InputListener k_7 = new InputListener(VK_7);
    public static final InputListener k_8 = new InputListener(VK_8);
    public static final InputListener k_9 = new InputListener(VK_9);
    public static final InputListener k_0 = new InputListener(VK_0);

    public static final InputListener k_up = new InputListener(VK_UP);
    public static final InputListener k_down = new InputListener(VK_DOWN);
    public static final InputListener k_left = new InputListener(VK_LEFT);
    public static final InputListener k_right = new InputListener(VK_RIGHT);
    public static final InputListener k_space = new InputListener(VK_SPACE);
    public static final InputListener k_shift = new InputListener(VK_SHIFT);
    public static final InputListener k_esc = new InputListener(VK_ESCAPE);
    public static final InputListener k_ctrl = new InputListener(VK_CONTROL);


    public static void init(Component c) {
        c.addKeyListener(keyAdapter);
        c.addFocusListener(AFKKeyPrevention);

        keys.add(k_a);
        keys.add(k_b);
        keys.add(k_c);
        keys.add(k_d);
        keys.add(k_e);
        keys.add(k_f);
        keys.add(k_g);
        keys.add(k_h);
        keys.add(k_i);
        keys.add(k_j);
        keys.add(k_k);
        keys.add(k_l);
        keys.add(k_m);
        keys.add(k_n);
        keys.add(k_o);
        keys.add(k_p);
        keys.add(k_q);
        keys.add(k_r);
        keys.add(k_s);
        keys.add(k_t);
        keys.add(k_u);
        keys.add(k_v);
        keys.add(k_w);
        keys.add(k_x);
        keys.add(k_y);
        keys.add(k_z);
        keys.add(k_1);
        keys.add(k_2);
        keys.add(k_3);
        keys.add(k_4);
        keys.add(k_5);
        keys.add(k_6);
        keys.add(k_7);
        keys.add(k_8);
        keys.add(k_9);
        keys.add(k_0);
        keys.add(k_up);
        keys.add(k_down);
        keys.add(k_left);
        keys.add(k_right);
        keys.add(k_space);
        keys.add(k_shift);
        keys.add(k_esc);
        keys.add(k_ctrl);
    }


    public static boolean isAnyKeyClicked() {
        boolean anyClicked = false;
        for (InputListener key: keys) {
            if (key.isClicked())
                anyClicked = true;
        }
        return anyClicked;
    }



    public static void update() {
        for (InputListener key: keys) {
            key.update();
        }
    }


    private static final KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            for (InputListener key: keys) {
                if (key.getIntCode() == keyCode) {
                    key.press();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            for (InputListener key: keys) {
                if (key.getIntCode() == e.getKeyCode()) {
                    key.release();
                }
            }
        }

    };


    private static final FocusAdapter AFKKeyPrevention = new FocusAdapter() {

        @Override
        public void focusLost(FocusEvent e) {
            for (InputListener key: keys) {
                key.release();
            }
        }
    };

}
