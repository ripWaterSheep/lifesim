package lifesim.state.menus;

import lifesim.state.engine.GamePanel;
import lifesim.util.sprites.Sprite;
import lifesim.input.MouseInput;
import lifesim.state.GameState;
import lifesim.state.menus.ui.Button;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;


public abstract class Menu implements GameState {

    private final Sprite bg;
    protected final List<Button> buttons = new ArrayList<>();


    protected Menu(Sprite bg) {
        this.bg = bg;
    }

    @Override
    public void update() {
        for (Button button: buttons) {
            button.listen();
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        bg.render(g2d, GamePanel.getCenterPos(), MouseInput.getCursorVelocity());

        for (Button button: buttons) {
            button.render(g2d);
        }
    }
}
