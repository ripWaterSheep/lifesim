package lifesim.state.menus.ui;

import lifesim.util.geom.Vector2D;

public class MultiStateButton extends Button {

    private final int numStates;
    private int currentState = 0;

    public MultiStateButton(String label, Vector2D pos, ButtonSize sizeType, int numStates) {
        super(label, pos, sizeType);
        this.numStates = numStates;
    }


    @Override
    protected void onClick() {
        super.onClick();
        currentState++;
        if (currentState >= numStates) {
            currentState = 0;
        }
        evaluateState(currentState);
    }

    public void evaluateState(int state) {
    }

}
