package game.controls;

public abstract class BetterControl {

    protected final int eventCode;

    boolean clicked = false;
    boolean pressed = false;


    private int readTime = 0;

    public BetterControl(int eventCode) {
        this.eventCode = eventCode;
    }

    public int getEventCode() {
        return eventCode;
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
