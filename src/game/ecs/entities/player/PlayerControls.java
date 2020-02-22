package game.ecs.entities.player;

import game.ecs.components.MovementComponent;
import game.ecs.components.SpawnerComponent;
import game.controls.MouseControls;

import static game.controls.KeyboardControls.*;

public class PlayerControls {

    private Player player;

    PlayerControls(Player player) {
        this.player = player;
    }


    public void run() {
        sprintControls();
        movementControls();
    }


    private void movementControls() {

        boolean up = false, down = false, left = false, right = false;

        if (getLeftPressed() && getRightPressed()) {
            if (getLeftReadTime() > getRightReadTime()) right = true;
            else left = true;
        } else if (getLeftPressed()) left = true;
        else if (getRightPressed()) right = true;

        if (getUpPressed() && getDownPressed()) {
            if (getUpReadTime() > getDownReadTime()) up = true;
            else down = true;
        } else if (getUpPressed()) up = true;
        else if (getDownPressed()) down = true;

        for (MovementComponent movement : player.getAll(MovementComponent.class)) {
            movement.setAngle(getIntendedAngle(up, down, left, right));
            movement.setMoving(up || down || left || right);
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
        if (getSpacePressed()) {
            player.get(MovementComponent.class).setSpeedRatio(1.5);
        }
    }



    private void weaponControls(Player player) {
        if (MouseControls.getRightClicked()) {
            player.get(SpawnerComponent.class).attemptSpawn(
                    player.getPos().getX(), player.getPos().getY(), player.getWorld());
        }
    }

}
