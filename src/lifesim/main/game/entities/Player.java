package lifesim.main.game.entities;

import lifesim.main.game.controls.KeyInputListener;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.entities.components.*;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.setting.World;

import java.awt.*;


public final class Player extends MovementEntity {

    private World world;


    public Player() {
        super("Player", new DirectionalAnimatedSprite(
                new Animation(200, "player_idle_1", "player_idle_2"),
                new Animation(100, "player_left_1", "player_left_2"),
                new Animation(100, "player_backward_1", "player_backward_2"),
                new Animation(100, "player_right_1", "player_right_2"),
                new Animation(100, "player_forward_1", "player_forward_2")

                //new Animation(100, "Eh Walk Right 1", "Eh Walk Right 2", "Eh Walk Right 3", "Eh Walk Right 4")
                ),
                new Vector2D(0, 0), 6,
                new PlayerStats(1000, 1000, 0, 0, 0));
    }


    public Vector2D getDisplayPos() {
        return sprite.size.scale(-0.5);
    }


    public World getWorld() {
        return world;
    }

    public void setWorld(World newWorld) {
        world = newWorld;
        newWorld.add(this);
    }



    private double calculateSpeed() {
        double speed = defaultSpeed;
        if (KeyInputManager.k_space.isPressed()) {
            speed *= 1.5;
            ((PlayerStats) stats).tire(0.025);
        }
        return speed;
    }


    public void control() {
        boolean up, down, left, right;

        final KeyInputListener upKey = KeyInputManager.k_w;
        final KeyInputListener leftKey = KeyInputManager.k_a;
        final KeyInputListener downKey = KeyInputManager.k_s;
        final KeyInputListener rightKey = KeyInputManager.k_d;

        if (leftKey.isPressed() && rightKey.isPressed()) {
            left = leftKey.getReadTime() < rightKey.getReadTime();
            right = !left;
        } else {
            left = leftKey.isPressed();
            right = rightKey.isPressed();
        }
        if (upKey.isPressed() && downKey.isPressed()) {
            up = upKey.getReadTime() < downKey.getReadTime();
            down = !up;
        } else {
            up = upKey.isPressed();
            down = downKey.isPressed();
        }

        if (up || down || left || right) {
            movement.setMagnDir(calculateSpeed(), getIntendedAngle(up, down, left, right));
        } else {
            movement.set(0, 0);
        }
    }


    private double getIntendedAngle(boolean up, boolean down, boolean left, boolean right) {
        double angle = 0;
        // Get angles for different key directions (makes going diagonal the same speed as horizontal or vertical)
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

        return angle;
    }


    @Override
    public void update(World world) {
        super.update(world);
        control();
    }

}
