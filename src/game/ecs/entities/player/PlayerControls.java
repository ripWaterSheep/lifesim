package game.ecs.entities.player;

import game.controls.BetterKey;
import game.controls.BetterMouse;
import game.ecs.components.*;

import game.ecs.entities.Entity;
import util.Geometry;

import static game.controls.BetterKeyboard.*;
import static java.lang.Math.sqrt;

public class PlayerControls {

    private Player player;

    PlayerControls(Player player) {
        this.player = player;
    }


    public void run() {
        sprintControls();
        movementControls();
        weaponControls();
    }


    private void movementControls() {
        boolean up, down, left, right;

        final BetterKey upkey = k_w;
        final BetterKey leftKey = k_a;
        final BetterKey downkey = k_s;
        final BetterKey rightKey = k_d;

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

        for (MovementComponent movement : player.getAll(MovementComponent.class)) {
            movement.setAngle(getIntendedAngle(up, down, left, right));
            if (up || down || left || right) {
                movement.setMovementTowardsAngle();
            } else movement.beStationary();
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
        if (k_space.isPressed()) {
            player.get(MovementComponent.class).setSpeedRatio(1.5);
        }
    }


    private void weaponControls() {

        if (BetterMouse.right.isClicked()) {
            double strength = player.get(StatsComponent.class).getStrength();

            double size = 9 + (strength / 275);
            double angle = Geometry.getAngleBetween(BetterMouse.right.getX(), BetterMouse.right.getY(), main.Main.getPanel().getMidWidth(), main.Main.getPanel().getMidHeight());
            double damage = sqrt((strength / 16) + 1);
            System.out.println(damage);


            if (BetterMouse.right.isClicked()) {
                player.getWorld().add(new Entity("Player Bullet")
                        .add(player.getPos().copyCurrentState())
                        .add(new SpatialComponent(size, size, true))
                        .add(new MovementComponent(40, angle))
                        .add(new AttackComponent(damage, true, true))
                        .add(new ProjectileComponent(700))
                );
                /*player.get(SpawnerComponent.class).attemptSpawn(
                        player.getPos().getX(), player.getPos().getY(), player.getWorld());*/
            }
        }
    }

}
