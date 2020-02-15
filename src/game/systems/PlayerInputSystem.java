package game.systems;

import game.components.Movement;
import game.controls.KeyboardControls;
import game.controls.MouseControls;
import game.entities.Entity;
import game.entities.Player;

import static game.controls.KeyboardControls.*;

public class PlayerInputSystem implements ISystem {

    @Override
    public void run(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;

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

        for (Movement movement : player.getAll(Movement.class)) {
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



    private void weaponControls() {
        if (MouseControls.getRightClicked()) {
            //player.getComponent(Weapon.class).spawn;
        }
    }

}