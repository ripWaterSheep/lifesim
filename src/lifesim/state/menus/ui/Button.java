package lifesim.state.menus.ui;

import lifesim.state.engine.Main;
import lifesim.util.sprites.Sprite;
import lifesim.game.input.MouseInput;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.geom.Vector2D;

import java.awt.*;


public class Button {

    private static final Color labelColor = new Color(120, 180, 200);
    private static final Font labelFont = FontLoader.getMainFont(11);

    private String label;
    private final Sprite sprite;
    private final Vector2D pos;


    public Button(String label, Vector2D pos, ButtonSize size) {
        this.label = label;
        this.pos = pos;
        this.sprite = size.sprite;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    private boolean mouseHovering() {
        return sprite.getBoundsAt(pos).contains(MouseInput.getCursorPos().toPoint());
    }

    public void listen() {
        if (mouseHovering()) {
            Main.getWindow().changeCursor(CursorType.POINTER);
            if (MouseInput.left.isClicked()) {
                onClick();
            }
            if (MouseInput.right.isClicked()) {
                onPress();
            }
        }
    }


    protected void onClick() {
    }

    protected void onPress() {
    }


    public void render(Graphics2D g2d) {
        Vector2D displayVel = new Vector2D(0, 0);
        if (mouseHovering()) {
            displayVel = MouseInput.getCursorVelocity();
        }
        sprite.render(g2d, pos,displayVel);
        GraphicsMethods.centeredString(g2d, label, pos.copy().translate(0, 1), labelFont, labelColor);
    }

}
