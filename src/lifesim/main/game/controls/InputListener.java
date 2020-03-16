package lifesim.main.game.controls;

public abstract class InputListener {

    protected final int intCode;
    private boolean pressed = false;
    private int pressTime = 0;


    public InputListener(int intCode) {
        this.intCode = intCode;
    }

    public int getIntCode() {
        return intCode;
    }

    public int getPressTime() {
        return pressTime;
    }

    public boolean isClicked() {
        return pressTime == 1;
    }

    public boolean isPressed() {
        return pressTime > 0;
    }


    void doPress() {
        pressed = true;
    }

    void doRelease() {
        pressed = false;
        pressTime = 0;
    }


    void run() {
        if (pressed) {
            pressTime += 1;
        }
    }


}
