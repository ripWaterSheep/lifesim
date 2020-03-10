package lifesim.main.game.ecs.entities.player;

import lifesim.main.Game;
import lifesim.main.game.controls.KeyInputListener;
import lifesim.main.game.controls.MouseInputManager;


import lifesim.main.game.ecs.components.*;
import lifesim.main.game.ecs.entities.Entity;
import lifesim.main.util.Geometry;
import lifesim.main.game.controls.KeyInputManager;
import lifesim.main.util.MyMath;

import java.awt.*;

import static java.lang.Math.sqrt;

public class PlayerControls {

    private Player player;
    private StatsComponent stats;
    private MovementComponent movement;

    PlayerControls(Player player) {
        this.player = player;
        stats = player.get(StatsComponent.class);
        this.movement = player.get(MovementComponent.class);
    }


    public void run() {
        sprintControls();
        movementControls();
        weaponControls();
        movement.multiplySpeed(MyMath.scale(stats.getEnergy(), 0, 1000, 0.65, 1));;
    }


    private void movementControls() {
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
        } if (upkey.isPressed() && downkey.isPressed()) {
            up = upkey.getReadTime() < downkey.getReadTime();
            down = !up;
        } else {
            up = upkey.isPressed();
            down = downkey.isPressed();
        }

        movement.setAngle(getIntendedAngle(up, down, left, right));

        if (up || down || left || right) {
            movement.setMovementTowardsAngle();
            stats.tire(0.025);
        } else {
            movement.stopMovement();
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
        } else if (right) angle = 180;
        else if (left) angle = 0;

        return angle;
    }


    private void sprintControls() {
        if (KeyInputManager.k_space.isPressed()) {
            player.get(MovementComponent.class).multiplySpeed(1.5);
            stats.tire(0.025);
        }
    }


    private void weaponControls() {
        if (MouseInputManager.right.isClicked()) {
            double strength = player.get(StatsComponent.class).getStrength();

            double size = 10 + (strength / 275);
            double angle = Geometry.getAngleBetween(MouseInputManager.right.getX(), MouseInputManager.right.getY(), Game.getPanel().getMidWidth(), Game.getPanel().getMidHeight());
            double damage = sqrt((strength / 16) + 1);
            System.out.println(damage);

            player.getWorld().add(new Entity("Player Bullet")
                    .add(player.getPos().copyCurrentState())
                    .add(new SpatialComponent(size, size, true))
                    .add(new AppearanceComponent(new Color(31, 31, 31)))
                    .add(new MovementComponent(50, angle))
                    .add(new AttackComponent(damage, true, true))
                    .add(new ProjectileComponent(700))
            );
        }
    }

}
