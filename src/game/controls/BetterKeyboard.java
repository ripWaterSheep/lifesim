package game.controls;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;


public final class BetterKeyboard {

    static final ArrayList<BetterKey> keys = new ArrayList<>();

    public final BetterKey a = new BetterKey(VK_A);
    public final BetterKey b = new BetterKey(VK_B);
    public final BetterKey c = new BetterKey(VK_C);
    public final BetterKey d = new BetterKey(VK_D);
    public final BetterKey e = new BetterKey(VK_E);
    public final BetterKey f = new BetterKey(VK_F);
    public final BetterKey g = new BetterKey(VK_G);
    public final BetterKey h = new BetterKey(VK_H);
    public final BetterKey i = new BetterKey(VK_I);
    public final BetterKey j = new BetterKey(VK_J);
    public final BetterKey k = new BetterKey(VK_K);
    public final BetterKey l = new BetterKey(VK_L);
    public final BetterKey m = new BetterKey(VK_M);
    public final BetterKey n = new BetterKey(VK_N);
    public final BetterKey o = new BetterKey(VK_O);
    public final BetterKey p = new BetterKey(VK_P);
    public final BetterKey q = new BetterKey(VK_Q);
    public final BetterKey r = new BetterKey(VK_R);
    public final BetterKey s = new BetterKey(VK_S);
    public final BetterKey t = new BetterKey(VK_T);
    public final BetterKey u = new BetterKey(VK_U);
    public final BetterKey v = new BetterKey(VK_V);
    public final BetterKey w = new BetterKey(VK_W);
    public final BetterKey x = new BetterKey(VK_X);
    public final BetterKey y = new BetterKey(VK_Y);
    public final BetterKey z = new BetterKey(VK_Z);

    public final BetterKey one = new BetterKey(VK_1);
    public final BetterKey two = new BetterKey(VK_2);
    public final BetterKey three = new BetterKey(VK_3);
    public final BetterKey four = new BetterKey(VK_4);
    public final BetterKey five = new BetterKey(VK_5);
    public final BetterKey six = new BetterKey(VK_6);
    public final BetterKey seven = new BetterKey(VK_7);
    public final BetterKey eight = new BetterKey(VK_8);
    public final BetterKey nine = new BetterKey(VK_9);
    public final BetterKey zero = new BetterKey(VK_0);

    public final BetterKey up = new BetterKey(VK_UP);
    public final BetterKey down = new BetterKey(VK_DOWN);
    public final BetterKey left = new BetterKey(VK_LEFT);
    public final BetterKey right = new BetterKey(VK_RIGHT);

    public final BetterKey space = new BetterKey(VK_SPACE);
    public final BetterKey shift = new BetterKey(VK_SHIFT);



    void init(JPanel panel) {
        panel.addKeyListener(keyAdapter);
    }



    private static KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {

            for (BetterKey key: keys) {
                if (key.getKeyCode() == e.getKeyCode()) {
                    key.doPress();
                }
            }
        }

    };

}
