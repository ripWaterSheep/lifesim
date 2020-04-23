package lifesim.state.menus;

import lifesim.state.GameState;
import lifesim.state.menus.ui.Button;

import java.awt.*;
import java.util.ArrayList;


public abstract class Menu implements GameState {

    private final ArrayList<Button> buttons = new ArrayList<>();

    @Override
    public void update() {
        for (Button button: buttons) {
            button.listen();
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        for (Button button: buttons) {
            button.render(g2d);
        }
    }
}
