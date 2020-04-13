package lifesim.main.game.input;

public class InputListener {

    protected final int intCode;
    private boolean pressed = false;
    private int pressTime = 0;
    private boolean released = false;


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

    public boolean isReleased() {
        return released;
    }


    void doPress() {
        pressed = true;
    }

    void doRelease() {
        pressTime = 0;
        pressed = false;
        released = true;
    }


    void update() {
        if (pressed) pressTime += 1;
        released = false;
    }

}
