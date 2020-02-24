package game.controls;

public abstract class BetterControl {

    protected final int intCode;

    private boolean clicked = false;
    private boolean pressed = false;


    private int readTime = 0;

    public BetterControl(int intCode) {
        this.intCode = intCode;
    }

    public int getIntCode() {
        return intCode;
    }

    public int getReadTime() {
        return readTime;
    }

    public boolean isClicked() {
        return readTime == 1;
    }

    public boolean isPressed() {
        return readTime > 0;
    }


    void doPress() {
        pressed = true;
    }

    void doRelease() {
        pressed = false;
        readTime = 0;
    }


    void run() {
        if (pressed) {
            readTime += 1;
        }
    }

    void reset() {
        clicked = false;
    }

}
