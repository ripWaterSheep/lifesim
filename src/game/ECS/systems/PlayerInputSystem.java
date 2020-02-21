package game.ECS.systems;

import game.ECS.components.MovementComponent;
import game.controls.MouseControls;
import game.ECS.entities.Entity;
import game.ECS.entities.Player;

import static game.controls.KeyboardControls.*;

public class PlayerInputSystem implements System {

    @Override
    public void run(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;

            sprintControls(player);
            movementControls(player);

        }
    }


    private void movementControls(Player player) {

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


    private void sprintControls(Player player) {
        if (getSpacePressed()) {
            player.get(MovementComponent.class).setSpeedRatio(1.5);
        }
    }



    private void weaponControls() {
        if (MouseControls.getRightClicked()) {
            //player.getComponent(Weapon.class).spawn;
        }
    }

}
