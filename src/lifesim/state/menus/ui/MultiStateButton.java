package lifesim.state.menus.ui;

import lifesim.io.output.GameWindow;
import lifesim.util.geom.Vector2D;

public abstract class MultiStateButton extends Button {

    private final int numStates;
    private int currentState = 0;

    public MultiStateButton(String label, Vector2D pos, ButtonType sizeType, GameWindow window, int numStates) {
        super(label, pos, sizeType, window);
        this.numStates = numStates;
    }


    @Override
    protected void onClick() {
        currentState++;
        if (currentState >= numStates) {
            currentState = 0;
        }
        evaluateState(currentState);
    }


    public abstract void evaluateState(int state);

}
