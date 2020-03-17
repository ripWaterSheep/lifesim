package lifesim.main.game.entities;

import lifesim.main.game.controls.KeyInputListener;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.entities.components.*;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.DirectionalAnimatedSprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.setting.World;
import lifesim.main.util.math.Geometry;

import java.util.ArrayList;

import static java.lang.Math.abs;


public final class Player extends InventoryEntity {

    private World world;


    public Player() {
        super("Player", new DirectionalAnimatedSprite(
                new Animation(200, "player_idle_1", "player_idle_2"),
                        new Animation(120, "player_right_1", "player_right_2"),
                new Animation(120, "player_forward_1", "player_forward_2"),
                        new Animation(120, "player_left_1", "player_left_2"),
                new Animation(120, "player_backward_1", "player_backward_2")

                //new Animation(100, "Eh Walk Right 1", "Eh Walk Right 2", "Eh Walk Right 3", "Eh Walk Right 4")
        ),
                new PlayerStats(1, 1000, 0, 0, 0), 4);
        movement.set(0, 0);
    }


    public Vector2D getDisplayPos() {
        return sprite.size.scale(-0.5);
    }


    public World getWorld() {
        return world;
    }

    public void setWorld(World newWorld) {
        world = newWorld;
        newWorld.add(this, new Vector2D(0, 0));
    }


    public void control() {
        boolean up, down, left, right;
        final KeyInputListener upKey = KeyInputManager.k_w,
                leftKey = KeyInputManager.k_a,
                downKey = KeyInputManager.k_s,
                rightKey = KeyInputManager.k_d;

        // Determine intended direction based on key presses.
        // If both keys in opposite directions are pressed, then keys pressed more recently have precedence over earlier pressed keys.
        left = leftKey.isPressed();
        right = rightKey.isPressed();
        if (left && right) {
            left = leftKey.getPressTime() < rightKey.getPressTime();
            right = !left;
        }
        up = upKey.isPressed();
        down = downKey.isPressed();
        if (up && down) {
            up = upKey.getPressTime() < downKey.getPressTime();
            down = !up;
        }

        // Get the correct angle based on combination of up, down, left, and right.
        double angle = 0;
        if (up) {
            if (left) angle = 45;
            else if (right) angle = 135;
            else angle = 90;
        } else if (down) {
            if (left) angle = 315;
            else if (right) angle = 225;
            else angle = 270;
        } else if (left) angle = 0;
        else if (right) angle = 180;

        // Move along x or y axis is needed, else slow down due to friction.
        double frictionDecelerationFactor = 0.85;
        if (left || right) movement.setXMagnDir(getIntendedSpeed(), angle);
        else movement.x *= frictionDecelerationFactor;
        if (up || down) movement.setYMagnDir(getIntendedSpeed(), angle);
        else movement.y *= frictionDecelerationFactor;
    }


    private double getIntendedSpeed() {
        double speed = defaultSpeed;
        if (KeyInputManager.k_space.isPressed()) {
            speed *= 1.5;
            ((PlayerStats) stats).tire(0.025);
        }
        return speed;
    }


    @Override
    protected void move() {
        super.move();
        control();
    }


    @Override
    public void onRemoval(World world) {
        super.onRemoval(world);
    }

}
