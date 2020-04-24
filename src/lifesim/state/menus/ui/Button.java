package lifesim.state.menus.ui;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.input.MouseInput;
import lifesim.util.GraphicsMethods;
import lifesim.util.fileIO.FontLoader;
import lifesim.util.math.geom.Vector2D;

import java.awt.*;


public class Button {

    private static final Color labelColor = new Color(120, 180, 200);
    private static final Font labelFont = FontLoader.getMainFont(10);

    private final String label;
    private final Sprite sprite;
    private final Vector2D pos;
    private final ButtonSize sizeType;


    public Button(String label, Vector2D pos, ButtonSize sizeType) {
        this.label = label;
        this.pos = pos;
        this.sizeType = sizeType;
        this.sprite = sizeType.sprite;
    }

    private boolean mouseHovering() {
        return sprite.getBoundsAt(pos).contains(MouseInput.getCursorPos().toPoint());
    }


    public void listen() {
        if (mouseHovering()) {
            if (MouseInput.left.isClicked()) {
                onClick();
            }
            if (MouseInput.right.isClicked()) {
                onPress();
            }
        }
    }


    public void onClick() {
    }

    public void onPress() {
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
