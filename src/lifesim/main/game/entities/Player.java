package lifesim.main.game.entities;

import lifesim.main.game.controls.KeyInputListener;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.entities.components.*;
import lifesim.main.game.entities.components.sprites.Animation;
import lifesim.main.game.entities.components.sprites.DirectionalAnimatedSprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.setting.World;

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
                4,
                new PlayerStats(1, 1000, 0, 0, 0));
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

        // Go in the direction of keys that are pressed, with keys pressed earlier having precedence over more recently pressed keys.
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

        double frictionDecelerationFactor = 0.87;
        if (left || right) movement.setXMagnDir(getIntendedSpeed(), getIntendedAngle(up, down, left, right));
        else movement.x *= frictionDecelerationFactor;
        if (up || down) movement.setYMagnDir(getIntendedSpeed(), getIntendedAngle(up, down, left, right));
        else movement.y *= frictionDecelerationFactor;


        double speedThreshold = 0.15;
        if (abs(movement.x) < speedThreshold) movement.x = 0;
        if (abs(movement.y) < speedThreshold) movement.y = 0;
    }


    private double getIntendedSpeed() {
        double speed = defaultSpeed;
        if (KeyInputManager.k_space.isPressed()) {
            speed *= 1.5;
            ((PlayerStats) stats).tire(0.025);
        }
        return speed;
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
