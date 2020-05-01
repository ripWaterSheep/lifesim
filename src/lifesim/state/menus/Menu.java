package lifesim.state.menus;

import lifesim.state.engine.StateManager;
import lifesim.util.sprites.Sprite;
import lifesim.game.input.MouseInput;
import lifesim.state.GameState;
import lifesim.state.menus.ui.Button;
import lifesim.util.geom.Vector2D;

import java.awt.*;
import java.util.ArrayList;


public abstract class Menu implements GameState {

    private final Sprite bg;
    protected final ArrayList<Button> buttons = new ArrayList<>();


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
        bg.render(g2d, new Vector2D(0, 0), MouseInput.getCursorVelocity());

        for (Button button: buttons) {
            button.render(g2d);
        }
    }
}
