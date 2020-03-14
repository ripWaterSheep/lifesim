package lifesim.main.game.entities;

import lifesim.main.game.controls.KeyInputListener;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.game.entities.components.AnimatedSprite;
import lifesim.main.game.entities.components.stats.PlayerStats;
import lifesim.main.game.setting.World;
import lifesim.main.game.entities.components.Sprite;
import lifesim.main.game.entities.components.Vector2D;

import java.awt.*;


public final class Player extends MovementEntity {

    private World world;


    public Player() {
        super("Player", new AnimatedSprite(35, "player_standing1", "player_standing2", "player_standing3", "player_standing4", "player_standing3", "player_standing2") , new Vector2D(0, 0), 10,
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


    public void control() {
        boolean up, down, left, right;

        final KeyInputListener upkey = KeyInputManager.k_w;
        final KeyInputListener leftKey = KeyInputManager.k_a;
        final KeyInputListener downkey = KeyInputManager.k_s;
        final KeyInputListener rightKey = KeyInputManager.k_d;

        if (leftKey.isPressed() && rightKey.isPressed()) {
            left = leftKey.getReadTime() < rightKey.getReadTime();
            right = !left;
        } else {
            left = leftKey.isPressed();
            right = rightKey.isPressed();
        }
        if (upkey.isPressed() && downkey.isPressed()) {
            up = upkey.getReadTime() < downkey.getReadTime();
            down = !up;
        } else {
            up = upkey.isPressed();
            down = downkey.isPressed();
        }

        if (up || down || left || right) {
            movement.setMagnDir(calculateSpeed(), getIntendedAngle(up, down, left, right));
        } else {
            movement.set(0, 0);
        }
    }


    private double calculateSpeed() {
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
        } else if (right) angle = 180;
        else if (left) angle = 0;

        return angle;
    }



    @Override
    public void update(World world) {
        super.update(world);
        control();
    }


    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
    }
}
