package lifesim.state.menus.ui;

import lifesim.game.entities.components.sprites.Sprite;
import lifesim.game.input.MouseInput;
import lifesim.util.math.geom.Vector2D;

import java.awt.*;


public class Button {

    private final Sprite sprite;
    private final Vector2D pos;


    public Button(Sprite sprite, Vector2D pos) {
        this.sprite = sprite;
        this.pos = pos;
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
    }

}
