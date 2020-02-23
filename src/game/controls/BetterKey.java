package game.controls;

import java.awt.event.KeyEvent;

public class BetterKey {

    private final int keyCode;

    public int getKeyCode() {
        return keyCode;
    }


    private long readTime = 0;

    public long getReadTime() {
        return readTime;
    }


    private boolean isStartedPressed() {
        return readTime == 1;
    }

    private boolean isPressed() {
        return readTime > 0;
    }



    BetterKey(int keyCode) {
        BetterKeyboard.keys.add(this);
        this.keyCode = keyCode;
    }



    void doPress() {
        readTime += 1;
    }


}
