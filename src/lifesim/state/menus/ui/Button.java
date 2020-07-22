package lifesim.state.menus.ui;

import lifesim.io.output.CursorType;
import lifesim.io.output.GameWindow;
import lifesim.util.sprites.Sprite;
import lifesim.io.input.MouseInput;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public abstract class Button {

    private static final Color labelColor = new Color(119, 168, 173);
    private static final Font labelFont = FontLoader.getMainFont(14);

    private String label;
    private final Sprite sprite;
    private final Vector2D pos;
    private final GameWindow window;


    public Button(String label, Vector2D pos, ButtonType size, GameWindow window) {
        this.label = label;
        this.pos = pos.copy(); // Copy position vector to new reference to not be affected by external modification of original reference.
        this.sprite = size.sprite;
        this.window = window;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    private boolean mouseHovering() {
        return sprite.getBoundsAt(pos).contains(MouseInput.getCursorPos().toPoint());
    }

    public void listen() {
        if (mouseHovering()) {
            window.changeCursor(CursorType.POINTER);
            if (MouseInput.left.isClicked()) {
                onClick();
            }
        }
    }


    protected abstract void onClick();

    public void render(Graphics2D g2d) {
        Vector2D displayVel = new Vector2D(0, 0);
        if (mouseHovering()) {
            displayVel = MouseInput.getCursorVelocity();
        }
        sprite.render(g2d, pos,displayVel);
        GraphicsMethods.centeredString(g2d, label, pos.copy().translate(0, 1), labelFont, labelColor);
    }

}
